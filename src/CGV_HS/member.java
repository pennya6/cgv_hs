package CGV_HS;

import java.util.Date;


/*
 * member: ��� ������� ǥ���� �� �ִ� ����� Ŭ����
 *    o cgv_hs �����ͺ��̽����� member ���̺�� ��ġ�Ǵ� Ŭ������
 *      member ���̺��� �� ���ð�  member Ŭ������ �� ��ü�� ��ȣ �����ȴ�.
 *    
 *    o �� Ŭ������ Java ���� Ŭ������ �ۼ��Ǿ� ��� �ʵ尡 private �ʵ��̸�
 *      setter �޼ҵ�� getter �޼ҵ尡 �ۼ��Ǿ� ����
 *   
 */

public class member {
	private String memberno;  //ȸ����ȣ
	private String ID;
	private String PW;
	private java.sql.Date birth; //�������
	private String phoneno;
	private String name;
	
	public member() {
		
	}
	
	public member(String memberno, String ID, String PW, Date birth, String phoneno, String name) {
		this.birth=(java.sql.Date) birth;
		this.ID=ID;
		this.memberno=memberno;
		this.name=name;
		this.phoneno=phoneno;
		this.PW=encriptPassword(PW);
	}
	
	//��ι�ȣ ��ȣȭ
	public static String encriptPassword(String password) {
		if (password==null || password.equals(""))
			return "1Ew$H6KhfKHJHG";			
		
		return "" + password.hashCode();  // Java�� hashCode() �޼ҵ带 �̿��� ���� ������ ��ȣȭ
	}
	
	public void encriptPassword() {
		PW = encriptPassword(PW);
	}
	
	//member ���� ���
	public void output() {
		System.out.print("	* ����ȣ :"+memberno+",  ");
		System.out.print("ID : "+ID+",  ");
		System.out.print("PW : "+PW+",  ");
		System.out.print("������� : "+birth+",  ");
		System.out.print("��ȭ��ȣ : "+phoneno+",  ");
		System.out.print("�̸� : "+name+"  ");
	}
	
	
	/*
	 * getter �޼ҵ�� setter �޼ҵ� ����
	 */
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		ID=ID;
	}
	public void setname(String name) {
		this.name=name;
	}
	public String getname() {
		return name;
	}
	public String getphoneno() {
		return phoneno;
	}
	public void setphoneno(String phoneno) {
		this.phoneno=phoneno;
	}
	public void setDatebirth(java.sql.Date birth) {
		this.birth=birth;
	}
	public java.sql.Date getDatebirth(){
		return birth;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String PW) {
		this.PW=encriptPassword(PW);
	}
	public String getmemberno() {
		return memberno;
	}
	public void setmemberno(String memberno) {
		this.memberno=memberno;
	}
	
	public void domemberWork() {};

}
