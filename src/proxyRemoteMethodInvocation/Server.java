/**
 * 服务端程序
 * 1.通过输入流读取call对象，并存放在缓存中
 * 2.运用反射机制调用AccountService对象的getAccount()方法
 * 3.把getAccount()方法的执行结果保存到call对象中
 * 4.通过输出流把包含了方法执行结果的call对象发送给客户端
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
	//存放远程对象的缓存
	private Map remoteObjects = new HashMap();
	
	//把一个远程对象放到缓存中
	public void register(String className,Object remoteObject) {
		remoteObjects.put(className,remoteObject);
	}
	
	//利用反射机制获得远程对象里的所有方法
	private RemoteCall invoke(RemoteCall call) {
		Object result = null;
		try {
			String className = call.getClassName();
			String methodName = call.getMethodName();
			Object[] params = call.getParams();
			Class classType = Class.forName(className);//
			Class[] paramTypes = call.getParamTypes();
			Method method = classType.getMethod(methodName, paramTypes);
					
			//从缓存中取出相关的远程对象
			Object remoteObject = remoteObjects.get(className);
			if(remoteObject==null){
				throw new Exception(className + "的远程对象不存在");
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
		//创建基于流的socket，并在8000端口监听
		ServerSocket serverSocket = new ServerSocket(8000);
		System.out.println("服务器启动。。。。。。");
		while (true) {
			Socket socket = serverSocket.accept();//阻塞
			InputStream in = socket.getInputStream();//获得传过来的输入流
			ObjectInputStream ois = new ObjectInputStream(in);//创建对象输入流
			
			OutputStream out = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);//创建对象输出流
			
			//接收客户发送的call对象
			RemoteCall remoteCallobj = (RemoteCall) ois.readObject();//把接收到的由remotecall封装的字节序列变成对象
			System.out.println(remoteCallobj);
			
			//调用相关对象的方法
			remoteCallobj = invoke(remoteCallobj);
			
			//向客户发送包含了执行结果的remotecallobj对象
			oos.writeObject(remoteCallobj);//把对象变成字节流传输
		
			ois.close();
			oos.close();
			socket.close();
		}
	}
	

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Server server = new Server();
		
		//把事先创建的AccountServiceImpl对象加入到服务器的缓存中
		server.register("AccountService", new AccountServiceImpl());
		server.service();
	}

}
