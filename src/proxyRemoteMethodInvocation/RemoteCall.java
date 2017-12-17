package proxyRemoteMethodInvocation;

import java.io.Serializable;

//这个类采用序列化机制实现serializable接口，把要发送的信息用RemoteCall这个类来封装
public class RemoteCall implements Serializable {

	private String className;//类名或接口名
	private String methodName;//方法名
	private Class[] paramTypes;//方法参数类型
	private Object[] params; //方法参数值
	
	//如果方法正常执行，则result为方法返回值；如果方法抛出异常，则result为该异常
	private static Object result;
	public RemoteCall(){}
	public RemoteCall(String className,String methodName,Class[] paramTypes,Object[] params) {
		this.className = className;
		this.methodName = methodName;
		this.params = params;
		this.paramTypes = paramTypes;
	}
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class[] getParamTypes() {
		return paramTypes;
	}
	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public static Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		RemoteCall.result = result;
	}
	
	public String  toString() {
		return "className = " +className + " methodName = " + methodName;
	}

}
