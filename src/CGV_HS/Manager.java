package CGV_HS;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Vector;

//extends 확인
public class Manager extends member {
	
	public Manager() {
		
	}
		
	public Manager(String memberno, String ID, String PW, java.sql.Date birth, String phoneno, String name) {
		super(memberno, ID, PW, birth, phoneno, name);
	}
	
	// 매니저 업무 처리 메소드
	public void doManagerWork() {
		
		Manager m = new Manager();
		
		
		int menu = 0;
		
		while(true)
		{
			System.out.println("관리자 시스템");
			
			System.out.println("1.회원 관리"); //회원 리스트 출력, 회원 삭제
			System.out.println("2.극장 관리"); //극장 리스트 출력, 극장  수정 삭제
			System.out.println("3.상영관 관리"); // 상영관 리스트 출력, 상영관 추가 수정 삭제
			System.out.println("4.영화 관리"); // 영화 리스트 출력, 영화 추가 수정 삭제
			System.out.println("5.상영 관리"); // 상영 리스트 출력, 상영 추가 삭제
			System.out.println("6.예매 관리"); // 고객 예매 리스트 출력
			System.out.println("7.패스트 오더"); // 고객 패스트 오더 리스트 출력
			System.out.println("8.후기 관리"); // 후기 리스트 출력, 후기 삭제
			System.out.println("9. 종료");
			System.out.println("\n 원하시는 작업을 선택하세요.");
			
			String menuSelection = SkScanner.getString();
			
			try {
				switch(menuSelection) {
				case"1":
					this.customerManage();
					break;
				case"2":
					this.theaterManage();
					break;
				case"3":
					this.screentheaterManage();
					break;
				case"4":
					this.movieManage();
					break;
				case"5":
					this.screenManage();
					break;
				case"6":
					this.ticketingManage();
					break;
				case"7":
					this.fastorderManage();
					break;
				case"8":
					this.reviewManage();
					break;
				case"9":
					System.out.println("종료합니다.");
					break;
				default:
					System.out.println("잘못된 입력입니다.");
				}
			}
			catch (Exception e) {
				System.err.println("\n"+ e.getMessage());
			}
		}
	}
	
	//매니저 업무 메소드
	
	//회원 관리
	public static void customerManage() {
		
		System.out.println("회원 관리");
		
		Vector members = DB.getAllmember(); 
		for(int i=0; i<members.size(); i++) {
			((member) members.elementAt(i)).output();
		}
		//회원 삭제
		System.out.print("  삭제하실 회원 넘버를 선택하세요 "); //물어보기
		String memberno = SkScanner.getString();
		
		DB.memberDelete(memberno); //DB 필요
		
	}	
	
	//극장 관리
	public static void  theaterManage() {
		System.out.println("극장 관리");
		
		Vector theaters = DB.getAlltheater(); //DB필요 
		for(int i=0; i<theaters.size(); i++) {
			((theater) theaters.elementAt(i)).output();
		}
		
		//극장 삭제
		System.out.print("  삭제하실 극장을 선택하세요 "); //물어보기
		String theaterno = SkScanner.getString();
		
		DB.theaterDelete(theaterno); //DB 필요
	}
	
	//상영관 관리
	public static void screentheaterManage() {
		System.out.println("상영관 관리");
		
		Vector screentheaters = DB.getAllscreentheater();
		for(int i=0; i<screentheaters.size(); i++) {
			((screentheater) screentheaters.elementAt(i)).output();
		}
		
		int choice=0;
		System.out.println("1. 상영관 삭제");
		System.out.println("2. 상영관 추가");
		System.out.println("진행할 업무를 선택하세요");
		
		if(choice==0) {
			//상영관 삭제
			System.out.print("  삭제하실 상영관을 선택하세요 "); //물어보기
			String screentheatername = SkScanner.getString();
			System.out.print("  삭제하실 상영관의 극장을 선택하세요 "); //물어보기
			String theatername = SkScanner.getString();
			
			DB.screentheaternameDelete(screentheatername,theatername); //DB 필요
		}
		else {
			//상영관 추가
			System.out.println("상영관 이름");
			String screenSelection = SkScanner.getString();
			System.out.println("영화관 선택");
			String theaterSelection = SkScanner.getString();
			int screentheaterseats=42;
			
			screentheater newmanageScreenTheater = new screentheater(screenSelection,theaterSelection,screentheaterseats);
			DB.insertscreentheater(newmanageScreenTheater); 
		}	
	}
	
	
	//영화 관리
	public static void movieManage() {
		System.out.println("영화 관리");
		
		Vector movies = DB.getAllmovie(); // DB필요
		
		for(int i=0; i<movies.size(); i++) {
			((movie) movies.elementAt(i)).output();
		}
		
		int choice=0;
		System.out.println("1. 영화삭제");
		System.out.println("2. 영화추가");
		System.out.println("진행할 업무를 선택하세요");
		
		if(choice==1) {
			//영화 삭제
			System.out.print("  삭제하실 영화를 선택하세요 "); //물어보기
			String movieno = SkScanner.getString();
			
			DB.movieDelete(movieno); //DB 필요
		}
		else {
			//영화 추가
			System.out.println("영화 제목");
			String titleSelection = SkScanner.getString();
			
			System.out.println("장르");
			String genreSelection = SkScanner.getString();
			
			System.out.println("개봉일"); //변환 메소드 필요. customer.java 154줄
			String year = SkScanner.getString();
			
			year = (year.length() == 1) ? "000" + year : (year.length() == 2) ? "00" + year : (year.length() == 3) ? "0" + year : year;
			
			String month = SkScanner.getString("월");
			String date = SkScanner.getString("일");
			
			java.sql.Date dateSelection = Date.valueOf(year + "-" + month + "-" +  date);
			System.out.println("\n  * 선택 날짜 = " + dateSelection);	
			
			System.out.println("줄거리");
			String storySelection = SkScanner.getString();
			
			System.out.println("러닝 타임");
			int runtimeSelection = SkScanner.getInt();
			
			System.out.println("영화번호");
			String movienoSelection=SkScanner.getString();
			
			System.out.println("평균평점");
			double movieavgGradeSelection=SkScanner.getDouble();
			
			System.out.println("리뷰수");
			int movieCntsReviewSelection=SkScanner.getInt();
			
			movie newmanageMovie = new movie(movienoSelection,titleSelection, genreSelection, dateSelection, runtimeSelection, storySelection, movieavgGradeSelection, movieCntsReviewSelection);
			
			DB.insertmovie(newmanageMovie); //DB필요
			
		}
	
	}
	
