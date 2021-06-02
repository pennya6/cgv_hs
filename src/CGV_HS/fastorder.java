package CGV_HS;

import java.util.*;

/*
 * fastorder : fastorder 객체 선언 클래스
 *  cgv_hs에서 ordersnack 테이블과 매치되는 클래스 
 * 
 * */

public class fastorder {
	String memberno; //고객번호
	String orderno; //주문번호
	String snackmenu; //주문 스낵메뉴
	String snackstatus; //주문상태
	String theaterno; //극장번호
	
	public fastorder() {
		
	}
	
	public fastorder(String memberno,String snackmenu) {
		this.memberno=memberno;
		this.snackmenu=snackmenu;
	}
	
	public fastorder(String orderno,String memberno, String snackmenu,
			String snackstatus, String theaterno) {
		this.orderno=orderno;
		this.memberno=memberno;
		this.snackmenu=snackmenu;
		this.snackstatus=snackstatus;
		this.theaterno=theaterno;
		
	}
	
	//주문현황 -> 모호하다 그 기준에 대해서 이야기해야할듯
	public void changeorderstatus(String orderstatus) {
		if(orderstatus=="접수") {
			System.out.println("주문하신 스낵이 접수 되었습니다.");
			//orderstatus="완료";
		}
		else
			System.out.println("완료되었습니다.");
	}
	
	//출력
	public void output() {
		
		//if(orderstatus=="접수") {
			System.out.print("	* 주문번호 : "+orderno+", ");
			System.out.print("주문스낵 : " + snackmenu +",  ");
			//System.out.print("주문현황 : "+snackstatus+",  ");
			System.out.print("극장 : " + theaterno +"\n");
			/*
			 * } else System.out.println("완료되었습니다.");
			 */
	}
	
}
