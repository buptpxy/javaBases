package proxyRemoteMethodInvocation;

import reflectRemoteMethodInvocation.AccountService;
//�ͻ���ͨ��AccountService�Ĵ�����StaticServiceProxy�����÷������˵�AccountServiceImpl����ķ���
//�Կͻ��˶��ԣ���Զ�̷�����ͨ�ŵ�ϸ�ڱ���װ��������StaticServiceProxy�У����˿ͻ��˵ı��
public class ProxyClient {

	public static void main(String[] args) throws Exception {
		// ������̬������ʵ��
		StaticServiceProxy service1 = new StaticServiceProxy("localhost", 8000);	
		System.out.println(service1.getAccount("Zhang3"));
		
		//������̬����ʵ��
		AccountService service2 = (AccountService) DynamicProxyFactory.getProxy(AccountService.class, "localhost", 8000);
		System.out.println(service2.getAccount("Li4"));
	}

}
