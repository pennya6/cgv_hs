package CGV_HS;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Vector;

//extends Ȯ��
public class Manager extends member {
	
	public Manager() {
		
	}
		
	public Manager(String memberno, String ID, String PW, java.sql.Date birth, String phoneno, String name) {
		super(memberno, ID, PW, birth, phoneno, name);
	}
	
	// �Ŵ��� ���� ó�� �޼ҵ�
	public void doManagerWork() {
		
		Manager m = new Manager();
		
		
		int menu = 0;
		
		while(true)
		{
			System.out.println("������ �ý���");
			
			System.out.println("1.ȸ�� ����"); //ȸ�� ����Ʈ ���, ȸ�� ����
			System.out.println("2.���� ����"); //���� ����Ʈ ���, ����  ���� ����
			System.out.println("3.�󿵰� ����"); // �󿵰� ����Ʈ ���, �󿵰� �߰� ���� ����
			System.out.println("4.��ȭ ����"); // ��ȭ ����Ʈ ���, ��ȭ �߰� ���� ����
			System.out.println("5.�� ����"); // �� ����Ʈ ���, �� �߰� ����
			System.out.println("6.���� ����"); // �� ���� ����Ʈ ���
			System.out.println("7.�н�Ʈ ����"); // �� �н�Ʈ ���� ����Ʈ ���
			System.out.println("8.�ı� ����"); // �ı� ����Ʈ ���, �ı� ����
			System.out.println("9. ����");
			System.out.println("\n ���Ͻô� �۾��� �����ϼ���.");
			
			String menuSelection = SkScanner.getString();
			
			try {
				switch(menuSelection) {
				case"1":
					this.customerManage();
					break;
				case"2":
					this.theaterManage();
					break;
				case"3":
					this.screentheaterManage();
					break;
				case"4":
					this.movieManage();
					break;
				case"5":
					this.screenManage();
					break;
				case"6":
					this.ticketingManage();
					break;
				case"7":
					this.fastorderManage();
					break;
				case"8":
					this.reviewManage();
					break;
				case"9":
					System.out.println("�����մϴ�.");
					break;
				default:
					System.out.println("�߸��� �Է��Դϴ�.");
				}
			}
			catch (Exception e) {
				System.err.println("\n"+ e.getMessage());
			}
		}
	}
	
	//�Ŵ��� ���� �޼ҵ�
	
	//ȸ�� ����
	public static void customerManage() {
		
		System.out.println("ȸ�� ����");
		
		Vector members = DB.getAllmember(); 
		for(int i=0; i<members.size(); i++) {
			((member) members.elementAt(i)).output();
		}
		//ȸ�� ����
		System.out.print("  �����Ͻ� ȸ�� �ѹ��� �����ϼ��� "); //�����
		String memberno = SkScanner.getString();
		
		DB.memberDelete(memberno); //DB �ʿ�
		
	}	
	
	//���� ����
	public static void  theaterManage() {
		System.out.println("���� ����");
		
		Vector theaters = DB.getAlltheater(); //DB�ʿ� 
		for(int i=0; i<theaters.size(); i++) {
			((theater) theaters.elementAt(i)).output();
		}
		
		//���� ����
		System.out.print("  �����Ͻ� ������ �����ϼ��� "); //�����
		String theaterno = SkScanner.getString();
		
		DB.theaterDelete(theaterno); //DB �ʿ�
	}
	
