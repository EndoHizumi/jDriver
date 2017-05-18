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
	 * �n���ꂽ�e�X�g�N���X�ɑ�����e�X�g���\�b�h�̏������W���A�Ԃ��܂��B
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
	 * �n���ꂽ�e�X�g�N���X�ɑ�����e�X�g���\�b�h�̏������W���A�Ԃ��܂��B
	 * @param className
	 * @return MethodInfoDTO
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static MethodInfoDTO getTestTargetMethods(Class targetClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return getTestTargetMethods(targetClass.newInstance());
	}

	/**
	 * �n���ꂽ�e�X�g�N���X�ɑ�����e�X�g���\�b�h�̏������W���A�Ԃ��܂��B
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
		
		 //parameterSource�����W����
		for (Method m : methodList) {
			ParameterSource paramSource = m.getAnnotation(ParameterSource.class);
			if (paramSource != null){
				parameterSourceNames.put(paramSource.name(),m.getName());
			}
		}
		
		for (Method m : methodList) {
			// ignore�A�m�e�\�V�������t���Ă��郁�\�b�h�͏��O����B
			if (m.getAnnotation(Ignore.class) != null){
				continue;
			}
			
			// ���s����e�X�g���\�b�h�����W����B
			Test testAnnotation = m.getAnnotation(Test.class);
			// Test�A�m�e�\�V�������t���Ă��郁�\�b�h���Ώ�
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
		// �e���\�b�h�̏��Ƃ����L����N���X�����i�[
		MethodInfoDTO methodInfoDto = new MethodInfoDTO();
		methodInfoDto.setMethodInfoList(targetMethods.toArray(new MethodInfoBean[0]));
		methodInfoDto.setTargetClassName(targetClass.getClass().getName());
		methodInfoDto.setTargetClassSimpleName(targetClass.getClass().getSimpleName());
		
		return methodInfoDto;
	}

	private static Class[] getExpectExceptions(Method target) {
		if (target == null) return null;
		if (target.getAnnotations() == null || target.getAnnotations().length == 0) return null;
		Test testAnnotation = target.getAnnotation(Test.class);
		if(testAnnotation == null)return null;
		Class[] classes = testAnnotation.ExpectExceptions();
		ArrayList<Class> expectExceptions = new ArrayList<Class>();
		for (Class item : classes) {
			if (item.getSimpleName().endsWith("Exception")) {
				expectExceptions.add(item);
			}
		}
		return expectExceptions.toArray(new Class[0]);
	}
	

	private static boolean isParameter(Method target) {
		return getParamerterCount(target) > 0 ? true : false;
	}

	private static int getParamerterCount(Method target) {
		return target.getParameterTypes().length;
	}
}
