package reflectRemoteMethodInvocation;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
/*�ͻ��˳�����Ҫ����server�˵�AccountServiceImpl�����е�getAccount()����
1.����һ���������RemoteCall����������AccountService�ӿڵ�getAccount()����
2.ͨ�������������call�����͸�������
3.���������ж�ȡcall����*/
public class RemoteClient {

	public void invoke() throws Exception {
		Socket socket = new Socket("localhost", 8000);
		OutputStream out = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		InputStream in = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(in);
		RemoteCall call = new RemoteCall("AccountService", "getAccount", new Class[]{String.class}, new Object[]{"Zhang3"});
		
		//�����������call����
		oos.writeObject(call);
		
		//���հ����˷���ִ�н����call����
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
