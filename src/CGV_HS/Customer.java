package CGV_HS;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.SimpleTimeZone;
import java.util.Vector;


public class Customer  extends member{
	
	public Customer() {
	}
	
	//�Ϲ� �� ��ü ������
	public Customer(String memberno, String ID, String PW,java.sql.Date birth, String phoneno, String name ) {
		super(memberno, ID,PW,birth,phoneno,name);
	}
	
	// �� ���� ó�� �޼ҵ�
	public void doCustomerWork() {
		
		Customer c=new Customer();
		
		int menu = 0;
		
		while(true)
		{
			System.out.println("\n\n <<��ȭ ���� �ý���>>");
			//System.out.println("   �� ID: " + getID() + "\n");
			System.out.println("   1. ��ȭ ��ȸ"); // ��ȭ ��ȸ(���� ����) 
			System.out.println("   2. ��ȭ ����"); // ��ȭ, ����, ��¥, �ð� , ����(����), �¼�  ( + ���� ���� : 8000, 12000)
			System.out.println("   3. �н�Ʈ���� "); 
			System.out.println("   4. ����������"); // 1.��ȭ ���� ��Ȳ  (�����ۼ�)
												// 2.�н�Ʈ���� ��Ȳ 3.���� ��ȸ �� �ۼ�
			System.out.println("   5. ����");
			System.out.println("\n  * ���Ͻô� �۾��� �����ϼ��� > ");
			
			String menuSelection = SkScanner.getString();

			try {
					switch(menuSelection) {
					case"1":
						this.movielistview();
						break;
					case"2":
						this.openticketing();
						break;
					case"3":
						this.fastorder(c);
						break;
					case"4":
						this.mypage(c); 
						break;
					case"5":
						System.out.println("�����Դϴ�.");
						break;
					default:
						System.out.println("�߸��� �Է��Դϴ�.");
				}
			}
			catch (Exception e) {
				System.err.println("\n "+ e.getMessage());
			}	
	}
}
	
	
	//�� ���� �޼ҵ�
	
	//��ȭ ���
	public void movielistview() {
		System.out.println("\n ��ȭ ��ȸ");
		
		Vector movies = DB.getAllmovie(); //DB����
		for(int i=0; i<movies.size(); i++) {
			((movie) movies.elementAt(i)).output();
		}
	}
	
	
	//��ȭ ����
	public void openticketing() {
			
			//��ȭ ���� ����
			movielistview();
			
			System.out.println("������ ��ȭ�� �������ּ���.");	
			String movieSelection = SkScanner.getString();
		
			DB.choicemovie_after_other(movieSelection);
			
			//������ ��ȭ�� ���� ���� ���� ����
			System.out.println("������ ������ �ּ���");
			String theaterSelection = SkScanner.getString();
			
			System.out.println("�󿵰��� �������ּ���");
			String screentheaterSelection=SkScanner.getString();
			
			//������ ��ȭ, ���忡 ���� ��¥���� ����
			System.out.println("��¥�� �������ּ���");
			
			String year=SkScanner.getString();
			// year String ���� 4�ڸ� ����Ʈ ���ڰ� �Ǿ�� �ϹǷ� 4�ڸ� �� ��ȯ 
			year = (year.length() == 1) ? "000" + year : (year.length() == 2) ? "00" + year : (year.length() == 3) ? "0" + year : year;
			
			String month = SkScanner.getString("  o �� > ");
			String date = SkScanner.getString("  o �� > ");
			
			java.sql.Date dateSelection = Date.valueOf(year + "-" + month + "-" +  date);
			
			System.out.println("\n  * ���� ��¥ = " + dateSelection);	
			//String dateSelection=SkScanner.getString();
			
			
			// ������ ��ȭ, ���忡 ���� �ð� ���� �Է��Ͽ� java.sql.Time ��ü�� ��ȯ�ϱ�
			System.out.println("�ð��� �������ּ���");
			
			String hour = SkScanner.getString("  o �� > ");
			String minute = SkScanner.getString("  o �� > ");
			
			java.sql.Time timeSelection = Time.valueOf(hour + ":" + minute + "00");  // ���� �ʰ� �Էµ��� ������ "00"�� �ݵ�� �߰��ؾ� ��

			System.out.println("\n  * ���� �ð� = " + timeSelection);	
			
			
			System.out.println("���̸� �������ּ���");
			int ageSelection=SkScanner.getInt();
			int price;
				//���ɿ� ���� ��� ��ȯ
				if(ageSelection<=19) {
					price=8000;
				}
				else price=12000;
			
				
			System.out.println("���� ����� �������ּ���");
			String paySelection=SkScanner.getString();
			
			System.out.println("�¼��� �������ּ���");
			String seatSelection=SkScanner.getString();
			
			ticketing newticketing=new ticketing(movieSelection,theaterSelection,screentheaterSelection,timeSelection,dateSelection,seatSelection,price,paySelection);
			DB.insertticketing(newticketing);
			
	}
	
