package proxyRemoteMethodInvocation;

/*��̬�����࣬ʵ��AccountService�ӿ�
��������ί���������ͬ�Ľӿڣ�
��������Ҫ����Ϊί����Ԥ������Ϣ��������Ϣ������Ϣ���ݸ�ί���࣬�º�����Ϣ
һ��������Ķ�����һ��ί����Ķ����������
������Ķ�����������ʵ�ַ��񣬶���ͨ������ί����Ķ������ط������ṩ�ض��ķ���
*/
public class StaticServiceProxy implements AccountService {

	private String host;
	private int port;
	
	public StaticServiceProxy(String host,int port) {
		// TODO Auto-generated constructor stub
		this.host = host;
		this.port = port;
	}
	

	public String getAccount(String Name) {
		// TODO Auto-generated method stub
		Connector connector = null;
		try {
			connector = new Connector(host, port);
			RemoteCall call = new RemoteCall("AccountService", "getAccount", new Class[]{String .class}, new Object[]{Name});
			connector.send(call);
			call = (RemoteCall) connector.receive();
			Object result = RemoteCall.getResult();
			return (String) result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			if(connector!=null)
				connector.close();
		}
		return Name;
	}

}
