package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class EchoThread extends Thread{
	 Socket socket;
	 EchoThread(Socket s){
		socket = s;
	}
	public void run() {
		System.out.println("����Ϊ�ͻ������ṩ����" + socket);
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);	
			String str;
			System.out.println("�ͻ����Ѿ���������");
			while ((str = in.readLine()) != null) {
				
				System.out.println(socket + "�յ�����" + str);
				out.println("��������յ�����" + str);
				out.flush();
				if (str.equals("end")) {
					System.out.println("ͨ������ֹ");
					break;
				}
			}

			// �ر�����			
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

public class MultiThreadServer {	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		if (args.length !=1) {
			System.out.println("�÷���MutiServer <�˿ں�>");
			return;
		}
		ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println("����������ڼ����˿ڣ�" + args[0]);
		for (;;) {
			new EchoThread(ss.accept()).start();
		}
	}
}
	


