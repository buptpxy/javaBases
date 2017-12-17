package udp;

import java.net.InetAddress;

//���շ�����
public class UDPConnectionReceiverSender {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length !=4)
			System.out.println("�����в��������ͷ�IP��ַ�����ͷ��˿ڣ����շ��˿ڣ������ַ���");
		else {
			try {
				InetAddress receiverHost = InetAddress.getByName(args[0]);
				int receiverPort = Integer.parseInt(args[1]);
				int myPort = Integer.parseInt(args[2]);
				String message = args[3];
				
				//�������ݱ����ڷ��ͺͽ�������
				DatagramSocketHelp mySocket = new DatagramSocketHelp(myPort);
				
				//�������ݱ�
				System.out.println(mySocket.receiveMessage());
				
				//�������ݱ�
				mySocket.sendMessage(receiverHost, receiverPort, message);
				
				mySocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}