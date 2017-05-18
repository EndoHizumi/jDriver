package jp.wsf.jDriver.test;


import static jp.wsf.jDriver.asserts.AssertResult.*;

import java.util.ArrayList;

import jp.wsf.jDriver.annotations.Test;
import jp.wsf.jDriver.core.jDriver;
;

public class TestassertInArrayList {

public static void main(String[] args) {
	jDriver jd = new jDriver();
	jd.execute();
}

@Test
public void TestassertInArray(){
	String expect = "hoge";
	ArrayList<String> actuals = new ArrayList<String>();
	actuals.add("foo");
	actuals.add("var");
	actuals.add("hoge");
	assertInList(expect,actuals);
}
}
