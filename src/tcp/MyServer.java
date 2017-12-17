package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//简单的利用流式套接字实现通信的服务器程序
public class MyServer {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		if (args.length!=1) {
			System.out.println("用法：MyServer <端口号>");
			return;
		}
		
		// 创建serverSocket 实例，建立连接
		ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println("服务程序正在监听端口：" + args[0]);

		// 监听客户程序的连接请求
		Socket client = server.accept();// 调用accept()方法会引起阻塞，直到有一个客户程序发送连接请求到服务器监听的窗口
		BufferedReader in = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		PrintWriter out = new PrintWriter(client.getOutputStream());
		//BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));//用来从键盘接受一行输入的代码
		// 从客户端读取数据，并输出到屏幕上，如果接收到"end"，则退出程序
		String str;
		System.out.println("客户端已经建立连接");
		//Scanner scanner = new Scanner(System.in);

		while ((str = in.readLine()) != null) {
			System.out.println(str);
			System.out.println("收到请求：" + str);
			out.println("服务端已收到请求：" + str);
			//out.println(wt.readLine());
			out.flush();
			if (str.equals("end")) {
				System.out.println("通信已终止");
				break;
			}
			//String nickname = scanner.next();
		}

		// 关闭连接
		//wt.close();
		out.close();
		in.close();
		client.close();
		server.close();
	}
}
