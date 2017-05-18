package jp.wsf.jDriver.test;

import static jp.wsf.jDriver.asserts.AssertResult.*;
import static jp.wsf.jDriver.method.Invoker.*;

import java.lang.reflect.InvocationTargetException;

import jp.wsf.jDriver.core.jDriver;

public class InvokePrivateMethodwithParam {
	public static void main(String[] args) {
		jDriver jd = new jDriver();
		jd.execute();
	}

	public void testSetName() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// set Ai's name,age
		Human Ai = new Human("ai", 16, "Female");
		InvokePrivate("setName", Ai,"you");
		String name = (String) InvokePrivate("getName", Ai);
		assertResult("She is a " + name, "you", name);
	}
}
