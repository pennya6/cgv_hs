package CGV_HS;


   /*���� �α����ϸ� �� ��ü�� �����ǰ� CGVHS �޴� �̿밡��
     member Ŭ�������� ���� ���� ������ �����ϴ� �������� �⺻ �޼ҵ尡 ���ǵǰ�,
     ���� Ŭ������ Rootmember�� Customer�� �۾� ������ ���� �������̵�*/

class memberSystem {
   
   static member memberLogin;   //member.java �ʿ�
   
   public static void main (String args[]) {
      if (DB.loadConnectCGVhs()==true)
         doCgvWork();   //9,10 -> DB�� ��� �����ϵ��� �غ��� �� ok�� CGV���� ����
      
   }
   
   public static void doCgvWork() {
      do {
         memberLogin = login();      // �α����� �õ��Ͽ� �����ϸ� ����� ��ü�� ��ȯ
                              // �α��ν� ���� ��ȯ�Ǵ� ��ü�� member Ŭ������ ��ü�� �ƴϰ�
                              //   Rootmember �Ǵ� Customer Ŭ������ ��ü�̴�
         
         if (memberLogin == null)
            System.out.println(" ����: ID �Ǵ� ��й�ȣ �����Դϴ�.");
      } while (memberLogin == null);
      
      memberLogin.domemberWork(); //Rootmember �Ǵ� Customer Ŭ������  �������̵� �� �޼ҵ尡 ȣ���
   }
   
   public static member login() {
      
      System.out.println("\n�α��� ");
      
      System.out.println("��ID");
      String ID = SkScanner.getString();
      System.out.println("��й�ȣ");
      String PW = SkScanner.getString();
      PW = member.encriptPassword(PW);
      
      return DB.getmember(ID, PW);
   }
   
}