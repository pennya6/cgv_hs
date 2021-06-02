package CGV_HS;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.SimpleTimeZone;
import java.util.Vector;


public class Customer  extends member{
	
	public Customer() {
	}
	
	//일반 고객 객체 생성자
	public Customer(String memberno, String ID, String PW,java.sql.Date birth, String phoneno, String name ) {
		super(memberno, ID,PW,birth,phoneno,name);
	}
	
	// 고객 업무 처리 메소드
	public void doCustomerWork() {
		
		Customer c=new Customer();
		
		int menu = 0;
		
		while(true)
		{
			System.out.println("\n\n <<영화 예매 시스템>>");
			//System.out.println("   고객 ID: " + getID() + "\n");
			System.out.println("   1. 영화 조회"); // 영화 조회(리뷰 포함) 
			System.out.println("   2. 영화 예매"); // 영화, 극장, 날짜, 시간 , 연령(가격), 좌석  ( + 연령 나이 : 8000, 12000)
			System.out.println("   3. 패스트오더 "); 
			System.out.println("   4. 마이페이지"); // 1.영화 예매 현황  (리뷰작성)
												// 2.패스트오더 현황 3.리뷰 조회 및 작성
			System.out.println("   5. 종료");
			System.out.println("\n  * 원하시는 작업을 선택하세요 > ");
			
			String menuSelection = SkScanner.getString();

			try {
					switch(menuSelection) {
					case"1":
						this.movielistview();
						break;
					case"2":
						this.openticketing();
						break;
					case"3":
						this.fastorder(c);
						break;
					case"4":
						this.mypage(c); 
						break;
					case"5":
						System.out.println("종료입니다.");
						break;
					default:
						System.out.println("잘못된 입력입니다.");
				}
			}
			catch (Exception e) {
				System.err.println("\n "+ e.getMessage());
			}	
	}
}
	
	
	//고객 업무 메소드
	
	//영화 목록
	public void movielistview() {
		System.out.println("\n 영화 조회");
		
		Vector movies = DB.getAllmovie(); //DB구현
		for(int i=0; i<movies.size(); i++) {
			((movie) movies.elementAt(i)).output();
		}
	}
	
	
	//영화 예매
	public void openticketing() {
			
			//영화 정보 나열
			movielistview();
			
			System.out.println("예매할 영화를 선택해주세요.");	
			String movieSelection = SkScanner.getString();
		
			DB.choicemovie_after_other(movieSelection);
			
			//선택한 영화에 대한 극장 정보 나열
			System.out.println("극장을 선택해 주세요");
			String theaterSelection = SkScanner.getString();
			
			System.out.println("상영관을 선택해주세요");
			String screentheaterSelection=SkScanner.getString();
			
			//선택한 영화, 극장에 대한 날짜정보 나열
			System.out.println("날짜를 선택해주세요");
			
			String year=SkScanner.getString();
			// year String 값은 4자리 디지트 문자가 되어야 하므로 4자리 로 변환 
			year = (year.length() == 1) ? "000" + year : (year.length() == 2) ? "00" + year : (year.length() == 3) ? "0" + year : year;
			
			String month = SkScanner.getString("  o 월 > ");
			String date = SkScanner.getString("  o 일 > ");
			
			java.sql.Date dateSelection = Date.valueOf(year + "-" + month + "-" +  date);
			
			System.out.println("\n  * 선택 날짜 = " + dateSelection);	
			//String dateSelection=SkScanner.getString();
			
			
			// 선택한 영화, 극장에 대한 시간 정보 입력하여 java.sql.Time 객체로 변환하기
			System.out.println("시간을 선택해주세요");
			
			String hour = SkScanner.getString("  o 시 > ");
			String minute = SkScanner.getString("  o 분 > ");
			
			java.sql.Time timeSelection = Time.valueOf(hour + ":" + minute + "00");  // 만약 초가 입력되지 않으면 "00"을 반드시 추가해야 함

			System.out.println("\n  * 선택 시간 = " + timeSelection);	
			
			
			System.out.println("나이를 선택해주세요");
			int ageSelection=SkScanner.getInt();
			int price;
				//연령에 따른 요금 변환
				if(ageSelection<=19) {
					price=8000;
				}
				else price=12000;
			
				
			System.out.println("결제 방법을 선택해주세요");
			String paySelection=SkScanner.getString();
			
			System.out.println("좌석을 선택해주세요");
			String seatSelection=SkScanner.getString();
			
			ticketing newticketing=new ticketing(movieSelection,theaterSelection,screentheaterSelection,timeSelection,dateSelection,seatSelection,price,paySelection);
			DB.insertticketing(newticketing);
			
	}
	
	// 문자열 날짜 정보가 주어지면 java.sql.Date 객체로 변환해 반환하는 메소드
		static java.sql.Date transformDate(String year, String month, String date) {
			// year String 값은 4자리 디지트 문자가 되어야 하므로 4자리 로 변환 
			year = (year.length() == 1) ? "000" + year : (year.length() == 2) ? "00" + year : (year.length() == 3) ? "0" + year : year;

			return java.sql.Date.valueOf(year + "-" + month + "-" + date);
		}

		// 문자열 시간 정보가 주어지면 java.sql.Time 객체로 변환해 반환하는 메소드
		static java.sql.Time transformTime(String hour, String minute, String second) {
			return java.sql.Time.valueOf(hour + ":" + minute + ":" + second);
		}

	
	
	//패스트오더 -> 주문 하는거?
	//조회?
	public static void fastorder(Customer c) {
		int snackChoice = 0;
		//그냥 이름 뽑기
		System.out.println("1. 패스트오더 조회하기"); 
		System.out.println("2. 패스트오더 주문하기");
		
		snackChoice = SkScanner.getInt();
	
		
			switch(snackChoice) {
			case 1:
									
				Vector snacks  = DB.getAllSnack(); //DB 필요
					for(int i=0; i<snacks.size(); i++) {
						((snack) snacks.elementAt(i)).output();
					}		
					
			case 2:
				System.out.println("구매하실 메뉴를 선택해주세요.");
				String snackSelection = SkScanner.getString();
				
				fastorder newfastorder = new fastorder(c.getmemberno(),snackSelection);
				DB.insertfastorder(newfastorder);												
		}
		
  }	

		
	public static void mypage(Customer c) {
		int choice=0;
		String review="";
		System.out.println("원하는 번호를 선택하세요");
		System.out.println("1.영화 예매 현황 및 리뷰작성");
		System.out.println("2.패스트오더 현황 ");
		System.out.println("3.리뷰 조회 ");
		
		choice=SkScanner.getInt();
		
		switch(choice) {
		case 1:
			System.out.println("영화 예매 현황입니다.");
			DB.getticketing(c.getmemberno());
			
			System.out.println("리뷰작성하기");
			System.out.println("예매번호를 입력하세요");
			String ticketingno=SkScanner.getString();
			
			System.out.println("review story 입력하세요");
			String review_story_s=SkScanner.getString();
			System.out.println("grade 입력하세요");
			int grade_s=SkScanner.getInt();
			DB.insertticketing_review(ticketingno,review_story_s,grade_s);
		
			
		case 2:
			System.out.println("패스트오더 현황입니다.");
			DB.getfastorder(c.getmemberno()); 
			
		case 3:
			System.out.println("리뷰조회입니다.");
			System.out.println("리뷰조회");
			
			review=SkScanner.getString();
			
			DB.getAllreview(c.getmemberno());

		}
	}
}