	//�󿵰� ����
	public static void screentheaterManage() {
		System.out.println("�󿵰� ����");
		
		Vector screentheaters = DB.getAllscreentheater();
		for(int i=0; i<screentheaters.size(); i++) {
			((screentheater) screentheaters.elementAt(i)).output();
		}
		
		int choice=0;
		System.out.println("1. �󿵰� ����");
		System.out.println("2. �󿵰� �߰�");
		System.out.println("������ ������ �����ϼ���");
		
		if(choice==0) {
			//�󿵰� ����
			System.out.print("  �����Ͻ� �󿵰��� �����ϼ��� "); //�����
			String screentheatername = SkScanner.getString();
			System.out.print("  �����Ͻ� �󿵰��� ������ �����ϼ��� "); //�����
			String theatername = SkScanner.getString();
			
			DB.screentheaternameDelete(screentheatername,theatername); //DB �ʿ�
		}
		else {
			//�󿵰� �߰�
			System.out.println("�󿵰� �̸�");
			String screenSelection = SkScanner.getString();
			System.out.println("��ȭ�� ����");
			String theaterSelection = SkScanner.getString();
			int screentheaterseats=42;
			
			screentheater newmanageScreenTheater = new screentheater(screenSelection,theaterSelection,screentheaterseats);
			DB.insertscreentheater(newmanageScreenTheater); 
		}	
	}
	
	
	//��ȭ ����
	public static void movieManage() {
		System.out.println("��ȭ ����");
		
		Vector movies = DB.getAllmovie(); // DB�ʿ�
		
		for(int i=0; i<movies.size(); i++) {
			((movie) movies.elementAt(i)).output();
		}
		
		int choice=0;
		System.out.println("1. ��ȭ����");
		System.out.println("2. ��ȭ�߰�");
		System.out.println("������ ������ �����ϼ���");
		
		if(choice==1) {
			//��ȭ ����
			System.out.print("  �����Ͻ� ��ȭ�� �����ϼ��� "); //�����
			String movieno = SkScanner.getString();
			
			DB.movieDelete(movieno); //DB �ʿ�
		}
		else {
			//��ȭ �߰�
			System.out.println("��ȭ ����");
			String titleSelection = SkScanner.getString();
			
			System.out.println("�帣");
			String genreSelection = SkScanner.getString();
			
			System.out.println("������"); //��ȯ �޼ҵ� �ʿ�. customer.java 154��
			String year = SkScanner.getString();
			
			year = (year.length() == 1) ? "000" + year : (year.length() == 2) ? "00" + year : (year.length() == 3) ? "0" + year : year;
			
			String month = SkScanner.getString("��");
			String date = SkScanner.getString("��");
			
			java.sql.Date dateSelection = Date.valueOf(year + "-" + month + "-" +  date);
			System.out.println("\n  * ���� ��¥ = " + dateSelection);	
			
			System.out.println("�ٰŸ�");
			String storySelection = SkScanner.getString();
			
			System.out.println("���� Ÿ��");
			int runtimeSelection = SkScanner.getInt();
			
			System.out.println("��ȭ��ȣ");
			String movienoSelection=SkScanner.getString();
			
			System.out.println("�������");
			double movieavgGradeSelection=SkScanner.getDouble();
			
			System.out.println("�����");
			int movieCntsReviewSelection=SkScanner.getInt();
			
			movie newmanageMovie = new movie(movienoSelection,titleSelection, genreSelection, dateSelection, runtimeSelection, storySelection, movieavgGradeSelection, movieCntsReviewSelection);
			
			DB.insertmovie(newmanageMovie); //DB�ʿ�
			
		}
	
	}
	
