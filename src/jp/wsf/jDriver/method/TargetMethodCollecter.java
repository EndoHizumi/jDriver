package jp.wsf.jDriver.method;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import jp.wsf.jDriver.annotations.Ignore;
import jp.wsf.jDriver.annotations.ParameterSource;
import jp.wsf.jDriver.annotations.Test;
import jp.wsf.jDriver.bean.MethodInfoBean;

public class TargetMethodCollecter {

	/**
	 * 渡されたテストクラスに属するテストメソッドの情報を収集し、返します。
	 * @param className
	 * @return MethodInfoDTO
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static MethodInfoDTO getTestTargetMethods(String targetClassName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (targetClassName == null || targetClassName.trim().length() == 0) return null;
		Class<?> c = Class.forName(targetClassName);
		return getTestTargetMethods(c);
	}

	/**
	 * 渡されたテストクラスに属するテストメソッドの情報を収集し、返します。
	 * @param className
	 * @return MethodInfoDTO
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static MethodInfoDTO getTestTargetMethods(Class<?> targetClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return getTestTargetMethods(targetClass.newInstance());
	}

	/**
	 * 渡されたテストクラスに属するテストメソッドの情報を収集し、返します。
	 * @param className
	 * @return MethodInfoDTO
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static MethodInfoDTO getTestTargetMethods(Object targetClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (targetClass == null) return null;
		Object obj = targetClass;
		Method[] methodList = obj.getClass().getDeclaredMethods();
		ArrayList<MethodInfoBean> targetMethods = new ArrayList<MethodInfoBean>();
		HashMap<String,String> parameterSourceNames = new HashMap<String,String>();

		 //parameterSourceを収集する
		for (Method m : methodList) {
			ParameterSource paramSource = m.getAnnotation(ParameterSource.class);
			if (paramSource != null){
				parameterSourceNames.put(paramSource.name(),m.getName());
			}
		}

		for (Method m : methodList) {
			// ignoreアノテ―ションが付いているメソッドは除外する。
			if (m.getAnnotation(Ignore.class) != null){
				continue;
			}

			// 実行するテストメソッドを収集する。
			Test testAnnotation = m.getAnnotation(Test.class);
			// Testアノテ―ションが付いているメソッドが対象
			if (testAnnotation != null) {
				MethodInfoBean methodinfo = new MethodInfoBean();
				methodinfo.setMethodName(m.getName());
				methodinfo.setExpectExceptions(getExpectExceptions(m));
				methodinfo.setParam(isParameter(m));
				if(methodinfo.isParam()){
					methodinfo.setParamerterSourceName((parameterSourceNames.get(testAnnotation.ParameterSource())));
				}
				targetMethods.add(methodinfo);
			}
		}
		// 各メソッドの情報とそれを有するクラス名を格納
		MethodInfoDTO methodInfoDto = new MethodInfoDTO();
		methodInfoDto.setMethodInfoList(targetMethods.toArray(new MethodInfoBean[0]));
		methodInfoDto.setTargetClassName(targetClass.getClass().getName());
		methodInfoDto.setTargetClassSimpleName(targetClass.getClass().getSimpleName());

		return methodInfoDto;
	}

	private static Class<?>[] getExpectExceptions(Method target) {
		if (target == null) return null;
		if (target.getAnnotations() == null || target.getAnnotations().length == 0) return null;
		Test testAnnotation = target.getAnnotation(Test.class);
		if(testAnnotation == null)return null;
		Class<?>[] classes = testAnnotation.ExpectExceptions();
		ArrayList<Class<?>> expectExceptions = new ArrayList<Class<?>>();
		for (Class<?> item : classes) {
			if (item.getSimpleName().endsWith("Exception")) {
				expectExceptions.add(item);
			}
		}
		return expectExceptions.toArray(new Class[0]);
	}


	private static boolean isParameter(Method target) {
		return ParamCollecter.getParamerterCount(target).length > 0 ? true : false;
	}
}
