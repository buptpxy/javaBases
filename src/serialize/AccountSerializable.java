package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



//���Զ�������л��ͷ����л�

//Account��ʵ��java.io.Serializable �ӿ�,ֻ��ʵ��������ӿڵ���Ķ�����ܱ����л�
class Account implements Serializable{
	private String name;
	private double balance;
	public Account(String name,double balance){
		this.name = name;
		this.balance = balance;
	}
	public String toString(){
		return "name:" + name + ",balance:" + balance;
	}
}

public class AccountSerializable {

	public static void main(String[] args) throws Exception{
		// ���������ļ�������
		File f = new File("objectFile.obj");//objectFile.obj ��ʾ������л�������ļ�
		
		//���л�����
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));//����һ�����������
		Account account1 = new Account("zhang3", 1000);
		Account account2 = new Account("Li4", 2000);
		out.writeObject(account1);//ͨ�����������д���󣬰�Java����ת��Ϊ�ֽ�����
		out.writeObject(account2);
		out.close();
		
		//�����л�����
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));//����һ������������
		Account obj1 = (Account) in.readObject();//ͨ��������������ȡ���󣬽��ֽ����лָ�ΪJava����
		System.out.println("account1���� = " + obj1);
		Account obj2 = (Account) in.readObject();
		System.out.println("account2���� = " + obj2);
		in.close();
	}

}
