package CGV_HS;

import java.util.*;

/*
 * screentheater : �󿵰� ��ü ���� Ŭ����
 *  cgv_hs���� screentheater ���̺�� ��ġ�Ǵ� Ŭ���� 
 * 
 * */

public class screentheater {
	String screentheatername; //�󿵰��̸�
	int screentheaterseat; //�ڸ���
	String theatername; //�����̸�
	
	public screentheater() {
		
	}
	
	public screentheater(String screentheatername, String theatername,int screentheaterseat) {
		this.screentheatername=screentheatername;
		this.theatername=theatername;
		this.screentheaterseat=screentheaterseat;
	}
	
	//�󿵰� ���� ���
	public void output() {
		System.out.print("	* �󿵰��̸� : "+screentheatername+", ");
		System.out.print("�󿵰� �ڸ��� : " + screentheaterseat +",  ");
		System.out.print("���� : " + theatername +"\n");
		
	}
	
	public void setscreentheatername(String screentheatername) {
		this.screentheatername=screentheatername;
	}
	public String getscreentheatername() {
		return screentheatername;
	}
	public String gettheatername() {
		return theatername;
	}
	public void settheatername(String theatername) {
		this.theatername=theatername;
	}
	public int getscreentheaterseat() {
		return screentheaterseat;
	}
	public void setscreentheaterseat(int screentheaterseat) {
		this.screentheaterseat=screentheaterseat;
	}
}
