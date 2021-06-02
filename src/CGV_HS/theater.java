package CGV_HS;

import java.util.*;

/* theater: 영화관 객체 선언 클래스
   cgv_hs DB에서 theater 테이블과 매치되는 클래스
 */


public class theater {
   
	 String theaterno;
     String theatername; //영화관 이름
     String address; //영화관 주소
   
   
   public theater() {
   }

   
   public theater(String theaterno,String theatername, String address) {
      this.theatername = theatername;
      this.address = address;
      this.theaterno=theaterno;
   }

   //극장 정보 출력
   public void output() {
	   System.out.println(" 극장번호: "+theaterno+", ");
      System.out.println(" 극장 이름: "+theatername+", ");
      System.out.println(" 극장 주소: "+address+";");
   }
   
   public String gettheatername() {
		return theatername;
	}
}