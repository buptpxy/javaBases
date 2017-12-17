package proxyRemoteMethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//����һ��AccountService�Ķ�̬�����࣬����������һ��ʵ�����������classType��ȡֵΪAccountService.class����ôgetProxy()�����ʹ���AccountService��̬�����ʵ��
public class DynamicProxyFactory {
	public static Object getProxy( final Class classType,final String host,final int port) {//final���ε��಻�ܱ��̳�,��final���εı�������һ��������ֻ�ܸ�ֵһ��
		InvocationHandler handler = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Exception {
				// �˲����뾲̬���������ƣ�����û�и�����ȷ��ί����
				Connector connector = null;
				try {
					connector = new Connector(host, port);
					RemoteCall call = new RemoteCall(classType.getName(),method.getName(),method.getParameterTypes(),args);
					connector.send(call);
					call = (RemoteCall) connector.receive();
					Object result = call.getResult();
					return result;
				} finally {
					if(connector!=null)
						connector.close();
				}
				
			}
		};
		return Proxy.newProxyInstance(classType.getClassLoader(), new Class[]{classType}, handler);
	}
}
