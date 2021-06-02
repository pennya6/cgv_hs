package CGV_HS;

/*
 * ticketing : 예매 객체 선언 클래스
 */

public class ticketing {
	String ticketingno;
	String memberno;
	String movieno;
	String theaterno;
   String screentheatername; //상영관이름
   java.sql.Time screentime; //상영시간
   java.sql.Date screendate; //상영날짜
   String ticketingseats; //좌석
   String reviewstory; //리뷰내용
   int grade; //평점
   int price; //가격
   String pay; //결제방법


   public ticketing() {
	   
   }
   
   //예매현황조회 
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
   
   //마이페이지 -> 다 끝내고 확인할때
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
	
	
	//티켓팅 정보 출력
	public void output() {
	   System.out.print(" 상영관: " + screentheatername + ",  ");
	   System.out.print("예약시간: " + screentime +",  ");
	   System.out.print("예약날짜: " + screendate +",   ");
	   System.out.print("예약좌석: " + ticketingseats + ", ");
	   System.out.print("   리뷰: " + reviewstory + ", ");
	   System.out.print("   평점: " + grade + ", ");
	   System.out.print("   가격: " + price + ", ");
	   System.out.print("결제방법: " + pay + ", ");
	}
	
	public void review_output() {
		System.out.print("   리뷰: " + reviewstory + ", ");
	}
}