package CGV_HS;

import java.util.*;


/*
 * screen : 상영 객체 선언 클래스
 *  cgv_hs에서 screen 테이블과 매치되는 클래스 
 * 
 * */

public class screen {
	String title; //영화제목
	String theatername; // 극장 이름
	String screentheatername; // 상영관 이름
	int screenround; //상영회차
	java.sql.Time screentime; //상영시간
	java.sql.Date screendate; //상영날짜
	
	
	
	public screen(java.sql.Date screendate, java.sql.Time screentime,String title, 
			String theatername, String screentheatername ,int screenround ) {
		
		this.screendate=new java.sql.Date(screendate.getDate());	
		this.screentime=new java.sql.Time(screentime.getTime());
		this.title=title;
		this.screenround=screenround;
		this.theatername=theatername;
		this.screentheatername=screentheatername;
	}
	
	public screen() {
		// TODO Auto-generated constructor stub
	}

	//상영정보 출력
	public void output() {
		System.out.println("	* 상영  ");
		System.out.print("날짜 : "+screendate +",  ");
		System.out.print("시간 : "+ screentime+",  ");
		System.out.print("영화제목 : "+ title+",  ");
		System.out.print("극장 : "+ theatername+",  ");
		System.out.print("상영관 : "+ screentheatername+",  ");
		System.out.print("상영회차 : "+ screenround+",  ");
	}
	
	
	//상영회차에 따라서 조조 

}
