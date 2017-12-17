package udp;

import java.net.InetAddress;

//接收返回类
public class UDPConnectionReceiverSender {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length !=4)
			System.out.println("命令行参数：发送方IP地址，发送方端口，接收方端口，发送字符串");
		else {
			try {
				InetAddress receiverHost = InetAddress.getByName(args[0]);
				int receiverPort = Integer.parseInt(args[1]);
				int myPort = Integer.parseInt(args[2]);
				String message = args[3];
				
				//产生数据报用于发送和接收数据
				DatagramSocketHelp mySocket = new DatagramSocketHelp(myPort);
				
				//接收数据报
				System.out.println(mySocket.receiveMessage());
				
				//返回数据报
				mySocket.sendMessage(receiverHost, receiverPort, message);
				
				mySocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
