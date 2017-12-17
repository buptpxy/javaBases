package multithreading;

public class SubclassThread extends Thread {

	public void run() {
		while (true) {
			//执行线程自身的任务
			try {
				sleep(5*1000);//睡眠5s，即在主程序输出"主程序结束"后，整个程序仍将持续5s才结束
				break;
			} catch (InterruptedException exc) {
				// 睡眠被中断
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new SubclassThread();//利用继承Thread类的线程类创建一个新线程时，可直接创建该线程的一个实例
		thread.start();
		System.out.println("主程序结束");
	}

}
