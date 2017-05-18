package jp.wsf.jDriver.test;

import static jp.wsf.jDriver.asserts.AssertResult.*;

import jp.wsf.jDriver.annotations.ParameterSource;
import jp.wsf.jDriver.annotations.Test;
import jp.wsf.jDriver.core.jDriver;

public class TestInvokeTestMethodwithParam {

		public static void main(String[] args) {
			jDriver jd = new jDriver();
			jd.execute();
		}

		@ParameterSource(name = "testTwo-params")
		public Object testParams() {
			String[][] params = { { "hello", " new world","hello new world" }, { "114", "514","114514" }, { "我が世の", "春が来たぁぁあぁ！！","我が世の春が来たぁぁあぁ！！" }, };
			return params;
		}

		@Test(ParameterSource = "testTwo-params")
		public void testTwo(String a, String b,String except) {
			String actual= a + b;
			assertResult(except,actual);
		}

}
