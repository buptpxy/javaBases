package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Account{
	private String name;
	private int balance;
	public Account(){};
	public Account(String name,int balance){
		this.name = name;
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}

public class AccountReflect {

	public Object copy(Object object) throws Exception {
		//获得对象的类型
		Class classType = object.getClass();
		System.out.println("Class: " + classType.getName());
		
		//通过原来类的构造方法实例化一个新的对象
		Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
		
		//获得对象的所有属性
		Field fields[] = classType.getDeclaredFields();//得到原来类的所有成员变量
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			
			//获得和属性对应的getXXX()和setXXX()方法的名字
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			String setMethodName = "set" + firstLetter + fieldName.substring(1);
			
			//获得和属性对应的getXXX()和setXXX()方法，并设置参数类型
			Method getMethod = classType.getMethod(getMethodName, new Class[]{});
			Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});
			
			//调用原对象的getXXX()方法
			Object value = getMethod.invoke(object, new Object[]{});//得到getMethod这个方法返回的参数值，invoke()把方法参数化
			System.out.println(fieldName + " : " + value);
			
			//调用复制对象的setXXX()方法
			setMethod.invoke(objectCopy, new Object[]{value});//把得到的参数值给setMethod传进去，这样复制的对象也有了原对象的属性的值
		}
		return objectCopy;
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Account account = new Account("Zhang3",1000);
		Account accountCopy = (Account) new AccountReflect().copy(account);//实例化一个新account对象，它的所有属性都是复制的原对象中的属性
		System.out.println("Copy information : " + accountCopy.getName() + " " + accountCopy.getBalance());
	}

}
