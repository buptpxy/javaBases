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
			System.out.println("�÷���MyClient <������><�˿ں�>");
			return;
		}
		
		//��ȡ����IP��ַ�������ڱ��صķ������Ĭ�϶˿ں���1234
		Socket server = new Socket(args[0], Integer.parseInt(args[1]));//Integer�� Int�İ�װ��
		
		//�������Ӳ�����������������������
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));//BufferedReader ��Reader����չ�������ṩͨ�õĻ��巽ʽ�ı���ȡ�������ṩ�˺�ʵ�õ�readLine����ȡһ���ı��У����ַ��������ж�ȡ�ı�����������ַ����Ӷ��ṩ�ַ���������еĸ�Ч��ȡ��
		PrintWriter out = new PrintWriter(server.getOutputStream());
		BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));//�����Ӽ��̽���һ������Ĵ���
		//InputStreamReader�����ֽ���ͨ���ַ���������,���b��InputStream����ͷ, ���Խϸ߼��ķ�ʽ,һ�ζ�ȡһ��һ���ַ������ı���ʽ���� / ���������ָ�������ʽ��
		
		//������̨������ַ������͸�����ˣ�������յ���end��,���˳�����
		while (true) {
			String str = wt.readLine();
			out.println(str);//out.println()������ͻ���,System.out.println()��ӡ�ڿ���̨����
			out.flush();//flushһ����Ҫ����IO�У�����ջ���������
			if (str.equals("end")) {
				System.out.println("ͨ���Ѿ���ֹ");
				break;
			}
			System.out.println(in.readLine());
			//out.println("�ͻ������յ�����" + in.readLine());
		}
		
		//�ر�����
		wt.close();
		out.close();
		in.close();
		server.close();

	}

}
