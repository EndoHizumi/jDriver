package jp.wsf.jDriver.test;

import static jp.wsf.jDriver.asserts.AssertResult.assertResult;
import static jp.wsf.jDriver.result.ResultCount.fail;
import jp.wsf.jDriver.annotations.Ignore;
import jp.wsf.jDriver.annotations.Test;
import jp.wsf.jDriver.core.jDriver;


public class TestAnnotation {
	public static void main(String[] args) {
		jDriver jd = new jDriver();
		jd.execute();
	}
	
	@Test
	public void hoge() {
		assertResult(true);
	}
	
	@Test(ExpectExceptions={IllegalArgumentException.class})
	public void hoge2(){
		ExpectExceptionTest exTest = new ExpectExceptionTest();
		exTest.isPassed(-1);
	}
	
	@Ignore
	public void testIgnoreAnnotation(){
		fail("Ignore Annotaion not Working!!");
	}
	
}
