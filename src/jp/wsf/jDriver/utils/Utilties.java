package jp.wsf.jDriver.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utilties {
	public static String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	/**
	 * sourceObjectからtargetBeanNameで指定されたJavaBeansを取得します。
	 * JavaBeansの任意getterを実行するのに用います。
	 * 
	 * @param targetBeanName
	 * @param sourceObject
	 * @return Object 取得したJavaBeans
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object fetchBean(String targetBeanName, Object sourceObject)
			throws NullPointerException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Object bean = null;
		PropertyDescriptor targetBean = null;
		try {
			targetBean = new PropertyDescriptor(targetBeanName, sourceObject
					.getClass());

		} catch (IntrospectionException e) {
			try {
				targetBean = new PropertyDescriptor(targetBeanName + "List",
						sourceObject.getClass());
			} catch (IntrospectionException e1) {
				e1.printStackTrace();
			}
		}
		bean = targetBean.getReadMethod().invoke(sourceObject, (Object[]) null);
		return bean;
	}

	/**
	 * sourceObjectからtargetBeanNameで指定されたオブジェクトを取得します。
	 * 非JavaBeansの任意getterを実行するのに用います。
	 * 
	 * @param String
	 *            targetObjectName
	 * @param Object
	 *            sourceObject
	 * @return 実行したgetterの戻り値
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 */
	public static Object fetchContainar(String targetObjectName, Object sourceObject)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException,
			NoSuchMethodException, ClassNotFoundException {
		// 継承されたメソッドに目的のBeanを返すGetterが定義されている場合があるので、getMethodでメソッド情報を取得する
		Method method = sourceObject.getClass().getMethod(
				"get" + capitalize(targetObjectName));
		Object Containar = method.invoke(sourceObject);
		return Containar;
	}
	
	public  String getTargetClassName() {
		StackTraceElement[] stacktraces = new Throwable().getStackTrace();
		String ClassName = "";
		
		for (StackTraceElement st : stacktraces) {
			if (isMyname(st.getClassName()) == false) {
				ClassName = st.getClassName();
			}
		}
		return ClassName;
	}
	
	private  boolean isMyname(String ClassName) {
		return ClassName.equals(this.getClass().getName());
	}	
	
}
