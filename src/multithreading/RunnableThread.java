package multithreading;
//ͨ��ʵ��Runnable�ӿڶ������߳�
class Counter implements Runnable{
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("������= " + i);
		}
	}
}

public class RunnableThread {
	public static void main(String[] args) {
		Counter counter = new Counter();//���һ��ʵ����Runnable�ӿڵ��߳���󣬿ɴ������߳����һ��ʵ��
		Thread thread = new Thread(counter);//�Ը�ʵ��Ϊ��������Thread���һ��ʵ��
		thread.start();//���ø�ʵ����start�������Ӷ�����һ�����߳�
		System.out.println("���������");
	}
}
