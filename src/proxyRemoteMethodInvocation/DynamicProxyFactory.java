package proxyRemoteMethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//创建一个AccountService的动态代理类，并返回它的一个实例。如果参数classType的取值为AccountService.class，那么getProxy()方法就创建AccountService动态代理的实例
public class DynamicProxyFactory {
	public static Object getProxy( final Class classType,final String host,final int port) {//final修饰的类不能被继承,被final修饰的变量就是一个常量，只能赋值一次
		InvocationHandler handler = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Exception {
				// 此步骤与静态代理相类似，但是没有给出明确的委托类
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
