package jp.wsf.jDriver.method;

import java.lang.reflect.Method;

public class ParamCollecter {
	private Class<?> targetClass = null;

	public ParamCollecter(Class<?> target) {
		targetClass = target;
	}

	public Class<?>[] getParamTypes(String MethodName) throws SecurityException, NoSuchMethodException{
		Method[] target = targetClass.getDeclaredMethods();
		Class<?>[] params = new Class[0];
		for(Method item:target){
			if(item.getName().equals(MethodName)){
				params =  item.getParameterTypes();
			}
		}
		return params;
	}

	public static Class<?>[] getParamerterCount(Method target) {
		Class<?>[] params = new Class[0];
		params =  target.getParameterTypes();
		return params;
	}



}
