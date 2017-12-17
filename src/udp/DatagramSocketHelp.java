package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
//���ݱ�������
public class DatagramSocketHelp extends DatagramSocket {
	static final int MAX_LEN = 100;
	DatagramSocketHelp(int portNo) throws SocketException {
		super(portNo);
		// TODO Auto-generated constructor stub
	}

	//������Ϣ����
	public void sendMessage(InetAddress receiverHost,int receiverPort,String message) throws IOException {
		byte[] sendBuffer = message.getBytes();
		DatagramPacket datagram = new DatagramPacket(sendBuffer, sendBuffer.length, receiverHost, receiverPort);
		this.send(datagram);
	}
	
	//������Ϣ����
	public String receiveMessage() throws IOException {
		byte[] receiverBuffer = new byte[MAX_LEN];
		DatagramPacket datagram = new DatagramPacket(receiverBuffer, receiverBuffer.length);
		this.receive(datagram);
		String message = new String(receiverBuffer);
		return message;
		
		
	}
}
