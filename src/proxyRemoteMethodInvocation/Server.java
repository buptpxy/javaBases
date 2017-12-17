/**
 * ����˳���
 * 1.ͨ����������ȡcall���󣬲�����ڻ�����
 * 2.���÷�����Ƶ���AccountService�����getAccount()����
 * 3.��getAccount()������ִ�н�����浽call������
 * 4.ͨ��������Ѱ����˷���ִ�н����call�����͸��ͻ���
 */
package proxyRemoteMethodInvocation;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;



public class Server {
	//���Զ�̶���Ļ���
	private Map remoteObjects = new HashMap();
	
	//��һ��Զ�̶���ŵ�������
	public void register(String className,Object remoteObject) {
		remoteObjects.put(className,remoteObject);
	}
	
	//���÷�����ƻ��Զ�̶���������з���
	private RemoteCall invoke(RemoteCall call) {
		Object result = null;
		try {
			String className = call.getClassName();
			String methodName = call.getMethodName();
			Object[] params = call.getParams();
			Class classType = Class.forName(className);//
			Class[] paramTypes = call.getParamTypes();
			Method method = classType.getMethod(methodName, paramTypes);
					
			//�ӻ�����ȡ����ص�Զ�̶���
			Object remoteObject = remoteObjects.get(className);
			if(remoteObject==null){
				throw new Exception(className + "��Զ�̶��󲻴���");
			}else {
				result = method.invoke(remoteObject, params);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result = e;
		}
		call.setResult(result);	
		return call;
	}
	
	public void service() throws Exception {
		//������������socket������8000�˿ڼ���
		ServerSocket serverSocket = new ServerSocket(8000);
		System.out.println("����������������������");
		while (true) {
			Socket socket = serverSocket.accept();//����
			InputStream in = socket.getInputStream();//��ô�������������
			ObjectInputStream ois = new ObjectInputStream(in);//��������������
			
			OutputStream out = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);//�������������
			
			//���տͻ����͵�call����
			RemoteCall remoteCallobj = (RemoteCall) ois.readObject();//�ѽ��յ�����remotecall��װ���ֽ����б�ɶ���
			System.out.println(remoteCallobj);
			
			//������ض���ķ���
			remoteCallobj = invoke(remoteCallobj);
			
			//��ͻ����Ͱ�����ִ�н����remotecallobj����
			oos.writeObject(remoteCallobj);//�Ѷ������ֽ�������
		
			ois.close();
			oos.close();
			socket.close();
		}
	}
	

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Server server = new Server();
		
		//�����ȴ�����AccountServiceImpl������뵽�������Ļ�����
		server.register("AccountService", new AccountServiceImpl());
		server.service();
	}

}
