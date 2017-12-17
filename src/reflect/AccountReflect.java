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
		//��ö��������
		Class classType = object.getClass();
		System.out.println("Class: " + classType.getName());
		
		//ͨ��ԭ����Ĺ��췽��ʵ����һ���µĶ���
		Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
		
		//��ö������������
		Field fields[] = classType.getDeclaredFields();//�õ�ԭ��������г�Ա����
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			
			//��ú����Զ�Ӧ��getXXX()��setXXX()����������
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			String setMethodName = "set" + firstLetter + fieldName.substring(1);
			
			//��ú����Զ�Ӧ��getXXX()��setXXX()�����������ò�������
			Method getMethod = classType.getMethod(getMethodName, new Class[]{});
			Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});
			
			//����ԭ�����getXXX()����
			Object value = getMethod.invoke(object, new Object[]{});//�õ�getMethod����������صĲ���ֵ��invoke()�ѷ���������
			System.out.println(fieldName + " : " + value);
			
			//���ø��ƶ����setXXX()����
			setMethod.invoke(objectCopy, new Object[]{value});//�ѵõ��Ĳ���ֵ��setMethod����ȥ���������ƵĶ���Ҳ����ԭ��������Ե�ֵ
		}
		return objectCopy;
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Account account = new Account("Zhang3",1000);
		Account accountCopy = (Account) new AccountReflect().copy(account);//ʵ����һ����account���������������Զ��Ǹ��Ƶ�ԭ�����е�����
		System.out.println("Copy information : " + accountCopy.getName() + " " + accountCopy.getBalance());
	}

}
