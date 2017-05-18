package jp.wsf.jDriver.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jp.wsf.jDriver.bean.MethodInfoBean;
import jp.wsf.jDriver.exception.TestExecuteException;
import jp.wsf.jDriver.result.ResultCount;

/**
 * @author shunsuke kikuchi
 *
 */
public class Invoker {
	
	// ���ݎ��s���̃e�X�g���\�b�h���
	private MethodInfoBean invoke = null;
	// �e�X�g�N���X
	private Class obj = null;
	// �e�X�g�N���X���̑Ώۃe�X�g���\�b�h��񃊃X�g
	private MethodInfoBean[] MethodInfoList = new MethodInfoBean[0];

	public Invoker(MethodInfoDTO targetMethods) throws ClassNotFoundException, TestExecuteException {
		super();
		this.obj = Class.forName(targetMethods.getTargetClassName());
		MethodInfoList = targetMethods.getMethodInfoList();
		if(MethodInfoList.length <= 0){
			throw new TestExecuteException("testClass Not Found.");
		}
	}
	
	
	/**
	 * �e�X�g���\�b�h�����s���܂��B
	 * @throws Exception
	 */
	public void execute()throws Exception{
		ResultCount.printResult(obj.getName(), "Test start");
		for(MethodInfoBean item:MethodInfoList){
			String methodName = item.getMethodName(); 
			String paramSourcesName = item.getParamerterSourceName();
			invoke = item;
			if( item.isParam()){
				ParamCollecter paramCollecter = new ParamCollecter(obj);
				InvokeWithParam(methodName,paramSourcesName,paramCollecter.getParamTypes(methodName));
				ResultCount.addCountInvoke();
			}else{
				Invoke(methodName);
				ResultCount.addCountInvoke();
			}
		}
	}
	
	/**
	 * �����̂Ȃ����\�b�h�����s���܂��B
	 * @param MethodName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException 
	 */
	private Object Invoke(String MethodName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Object actual = null;
		Constructor constructer = obj.getDeclaredConstructor();
		constructer.setAccessible(true);
		Object targetClass = constructer.newInstance();
		Method method = obj.getDeclaredMethod(MethodName);
		method.setAccessible(true);
		ResultCount.printResult(method.getName(), "\nInvoke");
		actual = method.invoke(targetClass);
		return actual;
	}
	
	/**
	 * �����������\�b�h�����s���܂��B
	 * @param MethodName
	 * @param paramSource
	 * @param params
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException 
	 */
	private Object InvokeWithParam(String MethodName, String paramSource,Class[] params) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, InstantiationException {
		Object actual = null;
		Object targetClass = obj.newInstance();
		Method method = obj.getDeclaredMethod(MethodName,params);
		method.setAccessible(true);
		ResultCount.printResult(method.getName(), "Invoke");
		actual = method.invoke(targetClass);
		return actual;
	}
	
	/**
	 * Invoke method from param[Target].
	 * 
	 * @param MethodName
	 * @param Target
	 * @return actual
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object InvokePrivate(String MethodName, Object Target)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		Object actual = null;
		Class<?> targetClass = Target.getClass();
		Method method = targetClass.getDeclaredMethod(MethodName);
		method.setAccessible(true);
		actual = method.invoke(Target);
		return actual;
	}

	/**
	 * Invoke method from param[Target].
	 * 
	 * @param MethodName
	 * @param Target
	 * @param args
	 * @return actual
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object InvokePrivate(String MethodName, Object Target,
			String... args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		Object actual = null;
		Object[] argsArray = args;
		Class<?> targetClass = Target.getClass();
		Method method = targetClass.getDeclaredMethod(MethodName,
				String.class);
		method.setAccessible(true);
		actual = method.invoke(Target, argsArray);
		return actual;
	}

	/**
	 * Invoke method from param[Target].
	 * 
	 * @param MethodName
	 * @param Target
	 * @param args
	 * @return actual
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object InvokePrivate(String MethodName, Object Target,
			Integer... args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		Object actual = null;
		Object[] argsArray = args;
		Class<?> targetClass = Target.getClass();
		Method method = targetClass.getDeclaredMethod(MethodName,
				Integer.class);
		method.setAccessible(true);
		actual = method.invoke(Target, argsArray);
		return actual;
	}

	/**
	 * Invoke method from param[Target].
	 * 
	 * @param MethodName
	 * @param Target
	 * @param args
	 * @return actual
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object InvokePrivate(String MethodName, Object Target,
			Boolean... args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		Object actual = null;
		Object[] argsArray = args;
		Class<?> targetClass = Target.getClass();
		Method method = targetClass.getDeclaredMethod(MethodName,
				Boolean.class);
		method.setAccessible(true);
		actual = method.invoke(Target, argsArray);
		return actual;
	}

	/**
	 * Invoke method from param[Target].
	 * 
	 * @param MethodName
	 * @param Target
	 * @param args
	 * @return actual
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object InvokePrivate(String MethodName, Object Target,
			Double... args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		Object actual = null;
		Object[] argsArray = args;
		Class<?> targetClass = Target.getClass();
		Method method = targetClass.getDeclaredMethod(MethodName,
				Double.class);
		method.setAccessible(true);
		actual = method.invoke(Target, argsArray);
		return actual;
	}

	/**
	 * Invoke method from param[Target].
	 * 
	 * @param MethodName
	 * @param Target
	 * @param args
	 * @return actual
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object InvokePrivate(String MethodName, Object Target, Float... args)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		Object actual = null;
		Object[] argsArray = args;
		Class<?> targetClass = Target.getClass();
		Method method = targetClass
				.getDeclaredMethod(MethodName, Float.class);
		method.setAccessible(true);
		actual = method.invoke(Target, argsArray);
		return actual;
	}
	
	/**
	 * Invoke method from param[Target].
	 * 
	 * @param MethodName
	 * @param Target
	 * @param args
	 * @return actual
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object InvokePrivate(String MethodName, Object Target,
			Long... args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		Object actual = null;
		Object[] argsArray = args;
		Class<?> targetClass = Target.getClass();
		Method method = targetClass.getDeclaredMethod(MethodName,
				Long.class);
		method.setAccessible(true);
		actual = method.invoke(Target, argsArray);
		return actual;
	}
	
	/**
	 * Invoke method from param[Target].
	 * 
	 * @param MethodName
	 * @param Target
	 * @param args
	 * @return actual
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object InvokePrivate(String MethodName, Object Target,
			Object... args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		
		Object actual = null;
		Object[] argsArray = args;
		Class<?> targetClass = Target.getClass();
		Method method = targetClass.getDeclaredMethod(MethodName,
				Object.class);
		method.setAccessible(true);
		actual = method.invoke(Target, argsArray);
		return actual;
		
	}
	
	/**
	 * Invoke method from param[Target].
	 * 
	 * @param MethodName
	 * @param Target
	 * @param args
	 * @return actual
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object InvokePrivate(String MethodName, Object Target,
			char args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		Object actual = null;
		char argsArray = args;
		Class<?> targetClass = Target.getClass();
		Method method = targetClass.getDeclaredMethod(MethodName,
				char.class);
		method.setAccessible(true);
		actual = method.invoke(Target, argsArray);
		return actual;
	}

	public MethodInfoBean getInvokeMethod() {
		return invoke;
	}
}
