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
		System.out.println("正在为客户程序提供服务：" + socket);
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);	
			String str;
			System.out.println("客户端已经建立连接");
			while ((str = in.readLine()) != null) {
				
				System.out.println(socket + "收到请求：" + str);
				out.println("服务端已收到请求：" + str);
				out.flush();
				if (str.equals("end")) {
					System.out.println("通信已终止");
					break;
				}
			}

			// 关闭连接			
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
			System.out.println("用法：MutiServer <端口号>");
			return;
		}
		ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println("服务程序正在监听端口：" + args[0]);
		for (;;) {
			new EchoThread(ss.accept()).start();
		}
	}
}
	


