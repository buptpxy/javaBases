package udp;

import java.net.InetAddress;

//���ͽ�����
public class UDPConnectionSenderReceiver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length !=4)
			System.out.println("�����в��������շ�IP��ַ�����շ��˿ڣ����ͷ��˿ڣ������ַ���");
		else {
			try {
				InetAddress receiverHost = InetAddress.getByName(args[0]);
				int receiverPort = Integer.parseInt(args[1]);
				int myPort = Integer.parseInt(args[2]);
				String message = args[3];
				
				//�������ݱ����ڷ��ͺͽ�������
				DatagramSocketHelp mySocket = new DatagramSocketHelp(myPort);
				
				//�������ݱ�
				mySocket.sendMessage(receiverHost, receiverPort, message);
				
				//�ȴ���������
				System.out.println(mySocket.receiveMessage());
				mySocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
