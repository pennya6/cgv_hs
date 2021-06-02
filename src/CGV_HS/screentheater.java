package CGV_HS;

import java.util.*;

/*
 * screentheater : 상영관 객체 선언 클래스
 *  cgv_hs에서 screentheater 테이블과 매치되는 클래스 
 * 
 * */

public class screentheater {
	String screentheatername; //상영관이름
	int screentheaterseat; //자리수
	String theatername; //극장이름
	
	public screentheater() {
		
	}
	
	public screentheater(String screentheatername, String theatername,int screentheaterseat) {
		this.screentheatername=screentheatername;
		this.theatername=theatername;
		this.screentheaterseat=screentheaterseat;
	}
	
	//상영관 정보 출력
	public void output() {
		System.out.print("	* 상영관이름 : "+screentheatername+", ");
		System.out.print("상영관 자리수 : " + screentheaterseat +",  ");
		System.out.print("극장 : " + theatername +"\n");
		
	}
	
	public void setscreentheatername(String screentheatername) {
		this.screentheatername=screentheatername;
	}
	public String getscreentheatername() {
		return screentheatername;
	}
	public String gettheatername() {
		return theatername;
	}
	public void settheatername(String theatername) {
		this.theatername=theatername;
	}
	public int getscreentheaterseat() {
		return screentheaterseat;
	}
	public void setscreentheaterseat(int screentheaterseat) {
		this.screentheaterseat=screentheaterseat;
	}
}