	// ���ڿ� ��¥ ������ �־����� java.sql.Date ��ü�� ��ȯ�� ��ȯ�ϴ� �޼ҵ�
		static java.sql.Date transformDate(String year, String month, String date) {
			// year String ���� 4�ڸ� ����Ʈ ���ڰ� �Ǿ�� �ϹǷ� 4�ڸ� �� ��ȯ 
			year = (year.length() == 1) ? "000" + year : (year.length() == 2) ? "00" + year : (year.length() == 3) ? "0" + year : year;

			return java.sql.Date.valueOf(year + "-" + month + "-" + date);
		}

		// ���ڿ� �ð� ������ �־����� java.sql.Time ��ü�� ��ȯ�� ��ȯ�ϴ� �޼ҵ�
		static java.sql.Time transformTime(String hour, String minute, String second) {
			return java.sql.Time.valueOf(hour + ":" + minute + ":" + second);
		}

	
	
	//�н�Ʈ���� -> �ֹ� �ϴ°�?
	//��ȸ?
	public static void fastorder(Customer c) {
		int snackChoice = 0;
		//�׳� �̸� �̱�
		System.out.println("1. �н�Ʈ���� ��ȸ�ϱ�"); 
		System.out.println("2. �н�Ʈ���� �ֹ��ϱ�");
		
		snackChoice = SkScanner.getInt();
	
		
			switch(snackChoice) {
			case 1:
									
				Vector snacks  = DB.getAllSnack(); //DB �ʿ�
					for(int i=0; i<snacks.size(); i++) {
						((snack) snacks.elementAt(i)).output();
					}		
					
			case 2:
				System.out.println("�����Ͻ� �޴��� �������ּ���.");
				String snackSelection = SkScanner.getString();
				
				fastorder newfastorder = new fastorder(c.getmemberno(),snackSelection);
				DB.insertfastorder(newfastorder);												
		}
		
  }	

		
	public static void mypage(Customer c) {
		int choice=0;
		String review="";
		System.out.println("���ϴ� ��ȣ�� �����ϼ���");
		System.out.println("1.��ȭ ���� ��Ȳ �� �����ۼ�");
		System.out.println("2.�н�Ʈ���� ��Ȳ ");
		System.out.println("3.���� ��ȸ ");
		
		choice=SkScanner.getInt();
		
		switch(choice) {
		case 1:
			System.out.println("��ȭ ���� ��Ȳ�Դϴ�.");
			DB.getticketing(c.getmemberno());
			
			System.out.println("�����ۼ��ϱ�");
			System.out.println("���Ź�ȣ�� �Է��ϼ���");
			String ticketingno=SkScanner.getString();
			
			System.out.println("review story �Է��ϼ���");
			String review_story_s=SkScanner.getString();
			System.out.println("grade �Է��ϼ���");
			int grade_s=SkScanner.getInt();
			DB.insertticketing_review(ticketingno,review_story_s,grade_s);
		
			
		case 2:
			System.out.println("�н�Ʈ���� ��Ȳ�Դϴ�.");
			DB.getfastorder(c.getmemberno()); 
			
		case 3:
			System.out.println("������ȸ�Դϴ�.");
			System.out.println("������ȸ");
			
			review=SkScanner.getString();
			
			DB.getAllreview(c.getmemberno());

		}
	}
}




