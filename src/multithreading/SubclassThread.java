package multithreading;

public class SubclassThread extends Thread {

	public void run() {
		while (true) {
			//ִ���߳����������
			try {
				sleep(5*1000);//˯��5s���������������"���������"�����������Խ�����5s�Ž���
				break;
			} catch (InterruptedException exc) {
				// ˯�߱��ж�
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new SubclassThread();//���ü̳�Thread����߳��ഴ��һ�����߳�ʱ����ֱ�Ӵ������̵߳�һ��ʵ��
		thread.start();
		System.out.println("���������");
	}

}
