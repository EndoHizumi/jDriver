# jDriver
Unit testing tool for java. Developing a JUnit to reference.  
Use a Reflection for Java.Private method test is also possible.

JUnitを参考にして、開発中のJava用のユニットテストツールです。  
Javaのリフレクションを、使っているので、Privateなメソッドもテスト可能です。

# Usage

1. Command Line args

  ```
  > java -jar jDriver.jar <Test-Class name>
  ```  

2. Import for your Test Class

  ```
  import jp.wsf.jDriver.core;
  public class TestassertResult {

  public static void main(String[] args) {
  	jDriver jd = new jDriver();
  	jd.execute();
  }

  public void TestAssertResult(){
  	String expect = "hoge";
  	String actuals = "hoge";
  	assertResult(expect,actuals);
  }
  }
  ```
