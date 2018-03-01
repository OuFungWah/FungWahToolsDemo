# Google Java编程风格指南
* CrazyWah
* January 22,2018

# 目录
1. 介绍 *(Introduction)*
2. 源文件基础规范 *(Source file basics)*
3. 源文件结构规范 *(Source file structure)*
4. 格式规范 *(Formatting)*
5. 命名规范 *(Naming)*
6. 编程实践规范 *(Programming Practices)*
7. Javadoc规范

# 1 介绍 *(Introduction)*
  这份文档是Google Java编程风格规范的完整定义。当且仅当一个Java源文件符合此文档中的规则， 我们才认为它符合Google的Java编程风格。<br/>
  *This document serves as the complete definition of Google's coding standards for source code in the Java™ Programming Language. A Java source file is described as being in Google Style if and only if it adheres to the rules herein.*<br/><br/>
  与其它的编程风格指南一样，这里所讨论的不仅仅是编码格式美不美观的问题， 同时也讨论一些常规及编码标准。然而，这份文档主要侧重于我们普遍遵循的规则， 对于那些不是明确强制要求的，我们尽量避免提供意见。(不管是人或工具)<br/>
  *Like other programming style guides, the issues covered span not only aesthetic issues of formatting, but other types of conventions or coding standards as well. However, this document focuses primarily on the hard-and-fast rules that we follow universally, and avoids giving advice that isn't clearly enforceable (whether by human or tool).*

## 1.1 术语说明 *(Terminology notes)*
在本文档中，除非另有说明：<br/>
*In this document, unless otherwise clarified:*

1. 术语class可表示一个普通类，枚举类，接口或是annotation类型(@interface)<br/>
*The term class is used inclusively to mean an "ordinary" class, enum class, interface or annotation type (@interface).*
2. 术语comment只用来指代实现的注释(implementation comments)，我们不使用“documentation comments”一词，而是用Javadoc。<br/>
*The term member (of a class) is used inclusively to mean a nested class, field, method, or constructor; that is, all top-level contents of a class except initializers and comments.
The term comment always refers to implementation comments. We do not use the phrase "documentation comments", instead using the common term "Javadoc."*

其他的术语说明会偶尔在后面的文档出现。
*Other "terminology notes" will appear occasionally throughout the document.*

## 1.2 指南说明 *(Guide notes)*
本文档中的示例代码并不作为规范。也就是说，虽然示例代码是遵循Google编程风格，但并不意味着这是展现这些代码的唯一方式。 示例中的格式选择不应该被强制定为规则。<br/>
*Example code in this document is non-normative. That is, while the examples are in Google Style, they may not illustrate the only stylish way to represent the code. Optional formatting choices made in examples should not be enforced as rules.*

# 2 源文件基础规范 *(Source file basics)*
## 2.1 文件命名 *(File name)*
源文件以其最顶层的类名来命名，大小写敏感，文件扩展名为`.java`。<br/>
*The source file name consists of the case-sensitive name of the top-level class it contains (of which there is exactly one), plus the `.java` extension.*
## 2.2 文件编码：UTF-8 *(File encoding: UTF-8)*
源文件编码格式为UTF-8。<br/>
*Source files are encoded in UTF-8.*
## 2.3 特殊字符 *(Special characters)*
### 2.3.1 空白字符 *(Whitespace characters)*
除了行结束符序列，ASCII水平空格字符(0x20，即空格)是源文件中唯一允许出现的空白字符，这意味着：<br/>
*Aside from the line terminator sequence, the ASCII horizontal space character (0x20) is the only whitespace character that appears anywhere in a source file. This implies that:*
1. 所有其它字符串中的空白字符都要进行转义。<br/>
*All other whitespace characters in string and character literals are escaped.*
2. 制表符不用于缩进。<br/>
*Tab characters are not used for indentation.*

