package proxyRemoteMethodInvocation;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
//��������Զ�̷����������ӣ��Լ����պͷ���socket����
public class Connector {
	private String host;
	private int port;
	private Socket skt;
	private InputStream is;
	private ObjectInputStream ois;
	private OutputStream os;
	private ObjectOutputStream oos;
	
	public Connector(String host,int port) throws Exception {
		this.host = host;
		this.port = port;
		connect(host,port);
	}

	//���Ͷ���
	public void send(Object obj) throws Exception {
		oos.writeObject(obj);
	}
	
	//���ն���
	public Object receive() throws Exception {
		return ois.readObject();
	}
	
	//������Զ�̷�����������(�޲�������)
	public void connect() throws Exception{
		connect(host,port);
	}
	
	//������Զ�̷�����������(�в�������)
	public void connect(String host, int port) throws Exception {
		// TODO Auto-generated method stub
		skt = new Socket(host,port);
		os = skt.getOutputStream();
		oos = new ObjectOutputStream(os);
		is = skt.getInputStream();
		ois = new ObjectInputStream(is);
	}
	
	//�ر�����
	public void close() {
		try {
			ois.close();
			oos.close();
			skt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Connector.close:" + e);
		}
	}
}
