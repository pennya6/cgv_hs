package CGV_HS;

import java.util.*;

/* theater: ��ȭ�� ��ü ���� Ŭ����
   cgv_hs DB���� theater ���̺�� ��ġ�Ǵ� Ŭ����
 */


public class theater {
   
	 String theaterno;
     String theatername; //��ȭ�� �̸�
     String address; //��ȭ�� �ּ�
   
   
   public theater() {
   }

   
   public theater(String theaterno,String theatername, String address) {
      this.theatername = theatername;
      this.address = address;
      this.theaterno=theaterno;
   }

   //���� ���� ���
   public void output() {
	   System.out.println(" �����ȣ: "+theaterno+", ");
      System.out.println(" ���� �̸�: "+theatername+", ");
      System.out.println(" ���� �ּ�: "+address+";");
   }
   
   public String gettheatername() {
		return theatername;
	}
}