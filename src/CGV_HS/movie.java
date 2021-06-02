package CGV_HS;

import java.util.*;

/*
 * movie : 영화 객체 선언 클래스
 *  cgv_hs에서 movie 테이블과 매치되는 클래스 
 * 
 * */

public class movie {
	String movieno; //영화번호
	String title; //영화제목
	String genre; //영화장르
	java.sql.Date date; //개봉일
	int runtime; //상영시간
	String story; //내용
	double avgGrade; //평균평점
	int cntsReview; //리뷰수
	
	public movie() {}
	
	public movie(String moiveno, String title, String genre, Date date, int runtime, 
			String story, double avgGrade, int cntsReview) {
		this.movieno=movieno;
		this.avgGrade=avgGrade;
		this.cntsReview=cntsReview;
		this.date=new java.sql.Date(date.getTime());
		this.genre=genre;
		this.runtime=runtime;
		this.story=story;
		this.title=title;
	}
	
	//리뷰수 증가
	public void addcntsReview(int cntsReview) {
		cntsReview+=1;
	}
	
	public void output() {
		System.out.print("   영화번호:"+movieno+",  ");
		System.out.print("   영화제목:"+title+",  ");
		System.out.print("   영화장르:"+genre+",  ");
		System.out.print("   개봉일:"+date+",  ");
		System.out.print("   상영시간:"+runtime+",  ");
		System.out.print("   영화줄거리:"+story+",  ");
		System.out.print("   평균평점:"+avgGrade+",  ");
		System.out.print("   리뷰수:"+cntsReview+"\n");
	}
	
	

}
