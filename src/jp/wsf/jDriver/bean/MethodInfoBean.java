package jp.wsf.jDriver.bean;

/**
 * テスト対象のメソッドに関する情報のBeanです。
 * @author shunsuke kikuchi
 *
 */
public class MethodInfoBean {
	private String methodName = "";
	private String paramerterSourceName = "";
	private Class[] expectExceptions = new Class[0];
	private boolean isParam = false;
	private String beforeMethodName = "";
	private String afterMethodName = "";
	
	public Class[] getExpectExceptions() {
		return expectExceptions;
	}
	public void setExpectExceptions(Class[] expectExceptions) {
		this.expectExceptions = expectExceptions;
	}
	public boolean isParam() {
		return isParam;
	}
	public void setParam(boolean isPatam) {
		this.isParam = isPatam;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getParamerterSourceName() {
		return paramerterSourceName;
	}
	public void setParamerterSourceName(String paramerterSource) {
		this.paramerterSourceName = paramerterSource;
	}
	public String getBeforeMethodName() {
		return beforeMethodName;
	}
	public void setBeforeMethodName(String beforeMethodName) {
		this.beforeMethodName = beforeMethodName;
	}
	public String getAfterMethodName() {
		return afterMethodName;
	}
	public void setAfterMethodName(String afterMethodName) {
		this.afterMethodName = afterMethodName;
	}
}
