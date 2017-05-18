package jp.wsf.jDriver.method;

import java.lang.reflect.Method;

public class ParamCollecter {
	private Class targetClass = null;
	
	public ParamCollecter(Class target) {
		targetClass = target;
	}
	
	public Class[] getParamTypes(String MethodName) throws SecurityException, NoSuchMethodException{
		Method target = targetClass.getDeclaredMethod(MethodName);
		return target.getParameterTypes();
	}
	
	
}
