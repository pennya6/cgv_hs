package CGV_HS;

import java.util.*;

/*
 * fastorder : fastorder ��ü ���� Ŭ����
 *  cgv_hs���� ordersnack ���̺�� ��ġ�Ǵ� Ŭ���� 
 * 
 * */

public class fastorder {
	String memberno; //����ȣ
	String orderno; //�ֹ���ȣ
	String snackmenu; //�ֹ� �����޴�
	String snackstatus; //�ֹ�����
	String theaterno; //�����ȣ
	
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
	
	//�ֹ���Ȳ -> ��ȣ�ϴ� �� ���ؿ� ���ؼ� �̾߱��ؾ��ҵ�
	public void changeorderstatus(String orderstatus) {
		if(orderstatus=="����") {
			System.out.println("�ֹ��Ͻ� ������ ���� �Ǿ����ϴ�.");
			//orderstatus="�Ϸ�";
		}
		else
			System.out.println("�Ϸ�Ǿ����ϴ�.");
	}
	
	//���
	public void output() {
		
		//if(orderstatus=="����") {
			System.out.print("	* �ֹ���ȣ : "+orderno+", ");
			System.out.print("�ֹ����� : " + snackmenu +",  ");
			//System.out.print("�ֹ���Ȳ : "+snackstatus+",  ");
			System.out.print("���� : " + theaterno +"\n");
			/*
			 * } else System.out.println("�Ϸ�Ǿ����ϴ�.");
			 */
	}
	
}
