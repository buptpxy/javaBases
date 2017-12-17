package exception;

class OutOfMoney extends Exception {
	
	public OutOfMoney() {
		// TODO Auto-generated constructor stub
		//给出该异常的一个默认的字符串描述
		super("Your account have not enough money!");//在JAVA类中使用super来引用父类的成分，用this来引用当前对象，如果一个类从另外一个类继承，我们new这个子类的实例对象的时候，这个子类对象里面会有一个父类对象。怎么去引用里面的父类对象呢？使用super来引用，this指的是当前对象的引用，super是当前对象里面的父对象的引用。
	}
	public OutOfMoney(String msg){
		//允许使用者自行给出对异常的一个字符串描述
		super(msg);
	}
	
}

class Account {
	private double balance = 1000;//balance-余额，amount-转账钱数
	public void transfer(double amount) throws OutOfMoney{//在方法中声明抛出异常
		if(balance<amount)
			throw new OutOfMoney("[Balance:" + balance + " < Amount:" + amount + "]");
		balance = balance - amount;
	}
	public double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
}

public class AccountException {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account obj = new Account();
		double amount = 800;
		for (int count=0; count<3; count++){
			try {
				obj.transfer(amount);
				System.out.println("Transfer amount: " + amount + ",and then balance: " + obj.getBalance());
			} catch (OutOfMoney exc) {
				// TODO: handle exception
				exc.printStackTrace();//打印创建所捕获的异常对象时的调用栈
			} finally {
				System.out.println("finally 语句块中的语句总是会执行！");
			}
			amount = amount - 300;
		}
	}

}
