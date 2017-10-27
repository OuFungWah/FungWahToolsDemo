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
？？？那么。。这个调用了两个我们实现好的方法的方法`getDataBaseLocked(boolean writable)`是何方神圣呢？
<br/>
是的，学习过数据库的应该能从这个方法的方法名中猜出来个七八十，那就是带锁访问数据库。
因为数据库中的数据要始终保持它的一致性，所以对数据库的数据修改时需要上锁，防止两个修改操作同时进行导致数据不一致，是一个数据库的知识点。
<br/>所以按照这个方法的功能，我们找到了调用它的方法
