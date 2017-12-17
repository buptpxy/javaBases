package proxyRemoteMethodInvocation;

import reflectRemoteMethodInvocation.AccountService;
//客户端通过AccountService的代理类StaticServiceProxy来调用服务器端的AccountServiceImpl对象的方法
//对客户端而言，与远程服务器通信的细节被封装到代理类StaticServiceProxy中，简化了客户端的编程
public class ProxyClient {

	public static void main(String[] args) throws Exception {
		// 创建静态代理类实例
		StaticServiceProxy service1 = new StaticServiceProxy("localhost", 8000);	
		System.out.println(service1.getAccount("Zhang3"));
		
		//创建动态代理实例
		AccountService service2 = (AccountService) DynamicProxyFactory.getProxy(AccountService.class, "localhost", 8000);
		System.out.println(service2.getAccount("Li4"));
	}

}
