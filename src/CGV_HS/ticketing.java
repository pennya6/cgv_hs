package CGV_HS;

/*
 * ticketing : ���� ��ü ���� Ŭ����
 */

public class ticketing {
	String ticketingno;
	String memberno;
	String movieno;
	String theaterno;
   String screentheatername; //�󿵰��̸�
   java.sql.Time screentime; //�󿵽ð�
   java.sql.Date screendate; //�󿵳�¥
   String ticketingseats; //�¼�
   String reviewstory; //���䳻��
   int grade; //����
   int price; //����
   String pay; //�������


   public ticketing() {
	   
   }
   
   //������Ȳ��ȸ 
   public ticketing(String ticketingno,String theaterno,String screentheatername, java.sql.Time screentime, java.sql.Date screendate, String ticketingseats, int price, String pay) {
	   this.ticketingno=ticketingno;
	   //this.memberno=memberno;
	   //this.movieno=movieno;
	this.theaterno=theaterno;
	   this.screentheatername = screentheatername;
	   this.screentime = new java.sql.Time(screentime.getTime());
	   this.screendate = new java.sql.Date(screendate.getDate());
	   this.ticketingseats = ticketingseats;
	   this.price = price;
	   this.pay = pay;
	}
   
   //���������� -> �� ������ Ȯ���Ҷ�
	public ticketing(String ticketingno,String memberno,String movieno,String theaterno,String screentheatername, java.sql.Time screentime, java.sql.Date screendate, String ticketingseats, String reviewstroy, int grade, int price, String pay) {
	   this.ticketingno=ticketingno;
	   this.memberno=memberno;
	   this.movieno=movieno;
		this.theaterno=theaterno;
		this.screentheatername = screentheatername;
	   this.screentime = new java.sql.Time(screentime.getTime());
	   this.screendate = new java.sql.Date(screendate.getDate());
	   this.ticketingseats = ticketingseats;
	   this.reviewstory = reviewstory;
	   this.grade = grade;
	   this.price = price;
	   this.pay = pay;
	}
	
	
	//Ƽ���� ���� ���
	public void output() {
	   System.out.print(" �󿵰�: " + screentheatername + ",  ");
	   System.out.print("����ð�: " + screentime +",  ");
	   System.out.print("���೯¥: " + screendate +",   ");
	   System.out.print("�����¼�: " + ticketingseats + ", ");
	   System.out.print("   ����: " + reviewstory + ", ");
	   System.out.print("   ����: " + grade + ", ");
	   System.out.print("   ����: " + price + ", ");
	   System.out.print("�������: " + pay + ", ");
	}
	
	public void review_output() {
		System.out.print("   ����: " + reviewstory + ", ");
	}
}