package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



//测试对象的序列化和反序列化

//Account类实现java.io.Serializable 接口,只有实现了这个接口的类的对象才能被序列化
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
		// 创建本地文件输入流
		File f = new File("objectFile.obj");//objectFile.obj 表示存放序列化对象的文件
		
		//序列化对象
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));//创建一个对象输出流
		Account account1 = new Account("zhang3", 1000);
		Account account2 = new Account("Li4", 2000);
		out.writeObject(account1);//通过对象输出流写对象，把Java对象转换为字节序列
		out.writeObject(account2);
		out.close();
		
		//反序列化对象
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));//创建一个对象输入流
		Account obj1 = (Account) in.readObject();//通过对象输入流读取对象，将字节序列恢复为Java对象
		System.out.println("account1对象 = " + obj1);
		Account obj2 = (Account) in.readObject();
		System.out.println("account2对象 = " + obj2);
		in.close();
	}

}
