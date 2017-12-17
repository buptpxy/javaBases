package exception;

class OutOfMoney extends Exception {
	
	public OutOfMoney() {
		// TODO Auto-generated constructor stub
		//�������쳣��һ��Ĭ�ϵ��ַ�������
		super("Your account have not enough money!");//��JAVA����ʹ��super�����ø���ĳɷ֣���this�����õ�ǰ�������һ���������һ����̳У�����new��������ʵ�������ʱ�������������������һ�����������ôȥ��������ĸ�������أ�ʹ��super�����ã�thisָ���ǵ�ǰ��������ã�super�ǵ�ǰ��������ĸ���������á�
	}
	public OutOfMoney(String msg){
		//����ʹ�������и������쳣��һ���ַ�������
		super(msg);
	}
	
}

class Account {
	private double balance = 1000;//balance-��amount-ת��Ǯ��
	public void transfer(double amount) throws OutOfMoney{//�ڷ����������׳��쳣
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
				exc.printStackTrace();//��ӡ������������쳣����ʱ�ĵ���ջ
			} finally {
				System.out.println("finally �����е�������ǻ�ִ�У�");
			}
			amount = amount - 300;
		}
	}

}
