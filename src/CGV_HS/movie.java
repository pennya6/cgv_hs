package CGV_HS;

import java.util.*;

/*
 * movie : ��ȭ ��ü ���� Ŭ����
 *  cgv_hs���� movie ���̺�� ��ġ�Ǵ� Ŭ���� 
 * 
 * */

public class movie {
	String movieno; //��ȭ��ȣ
	String title; //��ȭ����
	String genre; //��ȭ�帣
	java.sql.Date date; //������
	int runtime; //�󿵽ð�
	String story; //����
	double avgGrade; //�������
	int cntsReview; //�����
	
	public movie() {}
	
	public movie(String moiveno, String title, String genre, Date date, int runtime, 
			String story, double avgGrade, int cntsReview) {
		this.movieno=movieno;
		this.avgGrade=avgGrade;
		this.cntsReview=cntsReview;
		this.date=new java.sql.Date(date.getTime());
		this.genre=genre;
		this.runtime=runtime;
		this.story=story;
		this.title=title;
	}
	
	//����� ����
	public void addcntsReview(int cntsReview) {
		cntsReview+=1;
	}
	
	public void output() {
		System.out.print("   ��ȭ��ȣ:"+movieno+",  ");
		System.out.print("   ��ȭ����:"+title+",  ");
		System.out.print("   ��ȭ�帣:"+genre+",  ");
		System.out.print("   ������:"+date+",  ");
		System.out.print("   �󿵽ð�:"+runtime+",  ");
		System.out.print("   ��ȭ�ٰŸ�:"+story+",  ");
		System.out.print("   �������:"+avgGrade+",  ");
		System.out.print("   �����:"+cntsReview+"\n");
	}
	
	

}
