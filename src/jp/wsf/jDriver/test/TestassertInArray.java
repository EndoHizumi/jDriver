package jp.wsf.jDriver.test;
import static jp.wsf.jDriver.asserts.AssertResult.*;

import jp.wsf.jDriver.annotations.Test;
import jp.wsf.jDriver.core.jDriver;
public class TestassertInArray {

public static void main(String[] args) {
	jDriver jd = new jDriver();
	jd.execute();
}

@Test
public void testassertInArray(){
	String expect = "hoge";
	String[] actuals = {"foo","var","hoge"};
	assertInArray(expect,actuals);
}
}
