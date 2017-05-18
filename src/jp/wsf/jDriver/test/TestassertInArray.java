package jp.wsf.jDriver.test;
import jp.wsf.jDriver.core.jDriver;

import static jp.wsf.jDriver.asserts.AssertResult.*;
public class TestassertInArray {
	
public static void main(String[] args) {
	jDriver jd = new jDriver();
	jd.execute("jp.wsf.jDriver.sample.TestassertInArray");
}

public void testassertInArray(){
	String expect = "hoge";
	String[] actuals = {"foo","var","hoge"};
	assertInArray(expect,actuals);
}
}
