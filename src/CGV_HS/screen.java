package CGV_HS;

import java.util.*;


/*
 * screen : �� ��ü ���� Ŭ����
 *  cgv_hs���� screen ���̺�� ��ġ�Ǵ� Ŭ���� 
 * 
 * */

public class screen {
	String title; //��ȭ����
	String theatername; // ���� �̸�
	String screentheatername; // �󿵰� �̸�
	int screenround; //��ȸ��
	java.sql.Time screentime; //�󿵽ð�
	java.sql.Date screendate; //�󿵳�¥
	
	
	
	public screen(java.sql.Date screendate, java.sql.Time screentime,String title, 
			String theatername, String screentheatername ,int screenround ) {
		
		this.screendate=new java.sql.Date(screendate.getDate());	
		this.screentime=new java.sql.Time(screentime.getTime());
		this.title=title;
		this.screenround=screenround;
		this.theatername=theatername;
		this.screentheatername=screentheatername;
	}
	
	public screen() {
		// TODO Auto-generated constructor stub
	}

	//������ ���
	public void output() {
		System.out.println("	* ��  ");
		System.out.print("��¥ : "+screendate +",  ");
		System.out.print("�ð� : "+ screentime+",  ");
		System.out.print("��ȭ���� : "+ title+",  ");
		System.out.print("���� : "+ theatername+",  ");
		System.out.print("�󿵰� : "+ screentheatername+",  ");
		System.out.print("��ȸ�� : "+ screenround+",  ");
	}
	
	
	//��ȸ���� ���� ���� 

}