### 2.3.2 特殊转义序列 *(Special escape sequences)*
对于具有特殊转义序列的任何字符(`\b`, `\t`, `\n`, `\f`, `\r`, ", '及`\`)，我们使用它的转义序列，而不是相应的八进制(比如`\012`)或Unicode(比如`\u000a`)转义。<br/>
*For any character that has a special escape sequence (`\b`, `\t`, `\n`, `\f`, `\r`, `\"`, `\'` and `\\`), that sequence is used rather than the corresponding octal (e.g. `\012`) or Unicode (e.g. `\u000a`) escape.*
### 2.3.3 非ASCII字符 *(Non-ASCII characters)*
对于剩余的非ASCII字符，是使用实际的Unicode字符(比如∞)，还是使用等价的Unicode转义符(比如\u221e)，取决于哪个能让代码更易于阅读和理解。<br/>
*For the remaining non-ASCII characters, either the actual Unicode character (e.g. `∞`) or the equivalent Unicode escape (e.g. `\u221e`) is used. The choice depends only on which makes the code easier to read and understand, although Unicode escapes outside string literals and comments are strongly discouraged.*

***Tip: 在使用Unicode转义符或是一些实际的Unicode字符时，建议做些注释给出解释，这有助于别人阅读和理解。***<br/>

例如:

```java
String unitAbbrev = "μs";                                 | 赞，即使没有注释也非常清晰
String unitAbbrev = "\u03bcs"; // "μs"                    | 允许，但没有理由要这样做
String unitAbbrev = "\u03bcs"; // Greek letter mu, "s"    | 允许，但这样做显得笨拙还容易出错
String unitAbbrev = "\u03bcs";                            | 很糟，读者根本看不出这是什么
return '\ufeff' + content; // byte order mark             | Good，对于非打印字符，使用转义，并在必要时写上注释
```

***Tip: 永远不要由于害怕某些程序可能无法正确处理非ASCII字符而让你的代码可读性变差。当程序无法正确处理非ASCII字符时，它自然无法正确运行， 你就会去fix这些问题的了。(言下之意就是大胆去用非ASCII字符，如果真的有需要的话)***

# 3 源文件结构规范 *(Source file structure)*
一个源文件包含(按顺序地)：<br/>
*A source file consists of, in order:*
  1. 许可证或版权信息(如有需要)<br/>
  *License or copyright information, if present*
  2. package语句<br/>
  *Package statement*
  3. import语句<br/>
  *Import statements*
  4. 一个顶级类(只有一个)<br/>
  *Exactly one top-level class*

以上每个部分之间用一个空行隔开。<br/>
*Exactly one blank line separates each section that is present.*
## 3.1 许可证或版权信息 *(License or copyright information, if present)*
如果一个文件包含许可证或版权信息，那么它应当被放在文件最前面。<br/>
*If license or copyright information belongs in a file, it belongs here.*

## 3.2 package语句 *(Package statement)*
package语句不换行，列限制(4.4节)并不适用于package语句。(即package语句写在一行里)<br/>
*The package statement is not line-wrapped. The column limit (Section 4.4, Column limit: 100) does not apply to package statements.*

## 3.3 import语句 *(Import statements)*
### 3.3.1 import不要使用通配符 *(No wildcard imports)*
即，不要出现类似这样的import语句：`import java.util.*;`<br/>
*Wildcard imports, static or otherwise, are not used.*
### 3.3.2 不要换行 *(No line-wrapping)*
import语句不换行，列限制(4.4节)并不适用于import语句。(每个import语句独立成行)<br/>
*Import statements are not line-wrapped. The column limit (Section 4.4, Column limit: 100) does not apply to import statements.*
### 3.3.3 顺序和间距 *(Ordering and spacing)*
import语句可分为以下几组，按照这个顺序，每组由一个空行分隔：<br/>
*Imports are ordered as follows:*

1. 所有的静态导入独立成组<br/>
*All static imports in a single block.*
2. 所有非静态导入独立一个模块<br/>
*All non-static imports in a single block.*

静态与非静态模块之间使用空行分割，模块内不能再使用空行<br/>
*If there are both static and non-static imports, a single blank line separates the two blocks. There are no other blank lines between import statements.*<br/>
各个模块里面的导入均按ASCII顺序排序<br/>
*Within each block the imported names appear in ASCII sort order. (Note: this is not the same as the import statements being in ASCII sort order, since '.' sorts before ';'.)*
### 3.3.4 无静态导入 *(No static import for classes)*
静态导入不用于静态嵌套类。它们使用常规导入。
*Static import is not used for static nested classes. They are imported with normal imports.*

## 3.4 类声明 *(Class declaration)*
### 3.4.1 只有一个顶级类声明 *(Exactly one top-level class declaration)*
每个源文件都有且只有一个和源文件同名的顶级类
*Each top-level class resides in a source file of its own.*
### 3.4.2 类成员排序 *(Ordering of class contents)*
类的成员顺序对易学性有很大的影响，但这也不存在唯一的通用法则。不同的类对成员的排序可能是不同的。 最重要的一点，每个类应该以某种逻辑去排序它的成员，维护者应该要能解释这种排序逻辑。比如， 新的方法不能总是习惯性地添加到类的结尾，因为这样就是按时间顺序而非某种逻辑来排序的。<br/>
*The order you choose for the members and initializers of your class can have a great effect on learnability. However, there's no single correct recipe for how to do it; different classes may order their contents in different ways.What is important is that each class uses some logical order, which its maintainer could explain if asked. For example, new methods are not just habitually added to the end of the class, as that would yield "chronological by date added" ordering, which is not a logical ordering.*
### 3.4.2.1 重载：永不分离 *(Overloads: never split)*
当一个类有多个构造函数，或是多个同名方法，这些函数/方法应该按顺序出现在一起，中间不要放进其它函数/方法。
*When a class has multiple constructors, or multiple methods with the same name, these appear sequentially, with no other code in between (not even private members).*
# 4 格式 *(Formatting)*
术语说明：块状结构(block-like construct)指的是一个类，方法或构造函数的主体。需要注意的是，数组初始化中的初始值可被选择性地视为块状结构(4.8.3.1节)。<br/>
*Terminology Note: block-like construct refers to the body of a class, method or constructor. Note that, by Section 4.8.3.1 on array initializers, any array initializer may optionally be treated as if it were a block-like construct.*

## 4.1 大括号 *(Braces)*
### 4.1.1 使用大括号(即使是可选的) *(Braces are used where optional)*
大括号与`if, else, for, do, while`语句一起使用，即使只有一条语句(或是空)，也应该把大括号写上。<br/>
*Braces are used with `if`, `else`, `for`, `do` and `while` statements, even when the body is empty or contains only a single statement.*

### 4.1.2 4.1.2 非空块：K & R 风格 *(Nonempty blocks: K & R style)*
对于非空块和块状结构，大括号遵循Kernighan和Ritchie风格 (Egyptian brackets):<br/>
*Braces follow the Kernighan and Ritchie style ("[Egyptian brackets](http://www.codinghorror.com/blog/2012/07/new-programming-jargon.html)") for nonempty blocks and block-like constructs:*
* 左大括号前不换行<br/>
*No line break before the opening brace.*
* 左大括号后换行<br/>
*Line break after the opening brace.*
* 右大括号前换行<br/>
*Line break before the closing brace.*
* 如果右大括号是一个语句、函数体或类的终止，则右大括号后换行; 否则不换行。例如，如果右大括号后面是else或逗号，则不换行。<br/>
*Line break after the closing brace, only if that brace terminates a statement or terminates the body of a method, constructor, or named class. For example, there is no line break after the brace if it is followed by `else` or a comma.*

示例:
```Java
return new MyClass() {
  @Override public void method() {
    if (condition()) {
      try {
        something();
      } catch (ProblemException e) {
        recover();
      }
    }
  }
};
```
4.8.1节给出了enum类的一些例外。<br/>
*A few exceptions for enum classes are given in Section 4.8.1, Enum classes.*
### 4.1.3 空块：可以用简洁版本 *(Empty blocks: may be concise)*
一个空的块状结构里什么也不包含，大括号可以简洁地写成{}，不需要换行。例外：如果它是一个多块语句的一部分(if/else 或 try/catch/finally) ，即使大括号内没内容，右大括号也要换行。<br/>
*An empty block or block-like construct may be in K & R style (as described in Section 4.1.2). Alternatively, it may be closed immediately after it is opened, with no characters or line break in between (`{}`), unless it is part of a multi-block statement (one that directly contains multiple blocks: `if/else` or `try/catch/finally`).*

例如：

```java
  // 这个样式合适
  void doNothing() {}

  // 这个样式同样合适
  void doNothingElse() {
  }

```
```java
  // 这个样式不合适: 在多模块的语句中有个不简洁的空模块
  try {
    doSomething();
  } catch (Exception e) {}
```

## 4.2 块缩进：2个空格 *(Block indentation: +2 spaces)*
每当开始一个新的块，缩进增加2个空格，当块结束时，缩进返回先前的缩进级别。缩进级别适用于代码和注释。(见4.1.2节中的代码示例)<br/>
*Each time a new block or block-like construct is opened, the indent increases by two spaces. When the block ends, the indent returns to the previous indent level. The indent level applies to both code and comments throughout the block. (See the example in Section 4.1.2, Nonempty blocks: K & R Style.)*
## 4.3 一行一个语句 *(One statement per line)*
每一个语句的结束换行<br/>
*(Each statement is followed by a line break.)*
## 4.4 列限制：80或100 *(Column limit: 100)*
一个项目可以选择一行80个字符或100个字符的列限制，除了下述例外，任何一行如果超过这个字符数限制，必须自动换行。<br/>
*Java code has a column limit of 100 characters. A "character" means any Unicode code point. Except as noted below, any line that would exceed this limit must be line-wrapped, as explained in Section 4.5, Line-wrapping.*

***不管是何种Unicode编码，标点都算一个字符，就算看起来多大或多小。例如：全角字符（如中文或韩文），您可以选择比此规则严格要求的地方更早地换行。*** <br/>
***Each Unicode code point counts as one character, even if its display width is greater or less. For example, if using fullwidth characters, you may choose to wrap the line earlier than where this rule strictly requires.***

例如：
1. 不可能满足列限制的行(例如，Javadoc中的一个长URL，或是一个长的JSNI方法参考)。<br/>
*Lines where obeying the column limit is not possible (for example, a long URL in Javadoc, or a long JSNI method reference).*
2. `package`和`import`语句(见3.2节和3.3节)。<br/>
*`package` and `import` statements (see Sections 3.2 Package statement and 3.3 Import statements).*
3. 注释中那些可能被剪切并粘贴到shell中的命令行。<br/>
*Command lines in a comment that may be cut-and-pasted into a shell.*

## 4.5 自动换行 *(Line-wrapping)*
术语说明：一般情况下，一行长代码为了避免超出列限制(80或100个字符)而被分为多行，我们称之为自动换行(line-wrapping)。<br/>
*Terminology Note: When code that might otherwise legally occupy a single line is divided into multiple lines, this activity is called line-wrapping.*
我们并没有全面，确定性的准则来决定在每一种情况下如何自动换行。很多时候，对于同一段代码会有好几种有效的自动换行方式。<br/>
*There is no comprehensive, deterministic formula showing exactly how to line-wrap in every situation. Very often there are several valid ways to line-wrap the same piece of code.*

***注意：尽管换行的典型原因是为了避免列限制溢出，但即使是实际上符合列限制的代码，也可以由作者自行决定将其换行。<br/>***
***Note: While the typical reason for line-wrapping is to avoid overflowing the column limit, even code that would in fact fit within the column limit may be line-wrapped at the author's discretion.***

***Tip: 提取方法或局部变量可以在不换行的情况下解决代码过长的问题(是合理缩短命名长度吧)***<br/>
***Tip: Extracting a method or local variable may solve the problem without the need to line-wrap.***

### 4.5.1 断开处 *(Where to break)*
自动换行的基本准则是：更倾向于在更高的语法级别处断开<br/>
*The prime directive of line-wrapping is: prefer to break at a higher syntactic level. Also:*
1. 当一个线在非赋值运算符中断裂时，该符号出现在该符号之前。 （请注意，这与Google风格在其他语言（如C ++和JavaScript）中使用的做法不同。）<br/>
*When a line is broken at a non-assignment operator the break comes before the symbol. (Note that this is not the same practice used in Google style for other languages, such as C++ and JavaScript.)*
  * 这也适用于以下“类似操作符”的符号：<br/>
  *This also applies to the following "operator-like" symbols:*
    * 点分隔符`.`<br/>*the dot separator (`.`)*
    * 方法引用的双冒号`::`<br/> *the two colons of a method reference (`::`)*
    * 一个类型绑定的＆符号
    *an ampersand in a type bound (`<T extends Foo & Bar>`)*
    * 一个Catch模块的大括号处<br/>
    *a pipe in a catch block (`catch (FooException | BarException e)`).*
2. 如果在赋值运算符处断开，通常的做法是在该符号后断开(比如=，它与前面的内容留在同一行)。这条规则也适用于`foreach`语句中的分号。<br/>
*When a line is broken at an assignment operator the break typically comes after the symbol, but either way is acceptable.This also applies to the "assignment-operator-like" colon in an enhanced for ("foreach") statement.*
* 方法名或构造函数名与左括号留在同一行。<br/>
*A method or constructor name stays attached to the open parenthesis (() that follows it.*
* 逗号(,)与其前面的内容留在同一行。<br/>
*A comma (,) stays attached to the token that precedes it.*
* 一条线不会在拉姆达的箭头( `=>` )附近断开，除非在拉姆达的主体由单个无支撑表达式组成的情况下，箭头后面可能会出现一个断点。<br/>
*A line is never broken adjacent to the arrow in a lambda, except that a break may come immediately after the arrow if the body of the lambda consists of a single unbraced expression.*<br/>
例如：

```java
MyLambda<String, Long, Object> lambda =
    (String label, Long value, Object obj) -> {
        ...
    };

Predicate<String> predicate = str ->
    longExpressionInvolving(str);

```
***注意，换行的初衷在于让代码更加清晰，代码行数不一定要很少***<br/>
***Note: The primary goal for line wrapping is to have clear code, not necessarily code that fits in the smallest number of lines.***

### 4.5.2 自动换行时缩进至少+4个空格 *(Indent continuation lines at least +4 spaces)*
自动换行时，第一行后的每一行至少比第一行多缩进4个空格<br/>
*When line-wrapping, each line after the first (each continuation line) is indented at least +4 from the original line.*<br/><br/>
当存在连续自动换行时，缩进可能会多缩进不只4个空格(语法元素存在多级时)。一般而言，两个连续行使用相同的缩进当且仅当它们开始于同级语法元素。<br/>
*When there are multiple continuation lines, indentation may be varied beyond +4 as desired. In general, two continuation lines use the same indentation level if and only if they begin with syntactically parallel elements.*<br/><br/>
第4.6.3水平对齐一节中指出，不鼓励使用可变数目的空格来对齐前面行的符号。<br/>
*Section 4.6.3 on Horizontal alignment addresses the discouraged practice of using a variable number of spaces to align certain tokens with previous lines.*

## 4.6 空格 *(Whitespace)*
### 4.6.1 垂直空白 *(Vertical Whitespace)*
以下情况需要使用一个空行：<br/>
*A single blank line appears:*
1. 类内连续的成员之间：字段，构造函数，方法，嵌套类，静态初始化块，实例初始化块。<br/>
*Between consecutive members or initializers of a class: fields, constructors, methods, nested classes, static initializers, and instance initializers.*
  * **例外：** 两个连续字段之间的空行是可选的，用于字段的空行主要用来对字段进行逻辑分组。<br/>
*Exception: A blank line between two consecutive fields (having no other code between them) is optional. Such blank lines are used as needed to create logical groupings of fields.*
  * **例外：** 两个枚举常量之间的空行在 4.8.1 小节中指出<br/>
  *Exception: Blank lines between enum constants are covered in Section 4.8.1.*
2. 在函数体内，语句的逻辑分组间使用空行<br/>
*Between statements, as needed to organize the code into logical subsections.*
3. 类内的第一个成员前或最后一个成员后的空行是可选的(既不鼓励也不反对这样做，视个人喜好而定)。<br/>
*Optionally before the first member or initializer, or after the last member or initializer of the class (neither encouraged nor discouraged).*
4. 要满足本文档中其他节的空行要求(比如3.3节：import语句)<br/>
*As required by other sections of this document (such as Section 3, Source file structure, and Section 3.3, Import statements).*

多个连续的空行是允许的，但没有必要这样做(我们也不鼓励这样做)。<br/>
*Multiple consecutive blank lines are permitted, but never required (or encouraged).*


### 4.6.2 水平空格 *(Horizontal whitespace)*
除了语言需求和其它规则，并且除了文字，注释和Javadoc用到单个空格，单个ASCII空格也出现在以下几个地方：<br/>
*Beyond where required by the language or other style rules, and apart from literals, comments and Javadoc, a single ASCII space also appears in the following places only.*

1. 分隔任何保留字与紧随其后的左括号(`(`)(如`if`, `for` `catch`等)。<br/>
*Separating any reserved word, such as `if`, `for` or `catch`, from an open parenthesis (`(`) that follows it on that line*
2. 分隔任何保留字与其前面的右大括号(`}`)(如`else`, `catch`)。<br/>
*Separating any reserved word, such as `else` or `catch`, from a closing curly brace (`}`) that precedes it on that line*
3. 在任何左大括号前({)，两个例外：<br/>
  *Before any open curly brace ({), with two exceptions:*
  * `@SomeAnnotation({a, b})`(不使用空格)。<br/>
  *@SomeAnnotation({a, b}) (no space is used)*
  * `String[][] x = {{"foo"}};`(大括号间没有空格，见下面的Note)。<br/>*String[][] x = {{"foo"}}; (no space is required between {{, by item 8 below)*
4. 在任何二元或三元运算符的两侧。这也适用于以下“类运算符”符号：<br/>
*On both sides of any binary or ternary operator. This also applies to the following "operator-like" symbols:*
  * 类型界限中的&(`<T extends Foo & Bar>`)。<br/>
  *the ampersand in a conjunctive type bound: <T extends Foo & Bar>*
  * catch块中的管道符号(`catch (FooException | BarException e`)。<br/>
  *the pipe for a catch block that handles multiple exceptions: catch (FooException | BarException e)*
  * `foreach`语句中的冒号。<br/>
  *the colon (:) in an enhanced for ("foreach") statement*
  * *the arrow in a lambda expression: (String str) -> str.length()*
  <br/>但不用在：
  * 用在如(`Object::toString`)的双冒号<br/>
  *the two colons (`::`) of a method reference, which is written like Object::toString*<br/>
  * 用在如(`Object.toString()`)的`.`点分隔符<br/>
  *the dot separator (.), which is written like object.toString()*
5. 在`,:;`或右括号`)`后面<br/>
*After `,:;` or the closing parenthesis ()) of a cast*
6. 如果在一条语句后做注释，则双斜杠(//)两边都要空格。这里可以允许多个空格，但没有必要。<br/>
*On both sides of the double slash (//) that begins an end-of-line comment. Here, multiple spaces are allowed, but not required.*
7. 类型和变量之间：List list。<br/>
*Between the type and variable of a declaration: List<String> list*
8. 数组初始化中，大括号内的空格是可选的<br/>
*Optional just inside both braces of an array initializer*
  * 即`new int[] {5, 6}`和`new int[] { 5, 6 }`都是可以的。<br/>
  *new int[] {5, 6} and new int[] { 5, 6 } are both valid*

***Note：这个规则并不要求或禁止一行的开头或结尾需要额外的空格，只对内部空格做要求。***<br/>
*This rule is never interpreted as requiring or forbidding additional space at the start or end of a line; it addresses only interior space.*
### 4.6.3 水平对齐：不做要求 *(Horizontal alignment: never required)*
术语说明：水平对齐指的是通过增加可变数量的空格来使某一行的字符与上一行的相应字符对齐。
*Terminology Note: Horizontal alignment is the practice of adding a variable number of additional spaces in your code with the goal of making certain tokens appear directly below certain other tokens on previous lines.*<br/><br/>
这是允许的(而且在不少地方可以看到这样的代码)，但Google编程风格对此不做要求。即使对于已经使用水平对齐的代码，我们也不需要去保持这种风格。<br/>
*This practice is permitted, but is never required by Google Style. It is not even required to maintain horizontal alignment in places where it was already used.*<br/>

以下示例先展示未对齐的代码，然后是对齐的代码：

```java
private int x; // this is fine
private Color color; // this too

private int   x;      // permitted, but future edits
private Color color;  // may leave it unaligned
```

***Tip：对齐可增加代码可读性，但它为日后的维护带来问题。考虑未来某个时候，我们需要修改一堆对齐的代码中的一行。 这可能导致原本很漂亮的对齐代码变得错位。很可能它会提示你调整周围代码的空白来使这一堆代码重新水平对齐(比如程序员想保持这种水平对齐的风格)， 这就会让你做许多的无用功，增加了reviewer的工作并且可能导致更多的合并冲突。***<br/>
*Tip: Alignment can aid readability, but it creates problems for future maintenance. Consider a future change that needs to touch just one line. This change may leave the formerly-pleasing formatting mangled, and that is allowed. More often it prompts the coder (perhaps you) to adjust whitespace on nearby lines as well, possibly triggering a cascading series of reformattings. That one-line change now has a "blast radius." This can at worst result in pointless busywork, but at best it still corrupts version history information, slows down reviewers and exacerbates merge conflicts.*

## 4.7 用小括号来限定组：推荐 *(Grouping parentheses: recommended)*
除非作者和reviewer都认为去掉小括号也不会使代码被误解，或是去掉小括号能让代码更易于阅读，否则我们不应该去掉小括号。 我们没有理由假设读者能记住整个Java运算符优先级表。<br/>
*Optional grouping parentheses are omitted only when author and reviewer agree that there is no reasonable chance the code will be misinterpreted without them, nor would they have made the code easier to read. It is not reasonable to assume that every reader has the entire Java operator precedence table memorized.*

## 4.8 具体结构 *(Specific constructs)*
### 4.8.1 枚举类 *(Enum classes)*
每个枚举常量的逗号后面的换行是可选的。添加空白行（通常是一行）同样是允许的。只是其中一个可能性。<br/>
*After each comma that follows an enum constant, a line break is optional. Additional blank lines (usually just one) are also allowed. This is one possibility:*

```java
private enum Answer {
  YES {
    @Override public String toString() {
      return "yes";
    }
  },

  NO,
  MAYBE
}
```

一个没有方法和没有文档的枚举类型可选择性地如数组初始化一般格式化（数组格式化地具体操作详见4.8.3.1小节）
*An enum class with no methods and no documentation on its constants may optionally be formatted as if it were an array initializer (see Section 4.8.3.1 on array initializers).*

```java
private enum Suit { CLUBS, HEARTS, SPADES, DIAMONDS }
```

由于枚举类也是一个类，因此所有适用于其它类的格式规则也适用于枚举类。<br/>
*Since enum classes are classes, all other rules for formatting classes apply.*

### 4.8.2 变量声明 *(Variable declarations)*

#### 4.8.2.1 每次只声明一个变量 *(One variable per declaration)*
不要使用组合声明，比如`int a, b;`。<br/>
*Every variable declaration (field or local) declares only one variable: declarations such as int a, b; are not used.*<br/><br/>
***另外：在for循环的开头处允许多个变量的声明***<br/>
*Exception: Multiple variable declarations are acceptable in the header of a for loop.*

#### 4.8.2.2 需要时才声明，并尽快进行初始化 *(Declared when needed)*

不要在一个代码块的开头把局部变量一次性都声明了(这是c语言的做法)，而是在第一次需要使用它时才声明。 局部变量在声明时最好就进行初始化，或者声明后尽快进行初始化。<br/>
*Local variables are not habitually declared at the start of their containing block or block-like construct. Instead, local variables are declared close to the point they are first used (within reason), to minimize their scope. Local variable declarations typically have initializers, or are initialized immediately after declaration.*

### 4.8.3 数组 *(Arrays)*

#### 4.8.3.1 数组初始化：可写成块状结构 *(Array initializers: can be "block-like")*
数组初始化可以写成块状结构，比如，下面的写法都是OK的：<br/>
Any array initializer may optionally be formatted as if it were a "block-like construct." For example, the following are all valid (not an exhaustive list):

```java
new int[] {          
  0, 1, 2, 3            
};     

new int[] {             
  0, 1,               
  2, 3
};       

new int[] {
  0,
  1,
  2,
  3
};

new int[]
    {0, 1, 2, 3};
```


#### 4.8.3.2 非C风格的数组声明 *(No C-style array declarations)*
中括号是类型的一部分：`String[] args`， 而非`String args[]`。<br/>
*The square brackets form a part of the type, not the variable: String[] args, not String args[].*

### 4.8.4 switch语句 *(Switch statements)*
术语说明：switch块的大括号内是一个或多个语句组。每个语句组包含一个或多个switch标签(case FOO:或default:)，后面跟着一条或多条语句。<br/>
Terminology Note: Inside the braces of a switch block are one or more statement groups. Each statement group consists of one or more switch labels (either case FOO: or default:), followed by one or more statements (or, for the last statement group, zero or more statements).

#### 4.8.4.1 缩进 *(Indentation)*
与其它块状结构一致，switch块中的内容缩进为2个空格。<br/>
*As with any other block, the contents of a switch block are indented +2.*<br/><br/>
每个switch标签后新起一行，再缩进2个空格，写下一条或多条语句。<br/>
After a switch label, there is a line break, and the indentation level is increased +2, exactly as if a block were being opened. The following switch label returns to the previous indentation level, as if a block had been closed.

#### 4.8.4.2 Fall-through:注释 *(Fall-through: commented)*
在一个switch块内，每个语句组要么通过break, continue, return或抛出异常来终止，要么通过一条注释来说明程序将继续执行到下一个语句组， 任何能表达这个意思的注释都是OK的(典型的是用// fall through)。这个特殊的注释并不需要在最后一个语句组(一般是default)中出现。示例：
<br/>
Within a switch block, each statement group either terminates abruptly (with a break, continue, return or thrown exception), or is marked with a comment to indicate that execution will or might continue into the next statement group. Any comment that communicates the idea of fall-through is sufficient (typically // fall through). This special comment is not required in the last statement group of the switch block. Example:

```java
switch (input) {
  case 1:
  case 2:
    prepareOneOrTwo();
    // fall through
  case 3:
    handleOneTwoOrThree();
    break;
  default:
    handleLargeNumber(input);
}
```
***注意：在`case 1:`后面不需要添加注释，仅仅添加在整个语句组的结束处***<br/>
*Notice that no comment is needed after case 1:, only at the end of the statement group.*

#### 4.8.4.3 default的情况要写出来 *(The default case is present)*

每个switch语句都包含一个`default`语句组，即使它什么代码也不包含。<br/>
Each switch statement includes a default statement group, even if it contains no code.

***例外：一个使用枚举类型的switch可以忽略掉default语句组，前提是把所有可能性列举出来了。如果缺少覆盖其中一种可能性会引起编译器或其他静态分析的报错***<br/>
*Exception: A switch statement for an enum type may omit the default statement group, if it includes explicit cases covering all possible values of that type. This enables IDEs or other static analysis tools to issue a warning if any cases were missed.*

### 4.8.5 注解 *(Annotations)*

注解紧跟在文档块后面，应用于类、方法和构造函数，一个注解独占一行。这些换行不属于自动换行(第4.5节，自动换行)，因此缩进级别不变。例如：<br/>

*Annotations applying to a class, method or constructor appear immediately after the documentation block, and each annotation is listed on a line of its own (that is, one annotation per line). These line breaks do not constitute line-wrapping (Section 4.5, Line-wrapping), so the indentation level is not increased. Example:*

```java
@Override
@Nullable
public String getNameIfPresent() { ... }
```

例外：单个的注解可以和签名的第一行出现在同一行。例如：<br/>
*Exception: A single parameterless annotation may instead appear together with the first line of the signature, for example:*

```java
@Override public int hashCode() { ... }
```

应用于字段的注解紧随文档块出现，应用于字段的多个注解允许与字段出现在同一行。例如：<br/>
*Annotations applying to a field also appear immediately after the documentation block, but in this case, multiple annotations (possibly parameterized) may be listed on the same line; for example:*

```java
@Partial @Mock DataLoader loader;
```
参数和局部变量注解没有特定规则。<br/>
There are no specific rules for formatting annotations on parameters, local variables, or types.

4.8.6 注释 *(Comments)*

may be preceded by arbitrary whitespace followed by an implementation comment. Such a comment renders the line non-blank.

4.8.6.1 Block comment style
块注释与其周围的代码在同一缩进级别。它们可以是`/* ... */`风格，也可以是`// ...风格。对于多行的/* ... */注释，后续行必须从*开始， 并且与前一行的*对齐。以下示例注释都是OK的。`
<br/>
*Block comments are indented at the same level as the surrounding code. They may be in /* ... */ style or // ... style. For multi-line /* ... */ comments, subsequent lines must start with * aligned with the * on the previous line.*

```java
/*
 * This is          // And so           /* Or you can
 * okay.            // is this.          * even do this. */
 */
```
注释不要封闭在由星号或其它字符绘制的框架里。<br/>
*Comments are not enclosed in boxes drawn with asterisks or other characters.*

在写多行注释时，如果你希望在必要时能重新换行(即注释像段落风格一样)，那么使用`/* ... */`。<br/>
*Tip: When writing multi-line comments, use the /* ... */ style if you want automatic code formatters to re-wrap the lines when necessary (paragraph-style). Most formatters don't re-wrap lines in // ... style comment blocks.*

### 4.8.7 修饰符 *(Modifiers)*
类和成员的modifiers如果存在，则按Java语言规范中推荐的顺序出现。<br/>
*Class and member modifiers, when present, appear in the order recommended by the Java Language Specification:*<br/>

```java
public protected private abstract default static final transient volatile synchronized native strictfp
```

### 4.8.8 数字文字 *(Numeric Literals)*
当表示long类型数值时，尽量选用`L`而非`l`,如：`3000000000L`<br/>
long-valued integer literals use an uppercase L suffix, never lowercase (to avoid confusion with the digit 1). For example, 3000000000L rather than 3000000000l.

# 5 命名 *(Naming)*
## 5.1 5.1 对所有标识符都通用的规则 *(Rules common to all identifiers)*
标识符只能使用ASCII字母和数字，因此每个有效的标识符名称都能匹配正则表达式`\w+`。<br/>
*Identifiers use only ASCII letters and digits, and, in a small number of cases noted below, underscores. Thus each valid identifier name is matched by the regular expression \w+ .*<br/>

在Google其它编程语言风格中使用的特殊前缀或后缀，如name_,
 mName, s_name和kName，在Java编程风格中都不再使用。<br/>
*In Google Style special prefixes or suffixes, like those seen in the examples name_, mName, s_name and kName, are not used.*
