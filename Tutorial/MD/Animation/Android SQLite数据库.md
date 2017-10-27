# Android SQLite数据库
现在，不管我们做什么项目都离不开两个字——数据。
要么上网拉数据，要么本地数据库的数据，这些数据都是做一个项目脱离不了的。
而不管是网上的接口拉数据还是本地数据，都离不开数据库存储数据，这就是我们要学习好Android数据的原因。
## Android数据库相关类：
### `SQLiteOpenHelper`
`SQLiteOpenHelper`是一个负责对数据库创建、管理和版本控制的工具类。
### `SQLiteDatabase`
`SQLiteDataBase`是官方封装好的`SQLite`数据库API类
### `ContentValues`
`ContentValues`是键值对存放容器类
### `CursorFactory`
`CursorFactory`是用于产生游标(Cursor)类的工厂类
### `Cursor`
`Cursor`是用于遍历搜索结果的游标
## 数据库的基础使用
### 1、继承抽象类`SQLiteDbOpenHelper`并重写相关方法
因为`SQLiteDbOpenHelper`是一个抽象类，所以我们需要新建一个它的子类并实现它的抽象方法。<br/>
重写两个构造函数：
* super(context, name, factory, version)：
* super(context, name, factory, version, errorHandler)：
重写两个方法：
* onCreate() :第一次创建数据库时调用
* onUpgrade() :更新数据库时调用
```java

public class BaseDatabaseHelper extends SQLiteOpenHelper {
    
    //注意SQLite的大小写
    private String createUserTable = "CREATE TABLE " + TABLE_USERS + "(" +
                " num CHAR(64) PRIMARY KEY NOT NULL," +
                " name CHAR(20) NOT NULL," +
                " password CHAR(20) NOT NULL," +
                " avatar CHAR(20)," +
                " sex CHAR(20) NOT NULL," +
                " birthday CHAR(20) NOT NULL," +
                " collage CHAR(20) NOT NULL," +
                " subject CHAR(20) NOT NULL," +
                " grade INT," +
                " class INT" +
                ")";

    /**
    * 构造函数
    * @param context 上下文资源
    * @param factory 游标工厂类对象，null为默认
    * @param name    数据库文件名字
    * @param version 数据库版本
    */
    public BaseDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
    * 带错误应对器的构造函数
    * @param context        上下文资源
    * @param factory        游标工厂类对象，null为默认
    * @param name           数据库文件名字
    * @param version        数据库版本
    * @param errorHandler   
    */
    public BaseDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //第一次创建数据库时会调用的方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行SQLite的建表语句
        db.execSQL(createUserTable);
    }

    /**
     * 更新数据库
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

```
[BaseDatabaseHelper.java](https://github.com/OuFungWah/FungWahToolsDemo/blob/master/fungwahtools/src/main/java/com/example/fungwahtools/database/helper/BaseDatabaseHelper.java)
#### 一点小思考：
我曾经一度认为`OpenHelper`的数据库创建是在创建`OpenHelper`对象的时候就创建好的，但还是有点小疑惑，于是去源码里面看了一下。。。
<br/>这是在源码中搜索的onCreate()出现的位置,不小心我们也看到了onUpGrade()和onDowngrade()
<br/>一言不合放源码:
```java

abstract class SQLiteDbOpenHelper{
    
    //...........前方省略
    
    private SQLiteDatabase getDatabaseLocked(boolean writable) {
            //............前方省略
    
                final int version = db.getVersion();
                if (version != mNewVersion) {
                    if (db.isReadOnly()) {
                        throw new SQLiteException(com.example.fungwahtools.com.example.fungwahtools.database +
                                db.getVersion() + " to " + mNewVersion + ": " + mName);
                    }
    
                    db.beginTransaction();
                    try {
                        if (version == 0) {
        //----------------我们的onCreate方法出现在这里----------------
                            onCreate(db);
                        } else {
                            if (version > mNewVersion) {
        //----------------我们的onDowngrade方法出现在这里----------------
                                onDowngrade(db, version, mNewVersion);
                            } else {
        //----------------我们的onUpgrade方法出现在这里----------------
                                onUpgrade(db, version, mNewVersion);
                            }
                        }
                        db.setVersion(mNewVersion);
                        db.setTransactionSuccessful();
                    } finally {
                        db.endTransaction();
                    }
                }
    
            //后方省略.............
        }
        
    //后方省略...........
}

```
？？？那么。。这个调用了我们实现好的方法的方法`getDataBaseLocked(boolean writable)`是何方神圣呢？
<br/>
是的，学习过数据库的应该能从这个方法的方法名中猜出来个七八十，那就是带锁访问数据库。
因为数据库中的数据要始终保持它的一致性，所以对数据库的数据修改时需要上锁，防止两个修改操作同时进行导致数据不一致，是一个数据库的知识点。
<br/>所以按照这个方法的功能，我们找到了调用它的方法
<br/>上源码~~~
```java

abstract class SQLiteOpenHelper{
    
    //前面省略.............
    
    //获取可写数据库
    public SQLiteDatabase getWritableDatabase() {
            synchronized (this) {
                return getDatabaseLocked(true);
            }
        }
    
    //获取只读数据库
    public SQLiteDatabase getReadableDatabase() {
            synchronized (this) {
                return getDatabaseLocked(false);
            }
        }
        
    //后面省略............
}

```
*通过阅读其源码发现，数据库是在调用获取数据库的方法时才进创建的*

### 2、创建一个数据库的操作类，定义你需要的个性化的数据库操作
当我们建立好`SQLiteDbOpenHelper`的子类以后，我们仅仅是定义好了数据库的获取类。
当我们通过`SQLiteDbOpenHelper`获取到了数据库以后还需要对数据进行增、删、改、查等操作，所以我们需要另外定义一个数据库操作类
就是常见的dao类，DAO(Data Access Object)。
<br/>上代码~
```java

public class BaseDao {

    private SQLiteOpenHelper helper;

    public BaseDao(SQLiteOpenHelper helper) {
        this.helper = helper;
    }

    /**
     * 
     * @param table 表名
     * @param contentValues 需要被插入的一行数据的所有必须字段与字段值对
     * @return 操作是否成功
     */
    protected boolean insert(String table, ContentValues contentValues) {
        boolean flag = false;
        long index = -1L;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            //第二个参数用于把没有指定字段的数据插入到第二个参数的列中,可为null
            index = database.insert(table, null, contentValues);
            if (index != -1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            database.close();
        }

        return flag;
    }

    /**
     *
     * @param table 表名
     * @param contentValues 需要被修改的字段与字段值对
     * @param where 约束字段 " name = ? "、" name = ? AND num = ? "、" name = 'Tom' " ，注意若在此参数指明了参数值请在Args参数处传null
     * @param whereArgs 约束字段对应的值
     * @return 操作是否成功
     */
    protected boolean update(String table, ContentValues contentValues, String where, String whereArgs[]) {
        boolean flag = false;
        int index = 0;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            /**
             * Convenience method for updating rows in the com.example.fungwahtools.database.
             *
             * @param table the table to update in 表名
             * @param values a map from column names to new column values. null is a
             *            valid value that will be translated to NULL.
             * @param whereClause the optional WHERE clause to apply when updating.
             *            Passing null will update all rows. 查找参数字段
             * @param whereArgs You may include ?s in the where clause, which
             *            will be replaced by the values from whereArgs. The values
             *            will be bound as Strings. 查找参数值
             * @return the number of rows affected 受影响的行数
             */
            index = database.update(table, contentValues, where, whereArgs);
            if (index != -1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            database.close();
        }
        return flag;
    }

    /**
     * 基础的删除功能
     * @param tableName 表名
     * @param where 约束字段名 " name = ? "、" name = ? AND num = ? "、" name = 'Tom' " ，注意若在此参数指明了参数值请在Args参数处传null
     * @param whereArgs 字段对应的值 对应的值 对应多少个问号就多少个值
     * @return 操作是否成功
     */
    private boolean delete(String tableName, String where, String whereArgs[]) {
        boolean flag = false;
        int index = -1;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            /**
             * Convenience method for deleting rows in the com.example.fungwahtools.database.
             *
             * @param table the table to delete from
             * @param whereClause the optional WHERE clause to apply when deleting.
             *            Passing null will delete all rows.
             * @param whereArgs You may include ?s in the where clause, which
             *            will be replaced by the values from whereArgs. The values
             *            will be bound as Strings.
             * @return the number of rows affected if a whereClause is passed in, 0
             *         otherwise. To remove all rows and get a count pass "1" as the
             *         whereClause.
             */
            index = database.delete(tableName, where, whereArgs);
            if (index != -1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            database.close();
        }
        return flag;
    }

    /**
     * 搜索全表
     *
     * @param table 表名
     * @return 返回所有符合条件的数据
     */
    private List<Map> selectAll(String table) {
        return select(table, null, null, null, null, null, null);
    }

    /**
     * 基础的 Select 方法
     *
     * @param table         表名
     * @param columns       选择要获取到的字段
     * @param selection     约束字段名 如： " name = ? "、" name = ? AND num = ? "、" name = 'Tom' "，注意若在此参数指明了参数值请在Args参数处传null
     * @param selectionArgs 对应的值 对应多少个问号就多少个值
     * @param groupBy       用于分组的字段名 " sex "
     * @param having        选择字段对应值的分组 " '男' "
     * @param orderBy       排序 " num ASC " " num DESC "
     * @return 返回所有符合条件的数据
     */
    public List<Map> select(String table, String[] columns, String selection,
                            String[] selectionArgs, String groupBy, String having,
                            String orderBy) {
        List<Map> mapList = new ArrayList<>();
        SQLiteDatabase database = null;
        /**
         * getType()返回的类型与对应的 int 值
         * int FIELD_TYPE_BLOB = 4;
         * int FIELD_TYPE_FLOAT = 2;
         * int FIELD_TYPE_INTEGER = 1;
         * int FIELD_TYPE_NULL = 0;
         * int FIELD_TYPE_STRING = 3;
         */
        Cursor cursor = null;
        try {
            database = helper.getReadableDatabase();
            /**
             * Query the given table, returning a {@link Cursor} over the result set.
             *
             * @param table The table name to compile the query against.
             * @param columns A list of which columns to return. Passing null will
             *            return all columns, which is discouraged to prevent reading
             *            data from storage that isn't going to be used.
             * @param selection A filter declaring which rows to return, formatted as an
             *            SQL WHERE clause (excluding the WHERE itself). Passing null
             *            will return all rows for the given table. 约束条件
             * @param selectionArgs You may include ?s in selection, which will be
             *         replaced by the values from selectionArgs, in order that they
             *         appear in the selection. The values will be bound as Strings. 约束条件参数
             * @param groupBy A filter declaring how to group rows, formatted as an SQL
             *            GROUP BY clause (excluding the GROUP BY itself). Passing null
             *            will cause the rows to not be grouped. 对某属性相等的行进行分组
             * @param having A filter declare which row groups to include in the cursor,
             *            if row grouping is being used, formatted as an SQL HAVING
             *            clause (excluding the HAVING itself). Passing null will cause
             *            all row groups to be included, and is required when row
             *            grouping is not being used.  选择对应属性值的分组
             * @param orderBy How to order the rows, formatted as an SQL ORDER BY clause
             *            (excluding the ORDER BY itself). Passing null will use the
             *            default sort order, which may be unordered. 排序方式
             * @return A {@link Cursor} object, which is positioned before the first entry. Note that
             * {@link Cursor}s are not synchronized, see the documentation for more details.
             * @see Cursor
             */
            cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
            while (cursor.moveToNext()) {
                Map map = new HashMap<>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    //判断数据类型再按相应的类型进行存储
                    switch (cursor.getType(i)) {
                        case 0:
                            map.put(cursor.getColumnName(i), null);
                            break;
                        case 1:
                            map.put(cursor.getColumnName(i), cursor.getInt(i));
                            break;
                        case 2:
                            map.put(cursor.getColumnName(i), cursor.getFloat(i));
                            break;
                        case 3:
                            map.put(cursor.getColumnName(i), cursor.getString(i));
                            break;
                        case 4:
                            map.put(cursor.getColumnName(i), cursor.getBlob(i));
                            break;
                    }
                }
                mapList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
        return mapList;
    }

}

```