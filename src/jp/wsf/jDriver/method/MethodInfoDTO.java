package jp.wsf.jDriver.method;

import jp.wsf.jDriver.bean.MethodInfoBean;

public class MethodInfoDTO {
	private String targetClassName = "";
	private MethodInfoBean[] MethodInfoList = new MethodInfoBean[0];
	private String targetClassSimpleName = "";
	
	public MethodInfoBean[] getMethodInfoList() {
		return MethodInfoList;
	}
	public void setMethodInfoList(MethodInfoBean[] methodInfoList) {
		MethodInfoList = methodInfoList;
	}
	public String getTargetClassName() {
		return targetClassName;
	}
	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}
	public String getTargetClassSimpleName() {
		return targetClassSimpleName;
	}
	public void setTargetClassSimpleName(String targetClassSimpleName) {
		this.targetClassSimpleName = targetClassSimpleName;
	}
	
}
