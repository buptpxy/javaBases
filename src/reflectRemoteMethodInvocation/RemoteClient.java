package reflectRemoteMethodInvocation;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
/*客户端程序，想要调用server端的AccountServiceImpl对象中的getAccount()方法
1.创建一个请求对象RemoteCall，它包含了AccountService接口的getAccount()方法
2.通过对象输出流把call对象发送给服务器
3.从输入流中读取call对象*/
public class RemoteClient {

	public void invoke() throws Exception {
		Socket socket = new Socket("localhost", 8000);
		OutputStream out = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		InputStream in = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(in);
		RemoteCall call = new RemoteCall("AccountService", "getAccount", new Class[]{String.class}, new Object[]{"Zhang3"});
		
		//向服务器发送call对象
		oos.writeObject(call);
		
		//接收包含了方法执行结果的call对象
		call = (RemoteCall) ois.readObject();
		System.out.println(call.getResult());
		ois.close();
		oos.close();
		socket.close();
		
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		new RemoteClient().invoke();
	}

}
