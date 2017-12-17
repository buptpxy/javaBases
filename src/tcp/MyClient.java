package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

	static Socket server;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length!=2) {
			System.out.println("用法：MyClient <主机名><端口号>");
			return;
		}
		
		//获取本地IP地址，访问在本地的服务程序，默认端口号是1234
		Socket server = new Socket(args[0], Integer.parseInt(args[1]));//Integer是 Int的包装类
		
		//建立连接并打开相关联的输入流和输出流
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));//BufferedReader 由Reader类扩展而来，提供通用的缓冲方式文本读取，而且提供了很实用的readLine，读取一个文本行，从字符输入流中读取文本，缓冲各个字符，从而提供字符、数组和行的高效读取。
		PrintWriter out = new PrintWriter(server.getOutputStream());
		BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));//用来从键盘接受一行输入的代码
		//InputStreamReader类是字节流通向字符流的桥梁,封裝了InputStream在里头, 它以较高级的方式,一次读取一个一个字符，以文本格式输入 / 输出，可以指定编码格式；
		
		//将控制台输入的字符串发送给服务端，如果接收到“end”,则退出程序
		while (true) {
			String str = wt.readLine();
			out.println(str);//out.println()输出到客户端,System.out.println()打印在控制台当中
			out.flush();//flush一般主要用在IO中，即清空缓冲区数据
			if (str.equals("end")) {
				System.out.println("通信已经终止");
				break;
			}
			System.out.println(in.readLine());
			//out.println("客户端已收到请求：" + in.readLine());
		}
		
		//关闭连接
		wt.close();
		out.close();
		in.close();
		server.close();

	}

}
