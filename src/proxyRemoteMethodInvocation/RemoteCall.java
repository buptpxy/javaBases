package proxyRemoteMethodInvocation;

import java.io.Serializable;

//�����������л�����ʵ��serializable�ӿڣ���Ҫ���͵���Ϣ��RemoteCall���������װ
public class RemoteCall implements Serializable {

	private String className;//������ӿ���
	private String methodName;//������
	private Class[] paramTypes;//������������
	private Object[] params; //��������ֵ
	
	//�����������ִ�У���resultΪ��������ֵ����������׳��쳣����resultΪ���쳣
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
