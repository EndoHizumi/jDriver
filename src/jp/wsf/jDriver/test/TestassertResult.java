package jp.wsf.jDriver.test;
import static jp.wsf.jDriver.asserts.AssertResult.*;

import jp.wsf.jDriver.annotations.Test;
import jp.wsf.jDriver.core.jDriver;
public class TestassertResult {

public static void main(String[] args) {
	jDriver jd = new jDriver();
	jd.execute();
}
@Test
public void TestAssertResult(){
	String expect = "hoge";
	String actuals = "hoge";
	assertResult(expect,actuals);
}
}