	//�� ����
	public static void screenManage() {
		System.out.println("�� ����");
		
		Vector screens = DB.getAllscreen();
		for(int i=0; i<screens.size(); i++) {
			((screen) screens.elementAt(i)).output();
		}
		
		int choice=0;
		System.out.println("1. �󿵻���");
		System.out.println("2. ���߰�");
		System.out.println("������ ������ �����ϼ���");
		
		if(choice==1) {
			//�� ����
			System.out.print("  �����Ͻ� �� ������ �����ϼ��� "); //�����
			String screen = SkScanner.getString();
			
			System.out.println("��ȭ�� ����");
			String theaterSelection2 = SkScanner.getString();
			System.out.println("�󿵰� �̸�");  //�󿵰� �߰��� �ִ� �ڵ�� ���Ƶ� �ɱ�?
			String screentheaterSelection2 = SkScanner.getString();
			
			System.out.println("�� ��¥");
			String year2 = SkScanner.getString();
			 
			 
			 year2 = (year2.length() == 1) ? "000" + year2 : (year2.length() == 2) ? "00" + year2 : (year2.length() == 3) ? "0" + year2 : year2;
				
				String month2 = SkScanner.getString("��");
				String date2 = SkScanner.getString("��");
				
				java.sql.Date dateSelection2 = Date.valueOf(year2 + "-" + month2 + "-" +  date2);
				
				System.out.println("�� �ð�");
				
				String hour2 = SkScanner.getString("  ��  ");
				String minute2 = SkScanner.getString("  ��  ");
				
				java.sql.Time timeSelection2 = Time.valueOf(hour2 + ":" + minute2 + "00");  // ���� �ʰ� �Էµ��� ������ "00"�� �ݵ�� �߰��ؾ� ��
			
			DB.screenDelete(dateSelection2,timeSelection2,theaterSelection2,screentheaterSelection2); //DB �ʿ�
		}
		
		else {
			//�� �߰�
			System.out.println("��ȭ�� ����");
			String theaterSelection = SkScanner.getString();
			System.out.println("�󿵰� �̸�");  //�󿵰� �߰��� �ִ� �ڵ�� ���Ƶ� �ɱ�?
			String screentheaterSelection = SkScanner.getString();
			System.out.println("��ȭ ����");
			String titleSelection = SkScanner.getString();
			System.out.println("�� ��¥");
			String year = SkScanner.getString();
			
			year = (year.length() == 1) ? "000" + year : (year.length() == 2) ? "00" + year : (year.length() == 3) ? "0" + year : year;
			
			String month = SkScanner.getString("��");
			String date = SkScanner.getString("��");
			
			java.sql.Date dateSelection = Date.valueOf(year + "-" + month + "-" +  date);
			System.out.println("\n  * ���� ��¥ = " + dateSelection);
			
			System.out.println("�� �ð�");
			
			String hour = SkScanner.getString("  ��  ");
			String minute = SkScanner.getString("  ��  ");
			
			java.sql.Time timeSelection = Time.valueOf(hour + ":" + minute + "00");  // ���� �ʰ� �Էµ��� ������ "00"�� �ݵ�� �߰��ؾ� ��

			System.out.println("\n  * ���� �ð� = " + timeSelection);
			
			System.out.println("ȸ��");
			int screenroundSelection = SkScanner.getInt();
			
			//DB.addscreenround(s, titleSelection);
			
			screen newmanagerScreen = new screen(dateSelection,timeSelection,titleSelection,theaterSelection,screentheaterSelection,screenroundSelection);
			DB.insertscreen(newmanagerScreen); //DB�ʿ�
		}
		
		
		
	}
	
	//���� ����
	public static void ticketingManage() {
		
		System.out.println("���� ����");
		
		Vector ticketings = DB.getticketing(); //DB�ʿ�
		
		for(int i=0; i<ticketings.size(); i++) {
			((ticketing) ticketings.elementAt(i)).output();
		}
	}
	
	//�н�Ʈ ���� ����
	public static void fastorderManage() {
		System.out.println("�н�Ʈ ���� ����");
		
		Vector fastorders = DB.getfastorder(); //DB�ʿ�
		
		for(int i=0; i<fastorders.size(); i++) {
			((fastorder) fastorders.elementAt(i)).output();
		}
	}
	
	//���� ����
	public static void reviewManage() {
		System.out.println("���� ����");
		
		Vector reviews = DB.getAllreview();
		for(int i=0; i<reviews.size(); i++) {
			((ticketing) reviews.elementAt(i)).output(); //DB�ʿ�
		}
		System.out.print("  �����Ͻ� ������ ���Ź�ȣ�� �����ϼ��� "); //�����
		String review_number = SkScanner.getString();
		
		DB.reivewDelete(review_number); //DB �ʿ�
	}
	
}


