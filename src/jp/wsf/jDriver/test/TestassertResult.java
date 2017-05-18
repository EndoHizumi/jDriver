package jp.wsf.jDriver.test;
import static jp.wsf.jDriver.asserts.AssertResult.*;
import jp.wsf.jDriver.core.jDriver;
public class TestassertResult {

public static void main(String[] args) {
	Throwable t = new Throwable();
	jDriver jd = new jDriver();
	jd.execute(t.getStackTrace()[0].getClassName());
}

public void TestAssertResult(){
	String expect = "hoge";
	String actuals = "hoge";
	assertResult(expect,actuals);
}
}
