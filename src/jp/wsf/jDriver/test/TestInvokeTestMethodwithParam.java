package jp.wsf.jDriver.test;

import jp.wsf.jDriver.annotations.ParameterSource;
import jp.wsf.jDriver.annotations.Test;
import jp.wsf.jDriver.core.jDriver;
import static jp.wsf.jDriver.asserts.AssertResult.*;

public class TestInvokeTestMethodwithParam {
	
		public static void main(String[] args) {
			jDriver jd = new jDriver();
			jd.execute();
		}
	
		@ParameterSource(name = "testTwo-params")
		public Object testParams() {
			Object[][] params = { { "hello", " new world","hello new world" }, { "114", "514","114514" }, { "�䂪����", "�t���������������I�I","�䂪���̏t���������������I�I" }, };
			return params;
		}

		@Test(ParameterSource = "testTwo-params")
		public void testTwo(String a, String b,String except) {
			String actual= a + b;
			assertResult(except,actual);
		}
}
