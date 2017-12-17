package multithreading;
//通过实现Runnable接口定义新线程
class Counter implements Runnable{
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("计数器= " + i);
		}
	}
}

public class RunnableThread {
	public static void main(String[] args) {
		Counter counter = new Counter();//完成一个实现了Runnable接口的线程类后，可创建该线程类的一个实例
		Thread thread = new Thread(counter);//以该实例为参数创建Thread类的一个实例
		thread.start();//调用该实例的start方法，从而创建一个新线程
		System.out.println("主程序结束");
	}
}
