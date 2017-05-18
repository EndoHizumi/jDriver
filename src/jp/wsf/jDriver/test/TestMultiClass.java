package jp.wsf.jDriver.test;

import jp.wsf.jDriver.core.jDriver;

public class TestMultiClass {
	public static void main(String[] args) {
		jDriver jd = new jDriver();
		jd.execute(InvokePrivateMethod.class);
		jd.execute(InvokePrivateMethodwithParam.class);
	}
}
