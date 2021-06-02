package CGV_HS;


   /*고객이 로그인하면 고객 객체가 생성되고 CGVHS 메뉴 이용가능
     member 클래스에는 고객의 여러 정보를 저장하는 고객변수와 기본 메소드가 정의되고,
     하위 클래스인 Rootmember와 Customer에 작업 수행을 위한 오버라이딩*/

class memberSystem {
   
   static member memberLogin;   //member.java 필요
   
   public static void main (String args[]) {
      if (DB.loadConnectCGVhs()==true)
         doCgvWork();   //9,10 -> DB를 사용 가능하도록 준비한 뒤 ok면 CGV업무 수행
      
   }
   
   public static void doCgvWork() {
      do {
         memberLogin = login();      // 로그인을 시도하여 성공하면 은행원 객체를 반환
                              // 로그인시 실제 반환되는 객체는 member 클래스의 객체가 아니고
                              //   Rootmember 또는 Customer 클래스의 객체이다
         
         if (memberLogin == null)
            System.out.println(" 오류: ID 또는 비밀번호 오류입니다.");
      } while (memberLogin == null);
      
      memberLogin.domemberWork(); //Rootmember 또는 Customer 클래스의  오버라이딩 된 메소드가 호출됨
   }
   
   public static member login() {
      
      System.out.println("\n로그인 ");
      
      System.out.println("고객ID");
      String ID = SkScanner.getString();
      System.out.println("비밀번호");
      String PW = SkScanner.getString();
      PW = member.encriptPassword(PW);
      
      return DB.getmember(ID, PW);
   }
   
}