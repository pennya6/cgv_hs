package CGV_HS;

public class snack {
	   String snackmenu;
	   int snackprice;
	
	
	public snack() {
	   
	}

	public snack(String snackmenu, int snackprice) {
	   this.snackmenu = snackmenu;
	   this.snackprice = snackprice;
	}

	public void output() {
	   System.out.println(" �޴�: "+snackmenu+", ");
	   System.out.println(" ����: "+snackprice+", ");
	}

}