	//상영 관리
	public static void screenManage() {
		System.out.println("상영 관리");
		
		Vector screens = DB.getAllscreen();
		for(int i=0; i<screens.size(); i++) {
			((screen) screens.elementAt(i)).output();
		}
		
		int choice=0;
		System.out.println("1. 상영삭제");
		System.out.println("2. 상영추가");
		System.out.println("진행할 업무를 선택하세요");
		
		if(choice==1) {
			//상영 삭제
			System.out.print("  삭제하실 상영 정보를 선택하세요 "); //물어보기
			String screen = SkScanner.getString();
			
			System.out.println("영화관 선택");
			String theaterSelection2 = SkScanner.getString();
			System.out.println("상영관 이름");  //상영관 추가에 있는 코드와 같아도 될까?
			String screentheaterSelection2 = SkScanner.getString();
			
			System.out.println("상영 날짜");
			String year2 = SkScanner.getString();
			 
			 
			 year2 = (year2.length() == 1) ? "000" + year2 : (year2.length() == 2) ? "00" + year2 : (year2.length() == 3) ? "0" + year2 : year2;
				
				String month2 = SkScanner.getString("월");
				String date2 = SkScanner.getString("일");
				
				java.sql.Date dateSelection2 = Date.valueOf(year2 + "-" + month2 + "-" +  date2);
				
				System.out.println("상영 시간");
				
				String hour2 = SkScanner.getString("  시  ");
				String minute2 = SkScanner.getString("  분  ");
				
				java.sql.Time timeSelection2 = Time.valueOf(hour2 + ":" + minute2 + "00");  // 만약 초가 입력되지 않으면 "00"을 반드시 추가해야 함
			
			DB.screenDelete(dateSelection2,timeSelection2,theaterSelection2,screentheaterSelection2); //DB 필요
		}
		
		else {
			//상영 추가
			System.out.println("영화관 선택");
			String theaterSelection = SkScanner.getString();
			System.out.println("상영관 이름");  //상영관 추가에 있는 코드와 같아도 될까?
			String screentheaterSelection = SkScanner.getString();
			System.out.println("영화 선택");
			String titleSelection = SkScanner.getString();
			System.out.println("상영 날짜");
			String year = SkScanner.getString();
			
			year = (year.length() == 1) ? "000" + year : (year.length() == 2) ? "00" + year : (year.length() == 3) ? "0" + year : year;
			
			String month = SkScanner.getString("월");
			String date = SkScanner.getString("일");
			
			java.sql.Date dateSelection = Date.valueOf(year + "-" + month + "-" +  date);
			System.out.println("\n  * 선택 날짜 = " + dateSelection);
			
			System.out.println("상영 시간");
			
			String hour = SkScanner.getString("  시  ");
			String minute = SkScanner.getString("  분  ");
			
			java.sql.Time timeSelection = Time.valueOf(hour + ":" + minute + "00");  // 만약 초가 입력되지 않으면 "00"을 반드시 추가해야 함

			System.out.println("\n  * 선택 시간 = " + timeSelection);
			
			System.out.println("회차");
			int screenroundSelection = SkScanner.getInt();
			
			//DB.addscreenround(s, titleSelection);
			
			screen newmanagerScreen = new screen(dateSelection,timeSelection,titleSelection,theaterSelection,screentheaterSelection,screenroundSelection);
			DB.insertscreen(newmanagerScreen); //DB필요
		}
		
		
		
	}
	
	//예매 관리
	public static void ticketingManage() {
		
		System.out.println("예매 관리");
		
		Vector ticketings = DB.getticketing(); //DB필요
		
		for(int i=0; i<ticketings.size(); i++) {
			((ticketing) ticketings.elementAt(i)).output();
		}
	}
	
	//패스트 오더 관리
	public static void fastorderManage() {
		System.out.println("패스트 오더 관리");
		
		Vector fastorders = DB.getfastorder(); //DB필요
		
		for(int i=0; i<fastorders.size(); i++) {
			((fastorder) fastorders.elementAt(i)).output();
		}
	}
	
	//리뷰 관리
	public static void reviewManage() {
		System.out.println("리뷰 관리");
		
		Vector reviews = DB.getAllreview();
		for(int i=0; i<reviews.size(); i++) {
			((ticketing) reviews.elementAt(i)).output(); //DB필요
		}
		System.out.print("  삭제하실 리뷰의 예매번호를 선택하세요 "); //물어보기
		String review_number = SkScanner.getString();
		
		DB.reivewDelete(review_number); //DB 필요
	}
	
}


