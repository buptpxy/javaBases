package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//�򵥵�������ʽ�׽���ʵ��ͨ�ŵķ���������
public class MyServer {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		if (args.length!=1) {
			System.out.println("�÷���MyServer <�˿ں�>");
			return;
		}
		
		// ����serverSocket ʵ������������
		ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println("����������ڼ����˿ڣ�" + args[0]);

		// �����ͻ��������������
		Socket client = server.accept();// ����accept()����������������ֱ����һ���ͻ��������������󵽷����������Ĵ���
		BufferedReader in = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		PrintWriter out = new PrintWriter(client.getOutputStream());
		//BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));//�����Ӽ��̽���һ������Ĵ���
		// �ӿͻ��˶�ȡ���ݣ����������Ļ�ϣ�������յ�"end"�����˳�����
		String str;
		System.out.println("�ͻ����Ѿ���������");
		//Scanner scanner = new Scanner(System.in);

		while ((str = in.readLine()) != null) {
			System.out.println(str);
			System.out.println("�յ�����" + str);
			out.println("��������յ�����" + str);
			//out.println(wt.readLine());
			out.flush();
			if (str.equals("end")) {
				System.out.println("ͨ������ֹ");
				break;
			}
			//String nickname = scanner.next();
		}

		// �ر�����
		//wt.close();
		out.close();
		in.close();
		client.close();
		server.close();
	}
}
