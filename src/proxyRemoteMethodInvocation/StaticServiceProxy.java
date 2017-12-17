package proxyRemoteMethodInvocation;

/*静态代理类，实现AccountService接口
代理类与委托类具有相同的接口；
代理类主要负责为委托类预处理消息、过滤消息、把消息传递给委托类，事后处理消息
一个代理类的对象与一个委托类的对象相关联，
代理类的对象本身并不真正实现服务，而是通过调用委托类的对象的相关方法来提供特定的服务
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
