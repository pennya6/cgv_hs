package CGV_HS;

import java.util.Date;


/*
 * member: 모든 은행원을 표현할 수 있는 은행원 클래스
 *    o cgv_hs 데이터베이스에서 member 테이블과 매치되는 클래스로
 *      member 테이블의 한 투플과  member 클래스의 한 객체가 상호 대응된다.
 *    
 *    o 이 클래스는 Java 빈즈 클래스로 작성되어 모든 필드가 private 필드이며
 *      setter 메소드와 getter 메소드가 작성되어 있음
 *   
 */

public class member {
	private String memberno;  //회원번호
	private String ID;
	private String PW;
	private java.sql.Date birth; //생년월일
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
	
	//비민번호 암호화
	public static String encriptPassword(String password) {
		if (password==null || password.equals(""))
			return "1Ew$H6KhfKHJHG";			
		
		return "" + password.hashCode();  // Java의 hashCode() 메소드를 이용한 아주 간단한 암호화
	}
	
	public void encriptPassword() {
		PW = encriptPassword(PW);
	}
	
	//member 정보 출력
	public void output() {
		System.out.print("	* 고객번호 :"+memberno+",  ");
		System.out.print("ID : "+ID+",  ");
		System.out.print("PW : "+PW+",  ");
		System.out.print("생년월일 : "+birth+",  ");
		System.out.print("전화번호 : "+phoneno+",  ");
		System.out.print("이름 : "+name+"  ");
	}
	
	
	/*
	 * getter 메소드와 setter 메소드 정의
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
