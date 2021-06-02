package CGV_HS;


import java.sql.*;
import java.io.IOException;
import java.util.Vector;




/**
 * DB 클래스
 *    실제 MySQL DBMS와 연결하고 DB 테이블의 저장 및 검색을 위한 메소드를 갖는 클래스
 *    
 *    DBMS 연결, 테이블에 대한 처리 요청은 모두 public static 메소드로 구현되므로 
 *    다른 클래스에서 DB 클래스의 메소드를 호출할 때 수신자는 DB이다.
 *    
 */

public class DB {
  
	static  Connection con = null;

	static String driver;
	public static String dbms;
	static String URL;
	static String database;

	static String driverMySQL = "com.mysql.jdbc.Driver";
	static String URLLocalMySQL = "jdbc:mysql://localhost:3306/" ;;

	static String URLRemoteMySQL = "jdbc:mysql://203.252.21.54:3306/";;

	static {
		driver = driverMySQL;
		dbms = "MySQL";
		URL = URLLocalMySQL;
		database = "cgv_hs";
	}

	public static void setDBMS(String dbmsTo) {
		System.out.println("  << for debug >> in setSBMS(): DBMS = " + dbmsTo); 
		
		if (dbmsTo.equals("MySQL")){
			driver = driverMySQL;
			dbms = "MySQL";
			URL = URLLocalMySQL;
		}
		else if (dbmsTo.equals("Remote MySQL")){
			driver = driverMySQL;
			dbms = "Remote MySQL";
			URL = URLRemoteMySQL;
		}

		loadConnectCGVhs();
	}
	
	// JDBC 드라이버 로드 및 연결, 연경 성공이면 true, 실패면 false 반환하는 메소드
		public static boolean loadConnectCGVhs()  {
			return loadConnect(database);
		}
		
		// 드라이브 로드 및 연결하는 메소드
		public static boolean loadConnect(String database)  {
			try {
				// 드라이버 로딩
				Class.forName(driverMySQL);
			} catch ( java.lang.ClassNotFoundException e ) {
				System.err.println("\n  ??? Driver load error in loadConnect(): " + e.getMessage() );
				e.printStackTrace();
			}

			try {
				// 연결하기 - HSbankJSP 데이터베이스와 연결
				con = DriverManager.getConnection(URL + database,"root", "onlyroot");  
				System.out.println("\n  << for debug >> 연결 성공: " + URL+database + "에 연결됨 \n");
				
				return true;
			} catch( SQLException ex ) {
				System.err.println("\n  ??? Connection error in loadConnect(): " + ex.getMessage() );
				ex.printStackTrace();
			}	   		
		
			return false;
		}
	
		
		
		  // 주어진 SQL 문을 실행하는 메소드
		public static void executeAnyQuery(String sql) { 
			try {
				Statement stmt = con.createStatement();
				stmt.execute(sql); 
		  } catch(SQLException ex ) {
			  System.err.println("\n  ??? SQL exec error in executeAnyQuery(): " +
			  ex.getMessage() ); ex.printStackTrace(); 
		  }
			}
		
		
		
		// member 객체를 member 테이블 member의 투플로 삽입하는 메소드
		   public static int insertmember(member member) {
				int updateCnt = 0;

				try {                      
					// SQL 질의문을 수행한다.
					String sql = "insert into member values (?, ?, ?, ?, ?, ?);" ;
					System.out.println("\n  << for debug >> SQL : " + sql + "\n");
					
					PreparedStatement prStmt = con.prepareStatement(sql);

					prStmt.setString(1, member.getmemberno());
					prStmt.setString(2, member.getID());
					prStmt.setString(3, member.getPW());
					prStmt.setDate(4, member.getDatebirth());
					prStmt.setString(5, member.getphoneno());
					prStmt.setString(6, member.getname());

					updateCnt = prStmt.executeUpdate();  		
					
				} catch( SQLException ex ) {

					System.err.println("\n  ??? SQL exec error in insertBanker(): " + ex.getMessage() );
				}

				return updateCnt;
		   }
		
	 
	 //주어진 아이디의 고객 탐색하여 성공하면 해당 고객 객체 반환하는 메소드
	 public static member getmember(String ID) {
		 try {                      
				// SQL 질의문을 수행한다.
				String sql = "select * from member where ID=?;" ;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");
				PreparedStatement prStmt = con.prepareStatement(sql);

				prStmt.setString(1, ID);

				ResultSet rs = prStmt.executeQuery();  
				if (rs.next())  {
					member member = getmemberFromRS(rs);
					return member;
				}			
			} catch( SQLException ex ) {             
				System.err.println("\n  ??? SQL exec error in getmember(): " + ex.getMessage() );
			}

			return null;
	 }
	 
	// 주어진 아이디와 패스워드의 은행원 탐색하여 성공하면 해당 member 객체를 반환하는 메소드
	// 탐색 실패시 null 반환
	 public static member getmember(String ID, String PW) {
			try {                      
				// SQL 질의문을 수행한다.
				String sql = "select * from member where ID=? and PW=?;" ;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");
				PreparedStatement prStmt = con.prepareStatement(sql);

				prStmt.setString(1, ID);
				prStmt.setString(2, PW);

				ResultSet rs = prStmt.executeQuery();  
				if (rs.next())  { 
					member member = getmemberFromRS(rs);
					return member;
				}			
			} catch( SQLException ex ) {             
				System.err.println("\n  ??? SQL exec error in getBanker(): " + ex.getMessage() );
			}

			return null;
		}
	 
	 public static Vector getAllmember() {
		 Vector members = new Vector();

			member m;

			ResultSet rs = getAllmemberRS();

			try {
				while (rs.next())  {
					m = getmemberFromRS(rs);
					members.addElement(m);
				}
			} catch( SQLException ex ) 	    {
				System.err.println("** SQL exec error in getAllAccounts() : " + ex.getMessage() );
			}	
			
			return members;
	 }
	 
	 public static void memberDelete(String memberno) {
		 
		 int deleteCnt=0;
		 
		 try {
			 
			 String sql="delete from member where memberno='"+memberno+"'";
			 PreparedStatement prStmt = con.prepareStatement(sql);
			 deleteCnt=prStmt.executeUpdate();
			 
		 }catch(SQLException ex) {
			 System.out.println("\n  ??? SQL exec error in deletemember(): " + ex.getMessage());
			 
		 }
		 if (deleteCnt > 0)
				System.out.println("\n  << for debug >> " + deleteCnt+" and "+memberno+" was deleted into member table\n\n");
			else
				System.out.println("  >> Error in deleting  member\n\n"); 

			System.out.println("\n  << for debug >> total "+getNummember()+"members in member table\n");
		}
		
	// 모든 member 탐색하여 ResultSet 객체로 반환하는 메소드
		public static ResultSet getAllmemberRS() {
			try {                      
				// SQL 질의문을 수행한다.
				String sql = "select * from member" ;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);  

				return rs;
				
			} catch( SQLException ex ) {             
				System.err.println("\n  ??? SQL exec error in getAllmemberRS(): " + ex.getMessage() );
			}

			return null;
		}
		
		// member 테이블의 투플 개수를 반환하는 메소드
		public static int getNummember() {
			int num = 0;
			String sql;

			try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select count(*) as num from member";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
				if (rs.next())
					num = rs.getInt("num");

			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getNumBankers() : " + ex.getMessage() );
			}

			return num;
		}
	 
	 /*
		 * 	  ResultSet 객체에 member 테이블의 투플이 저장되어 있을 때, 이를 member 객체로 변환하는 메소드
		 *    은행원은 root 은행원과 일반 은행원이 잇으며, root 은행원의 ID는 "root"임
		 *    그러므로 투플의 ID 애트리뷰트 값이 "root"이면 Rootmember객체로, 그 외는  Customer 객체로 반환
		 */	
	 public static member getmemberFromRS(ResultSet rs) {
		 member member=null;
		 
		 try {
				String ID = rs.getString("ID");  // ID 애트리뷰트 값을 저장

				if (ID.substring(5).equals("cgvhs"))           // ID가 cgvhs~~ 으로 시작하면 관리자
				{
					System.out.println("관리자입니다.");
					member=new Manager();
				}
				else
					//member = new Customer(); // 아니면 Customer 객체를 생성하여  member 변수가 참조하게 함
					member = new Customer();
				
				member.setname( rs.getString("name") );
				member.setID( rs.getString("ID") );
				member.setPW( rs.getString("PW") );
				member.setDatebirth( rs.getDate("birth") );
				member.setphoneno(rs.getString("phoneno"));
				member.setmemberno(rs.getString("memberno"));
			
			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getmemberFromRS(): " + ex.getMessage() );
			}

			return member;
	 }
	 
	 //상영 추가
	 public static void insertscreen(screen screen) {
		 int updateCnt=0;
		 
		 try {
			// SQL 질의문을 수행한다.
				String sql = "insert into screen (title, theatername, screentheatername, screenround, screentime, screendate) values (?, ?, ?, ?, ?,?);" ;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");
				PreparedStatement prStmt = con.prepareStatement(sql);

				prStmt.setString(1, screen.title);
				prStmt.setString(2, screen.theatername);
				prStmt.setString(3, screen.screentheatername);
				prStmt.setInt(4, screen.screenround);
				prStmt.setTime(5, screen.screentime);
				prStmt.setDate(6, screen.screendate);

				//상영회차 증가 
				addscreenround(screen,screen.title);
				
				updateCnt = prStmt.executeUpdate(); 
			 
		 }catch( SQLException ex ) {

				System.err.println("\n  ??? SQL exec error in insertscreen(): " + ex.getMessage() );
			}
		 
		 if(updateCnt >0)
			 System.out.println("\n  << for debug >> " + updateCnt+" new acount was inserted into screen table\n\n");
			else
				System.out.println("  >> Error in Inserting New screen\n\n"); 

			System.out.println("\n  << for debug >> total "+getNumScreens()+" screens in screen table\n");
		 
	 }
	 
	
		  //상영회차 증가 
	 public static void addscreenround (screen s, String title) {
		  
		 s.screenround+=1; 
		  }
		 
	// screen 테이블의 투플 개수를 반환하는 메소드
	 public static int getNumScreens() {
		 int num=0;
		 String sql;
		 
		 try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select count(title) as num from screen";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
				if (rs.next())
					num = rs.getInt("num");


			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getNumScreens() : " + ex.getMessage() );
			}

			return num;
		 
	 }
	 
	 //상영 테이블의 투플개수를 반환
	 public static int getNumscreen() {
		 int num=0;
		
		 String sql;

			try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select count(title) as num from screen";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
				if (rs.next())
					num = rs.getInt("num");


			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getNumTransactions() : " + ex.getMessage() );
			}

			return num;
	 }
	 
	 public static ResultSet getAllscreenRS() {
		 
		 String sql;
		 ResultSet rs = null;
		 
		 try {
			   // Statement 생성 
				Statement stmt = con.createStatement();
			    
			   sql = "select * from screen";
			   System.out.println("   >> SQL : " + sql + "\n");
			
			   rs = stmt.executeQuery(sql);  
			 
			} catch( SQLException ex ) 	    {
			    System.err.println("** SQL exec error in getAllTransactionsRS() : " + ex.getMessage() );
			}
		
			return rs;	
	 }
	 
	
	
	 //예매
	  public static int getNumticketing() {
			int num = 0;
			String sql;

			try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select count(ticketingno) as num from ticketing";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
				if (rs.next())
					num = rs.getInt("num");


			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getNumTransactions() : " + ex.getMessage() );
			}

			return num;
	  }
	  
	  //선택한 영화제목에 따른 극장,상영관, 날짜, 시간  뽑기
	  public static Vector choicemovie_after_other(String title) {
		  Vector trs=new Vector();
		  ticketing t;	  
		  String sql;	
		  ResultSet rs = null;
		  
		  try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select screen.theatername, screen.screentheatername,screen.screendate, screen.screentime from screen where title = "+ title;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				rs = stmt.executeQuery(sql);  
				while (rs.next())  {
					t= getticketingFromRS(rs);
					trs.addElement(t);
				}

			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getTransactions() : " + ex.getMessage() );
			}

			return trs;
	  }
	  
	  
	  

	  public static Vector getticketing(String memberno) {
		  Vector trs=new Vector();
		  
		  ticketing t;	  
		  String sql;	
		  ResultSet rs = null;
			
		  
		  try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select * from ticketing where memberno = '"+memberno+"'";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				rs = stmt.executeQuery(sql);  
				while (rs.next())  {
					t= getticketingFromRS(rs);
					trs.addElement(t);
				}

			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getTransactions() : " + ex.getMessage() );
			}

			return trs;	
	  }
	  public static Vector getticketing() {
		  Vector trs=new Vector();
		  
		  ticketing t;	  
		  String sql;	
		  ResultSet rs = null;
			
		  
		  try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select * from ticketing";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				rs = stmt.executeQuery(sql);  
				while (rs.next())  {
					t= getticketingFromRS(rs);
					trs.addElement(t);
				}

			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getTransactions() : " + ex.getMessage() );
			}

			return trs;	
	  }
	  
	  public static void insertticketing (ticketing t) {
		  int updateCnt = 0;
		  
		  try {
				// SQL 질의문 수행
				String sql = "insert into ticketing(ticketingno,memberno,movieno,theaterno,screentheatername,"+
				"screentime, screendate, ticketingseats, price,pay) "
						+ "values(?,?,?,?,?, ?, ?, ?, ?,?);" ;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");
				PreparedStatement prStmt = con.prepareStatement(sql);

				prStmt.setString(1, t.ticketingno);
				prStmt.setString(2, t.memberno);
				prStmt.setString(3, t.movieno);
				prStmt.setString(4, t.theaterno);
				prStmt.setString(5, t.screentheatername);
				prStmt.setTime(6, t.screentime);
				prStmt.setDate(7, t.screendate);
				prStmt.setString(8, t.ticketingseats);
				prStmt.setInt(9, t.price);
				prStmt.setString(10, t.pay);
				
			

				updateCnt = prStmt.executeUpdate();
				
			} catch( SQLException ex ) {
				System.err.println("\n  ??? SQL exec error in insertTransaction(): " + ex.getMessage() );
			}

			if (updateCnt > 0)
				System.out.println("\n  << for debug >> " + updateCnt+" new transaction was inserted into account table\n\n");
			else
				System.out.println("  >> Error in Inserting New ticketing\n\n"); 

			System.out.println("\n  << for debug >> total "+getNumticketing()+" ticketings in ticketing table\n");
		}
	  //update문으로 변경
	  public static void insertticketing_review (String ticketingno,String reviewstory, int grade) {
		  int updateCnt = 0;
		  ticketing t=new ticketing();
		  try {
				// SQL 질의문 수행
			  
			
			  String sql = "update ticketing set reviewstory = " + reviewstory +",grade ="+grade +
					  " where ticketingno = " + ticketingno;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");
				PreparedStatement prStmt = con.prepareStatement(sql);

				prStmt.setString(1, t.reviewstory);
				prStmt.setInt(2,t.grade);
				updateCnt = prStmt.executeUpdate();
				
				add_Cntsreview(t);
				
			} catch( SQLException ex ) {
				System.err.println("\n  ??? SQL exec error in insertTransaction(): " + ex.getMessage() );
			}

			if (updateCnt > 0)
				System.out.println("\n  << for debug >> " + updateCnt+" new transaction was inserted into account table\n\n");
			else
				System.out.println("  >> Error in Inserting New ticketing\n\n"); 

			System.out.println("\n  << for debug >> total "+getNumticketing()+" ticketings in ticketing table\n");
		}
	  
	  //리뷰수 증가
	  public static void add_Cntsreview(ticketing t) {
		  movie m=new movie();
		  String sql="select movie.title from ticketing,movie  where movie.movieno=ticketing.movieno and ticketing.ticketingno = "+t.ticketingno;
		  m.title=sql;
		  m.cntsReview+=1;
		  m.avgGrade+=t.grade;
		  m.avgGrade=m.avgGrade%m.avgGrade;
	  }
	  
	  public static ResultSet getAllticketingRS() {
		  
		  Vector ticketing = new Vector();
			
			ticketing t;
			String sql;
			
			ResultSet rs = null;
			
			try {
			   // Statement 생성 
				Statement stmt = con.createStatement();
			    
			   sql = "select * from ticketing";
			   System.out.println("   >> SQL : " + sql + "\n");
			
			   rs = stmt.executeQuery(sql);  
			 
			} catch( SQLException ex ) 	    {
			    System.err.println("** SQL exec error in getAllticketingRS() : " + ex.getMessage() );
			}
		
			return rs;	
	  }
	  
	  public static ticketing getticketingFromRS(ResultSet rs) {
		  ticketing t=new ticketing();
		  
		  try {
			  t.screentheatername=rs.getString("screentheatername");
			  t.screentime=rs.getTime("screentime");
			  t.screendate=rs.getDate("screendate");
			  t.ticketingseats=rs.getString("ticketingseates");
			  t.reviewstory=rs.getString("reviewstory");
			  t.grade=rs.getInt("grade");
			  t.pay=rs.getString("pay");
		  }
		  catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getticketingFromRS() : " + ex.getMessage() );
			}

			return t;
	  }
	  
	  //패스트오더 수 
	  public static int getNumfastorder() {
		  int num=0;
		  String sql;
		  
		  try {
			// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select count(orderno) as num from fastorder";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
				if (rs.next())
					num = rs.getInt("num");


			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getNumfastorder() : " + ex.getMessage() );
			}

			return num;
	  }
	  
	  //패스트오더 조회
	  public static Vector getfastorder(String memberno) {
		  	Vector trs = new Vector();

			fastorder tr;
			String sql;
			
			ResultSet rs = null;
			
			try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select * from fastorder where memberno = '"+memberno+"'";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				rs = stmt.executeQuery(sql);  
				while (rs.next())  {
					tr = getfastorderFromRS(rs);
					trs.addElement(tr);
				}

			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getfastorder() : " + ex.getMessage() );
			}

			return trs;
	  }
	  
	  //패스트오더 조회
	  public static Vector getfastorder() {
		  	Vector trs = new Vector();

			fastorder tr;
			String sql;
			
			ResultSet rs = null;
			
			try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select * from fastorder ";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				rs = stmt.executeQuery(sql);  
				while (rs.next())  {
					tr = getfastorderFromRS(rs);
					trs.addElement(tr);
				}

			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getfastorder() : " + ex.getMessage() );
			}

			return trs;
	  }
	  
	  //fastorder table
	  //
	  public static void insertfastorder(fastorder f) {
		  int updateCnt = 0;

			try {
				// SQL 질의문 수행
				String sql = "insert into fastorder values (?, ?, ?, ?, ?);" ;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");
				PreparedStatement prStmt = con.prepareStatement(sql);

				prStmt.setString(1,f.memberno);
				prStmt.setString(2, f.orderno);
				prStmt.setString(3, f.snackmenu);
				prStmt.setString(4, f.snackstatus);
				prStmt.setString(5, f.theaterno);		   
				
				updateCnt = prStmt.executeUpdate();
				
			} catch( SQLException ex ) {
				System.err.println("\n  ??? SQL exec error in insertfastorder(): " + ex.getMessage() );
			}

			if (updateCnt > 0)
				System.out.println("\n  << for debug >> " + updateCnt+" new fastorder was inserted into fastorder table\n\n");
			else
				System.out.println("  >> Error in Inserting New fastorder\n\n"); 

			System.out.println("\n  << for debug >> total "+getNumfastorder()+" fastorder in fastorder table\n");
		}
	  
	  public static ResultSet getAllfastorderRS() {
		  Vector fastorder = new Vector();
		  
		  fastorder f;
		  String sql;
		  
		  ResultSet rs = null;
			
			try {
			   // Statement 생성 
				Statement stmt = con.createStatement();
			    
			   sql = "select * from fastorder";
			   System.out.println("   >> SQL : " + sql + "\n");
			
			   rs = stmt.executeQuery(sql);  
			 
			} catch( SQLException ex ) 	    {
			    System.err.println("** SQL exec error in getAllfastorderRS() : " + ex.getMessage() );
			}
		
			return rs;	
	  }
	  
	  public static fastorder getfastorderFromRS(ResultSet rs) {
		  fastorder f=new fastorder();
		  
		  try {
				f.memberno = rs.getString("memberno");
				f.orderno = rs.getString("orderno");
				f.snackmenu = rs.getString("snackmenu");
				f.snackstatus = rs.getString("snackstatus");
				f.theaterno = rs.getString("theaterno");
				
			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getAccountFromRS() : " + ex.getMessage() );
			}

			return f;
	  }
	  
	  
	  //movie
	  
	  public static int getNummovie() {
		  int num=0;
		  
		  String sql;
		  
		  try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select count(movieno) as num from movie";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
				if (rs.next())
					num = rs.getInt("num");


			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getNummovie() : " + ex.getMessage() );
			}

			return num;
	  }
	  
	  public static ResultSet getmovieRS(int movieno) {
		  String sql;
		  
		  try {
				// Statement 생성 
				Statement stmt = con.createStatement();

				sql = "select * from movie where movieno =' "+ movieno+"'";
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");

				ResultSet rs = stmt.executeQuery(sql);  
				
				return rs;

			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getmovie() : " + ex.getMessage() );
			}

			return null;
	  }
	  
	  public static movie getmovie(int movieno)  {	
			String sql;

			try {
				ResultSet rs = getmovieRS(movieno);  
				
				if (rs.next()) 
					return getmovieFromRS(rs);

			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getmovie() : " + ex.getMessage() );
			}

			return null;		
		}
	 
	  public static movie getmovieFromRS(ResultSet rs) {  
			movie m = new movie();

			try {
				
				m.movieno = rs.getString("movieno");
				m.title = rs.getString("title");
				m.date = rs.getDate("date");
				m.runtime=rs.getInt("runtime");
				m.story=rs.getString("story");
				m.avgGrade = rs.getDouble("avgGrade");
				m.cntsReview = rs.getInt("cntsReview");
				
			} catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in getmovieFromRS() : " + ex.getMessage() );
			}

			return m;
		}
	  
	  public static void insertmovie(movie m) {
		  int updateCnt=0;
		  
		  try {                      
				// SQL 질의문을 수행한다.
				String sql = "insert into movie  values (?, ?, ?, ?, ?,?,?,?);" ;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");
				PreparedStatement prStmt = con.prepareStatement(sql);

				prStmt.setString(1, m.movieno);
				prStmt.setString(2, m.title);
				prStmt.setString(3, m.genre);
				prStmt.setDate(4, m.date);
				prStmt.setInt(5, m.runtime);
				prStmt.setString(6, m.story);
				prStmt.setDouble(7, m.avgGrade);
				prStmt.setInt(8,m.cntsReview);

				updateCnt = prStmt.executeUpdate();  		
			} catch( SQLException ex ) {

				System.err.println("\n  ??? SQL exec error in insertmovie(): " + ex.getMessage() );
			}
		  if (updateCnt > 0)
				System.out.println("\n  << for debug >> " + updateCnt+" new movie was inserted into movie table\n\n");
			else
				System.out.println("  >> Error in Inserting New moviet\n\n"); 

			System.out.println("\n  << for debug >> total "+getNummovie()+" movies in movie table\n");
		}
	  
	  public static Vector getAllmovie() {
		  Vector movie =new Vector();
		  
		  movie m;
		  
		  ResultSet rs=getAllmovieRS();
		  
		  try {
				while (rs.next())  {
					m= getmovieFromRS(rs);
					movie.addElement(m);
				}
			} catch( SQLException ex ) 	    {
				System.err.println("** SQL exec error in getAllmovie() : " + ex.getMessage() );
			}	
			
			return movie;
		  
	  }
	  public static ResultSet getAllmovieRS()  {
			Vector movie = new Vector();
			
			movie acc;
			String sql;
			
			ResultSet rs = null;
			
			try {
			   // Statement 생성 
				Statement stmt = con.createStatement();
			    
			   sql = "select * from movie";
			   System.out.println("   >> SQL : " + sql + "\n");
			
			   rs = stmt.executeQuery(sql);  		   
			} catch( SQLException ex ) 	    {
			    System.err.println("** SQL exec error in getAllmovieRS() : " + ex.getMessage() );
			}
		
			return rs;	
		}
	  public static ResultSet getAllmovieRS2(String title)  {
			Vector movie = new Vector();
			
			movie acc;
			String sql;
			
			ResultSet rs = null;
			
			try {
			   // Statement 생성 
				Statement stmt = con.createStatement();
			    
				sql = "select * from movie where title='"+title+"';";
			   System.out.println("   >> SQL : " + sql + "\n");
			
			   rs = stmt.executeQuery(sql);  		   
			} catch( SQLException ex ) 	    {
			    System.err.println("** SQL exec error in getAllmovieRS() : " + ex.getMessage() );
			}
		
			return rs;	
		}
	  
	 
	  
	  
	  public static ResultSet getAllscreen_movieRS()  {
			Vector movie = new Vector();
			
			movie acc;
			String sql;
			
			ResultSet rs = null;
			
			try {
			   // Statement 생성 
				Statement stmt = con.createStatement();
			    
			   sql = "select DISTINCT title from screen";
			   System.out.println("   >> SQL : " + sql + "\n");
			
			   rs = stmt.executeQuery(sql);  		   
			} catch( SQLException ex ) 	    {
			    System.err.println("** SQL exec error in getAllmovieRS() : " + ex.getMessage() );
			}
		
			return rs;	
		}
		 public static void movieDelete(String movieno) {
			 int deleteCnt=0;
			 
			 try {
				 
				 String sql="delete from movie where movieno='"+movieno+"'";
				 PreparedStatement prStmt = con.prepareStatement(sql);
				 deleteCnt=prStmt.executeUpdate();
				 
				 
			 }catch(SQLException ex) {
				 System.out.println("\n  ??? SQL exec error in deletemovie(): " + ex.getMessage());
				 
			 }if (deleteCnt > 0)
					System.out.println("\n  << for debug >> " + deleteCnt+" and "+movieno+" was deleted into movie table\n\n");
				else
					System.out.println("  >> Error in deleting movie\n\n"); 

				System.out.println("\n  << for debug >> total "+getNummovie()+" movies in account table\n");
			}
			
	  
	  //상영관 추가
		 public static void insertscreentheater(screentheater screentheater) {
			 int updateCnt=0;
			 
			 try {
				// SQL 질의문을 수행한다.
					String sql = "insert into screentheater (screentheatername, screentheaterseat, theatername) values ( ?, ?,?);" ;
					System.out.println("\n  << for debug >> SQL : " + sql + "\n");
					PreparedStatement prStmt = con.prepareStatement(sql);

					prStmt.setString(1, screentheater.screentheatername);
					prStmt.setInt(2, screentheater.screentheaterseat);
					prStmt.setString(3, screentheater.theatername);
				
					
					updateCnt = prStmt.executeUpdate(); 
				 
			 }catch( SQLException ex ) {

					System.err.println("\n  ??? SQL exec error in insertscreentheater(): " + ex.getMessage() );
				}
			 
			 if(updateCnt >0)
				 System.out.println("\n  << for debug >> " + updateCnt+" new acount was inserted into screentheater table\n\n");
				else
					System.out.println("  >> Error in Inserting New screen\n\n"); 

				System.out.println("\n  << for debug >> total "+getNumScreentheater()+" screentheater in screentheater table\n");
			 
		 }
		 
		 
		 
		// screentheater 테이블의 투플 개수를 반환하는 메소드
		 public static int getNumScreentheater() {
			 int num=0;
			 String sql;
			 
			 try {
					// Statement 생성 
					Statement stmt = con.createStatement();

					sql = "select count(screentheater) as num from screentheater";
					System.out.println("\n  << for debug >> SQL : " + sql + "\n");

					ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
					if (rs.next())
						num = rs.getInt("num");


				} catch( SQLException ex ) 	    {
					System.err.println("\n  ??? SQL exec error in getNumScreentheater() : " + ex.getMessage() );
				}

				return num;
			 
		 }
		 
		 //상영관 테이블의 투플개수를 반환
		 public static int getNumscreentheater() {
			 int num=0;
			
			 String sql;

				try {
					// Statement 생성 
					Statement stmt = con.createStatement();

					sql = "select count(screentheater) as num from screentheater";
					System.out.println("\n  << for debug >> SQL : " + sql + "\n");

					ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
					if (rs.next())
						num = rs.getInt("num");


				} catch( SQLException ex ) 	    {
					System.err.println("\n  ??? SQL exec error in getNumscreentheater() : " + ex.getMessage() );
				}

				return num;
		 }
		 
		 public static ResultSet getAllscreentheaterRS() {
			 
			 String sql;
			 ResultSet rs = null;
			 
			 try {
				   // Statement 생성 
					Statement stmt = con.createStatement();
				    
				   sql = "select * from screentheater";
				   System.out.println("   >> SQL : " + sql + "\n");
				
				   rs = stmt.executeQuery(sql);  
				 
				} catch( SQLException ex ) 	    {
				    System.err.println("** SQL exec error in getAllScreentheaterRS() : " + ex.getMessage() );
				}
			
				return rs;	
		 }
		 
		 public static Vector getAllscreentheater() {
			 Vector screentheater=new Vector();
			 screentheater s;
			 ResultSet rs=getAllscreentheaterRS();
			 String sql;
			 
			 try {
				  
				  	Statement stmt = con.createStatement();

					sql = "select screentheater.theatername, screentheater.screentheatername, screentheater.screentheaterseat\r\n" + 
							"from screentheater";
					System.out.println("\n  << for debug >> SQL : " + sql + "\n");

					rs = stmt.executeQuery(sql); 
					
					while (rs.next())  {
						s= getscreentheaterFromRs(rs);
						screentheater.addElement(s);
					}
				} catch( SQLException ex ) 	    {
					System.err.println("** SQL exec error in getAllmovie() : " + ex.getMessage() );
				}	
				
				return screentheater;
		 }
		 
		

		public static screentheater getscreentheaterFromRs(ResultSet rs) {
			 screentheater s=new screentheater();
			 try {
				 s.screentheatername=rs.getString("screentheatername");
				 s.theatername=rs.getString("theatername");
				 s.screentheaterseat=rs.getInt("screentheaterseat");
			 }catch( SQLException ex ) 	    {
					System.err.println("\n  ??? SQL exec error in getscreentheaterFromRS() : " + ex.getMessage() );
				}

				return s;
		 }
		 
		
		
		
		 //리뷰 조희
		 public static Vector getAllreview(String memberno) {
			 
			  Vector ticketing =new Vector();
			  
			  ticketing m;
			  
			  ResultSet rs=getAllmovieRS();
			  String sql;
			  
			  try {
				  
				  	Statement stmt = con.createStatement();

					sql = "select * from ticketing where memberno = '"+memberno+"'";
					System.out.println("\n  << for debug >> SQL : " + sql + "\n");

					rs = stmt.executeQuery(sql); 
					
					while (rs.next())  {
						m= getticketingFromRS(rs);
						ticketing.addElement(m);
					}
				} catch( SQLException ex ) 	    {
					System.err.println("** SQL exec error in getAllmovie() : " + ex.getMessage() );
				}	
				
				return ticketing;	  
		  }
		 
		 public static Vector getAllreview() {
			 
			  Vector ticketing =new Vector();
			  
			  ticketing m;
			  
			  ResultSet rs=getAllmovieRS();
			  String sql;
			  
			  try {
				  
				  	Statement stmt = con.createStatement();

					sql = "select * from ticketing";
					System.out.println("\n  << for debug >> SQL : " + sql + "\n");

					rs = stmt.executeQuery(sql); 
					
					while (rs.next())  {
						m= getticketingFromRS(rs);
						ticketing.addElement(m);
					}
				} catch( SQLException ex ) 	    {
					System.err.println("** SQL exec error in getAllmovie() : " + ex.getMessage() );
				}	
				
				return ticketing;	  
		  }
		 
		  public static ResultSet getAllreviewRS()  {
				Vector ticketing = new Vector();
				
				ticketing acc ;
				String sql;
				
				ResultSet rs = null;
				
				try {
				   // Statement 생성 
					Statement stmt = con.createStatement();
				    
				   sql = "select ticketingno,title,ID,grade,reviewstory from ticketing,movie,member where movie.movieno=ticketing.movieno and ticketing.memberno=member.memberno";
				   System.out.println("   >> SQL : " + sql + "\n");
				
				   rs = stmt.executeQuery(sql);  		   
				} catch( SQLException ex ) 	    {
				    System.err.println("** SQL exec error in getAllreviewRS() : " + ex.getMessage() );
				}
			
				return rs;	
			}
		  
		  
		  //order
		  public static void insertsnack(snack s) {
			int updateCnt =0;

			try{
				//SQl 질의문을 수행한다.
				String sql = "insert into snack(snackmenu, snackprice) values (?, ?);" ;
				System.out.println("\n  << for debug >> SQL : " + sql + "\n");
				PreparedStatement prStmt = con.prepareStatement(sql);

				prStmt.setString(1, s.snackmenu);
				prStmt.setInt(2, s.snackprice);

				updateCnt = prStmt.executeUpdate();

				 }catch( SQLException ex ) {

					System.err.println("\n  ??? SQL exec error in snack(): " + ex.getMessage() );
				}

			 if(updateCnt >0)
					 System.out.println("\n  << for debug >> " + updateCnt+" new snack was inserted into screentheater table\n\n");
					else
						System.out.println("  >> Error in Inserting New screen\n\n"); 

				 
			 }
		  
		  public static Vector getAllSnack() {
			  Vector snacks = new Vector();
			  
			  snack s;
			  
			  ResultSet rs=getAllSnackRS();
			  try {
				  while (rs.next())  {
						s = getSnackFromRS(rs);
						snacks.addElement(s);
					}
				  
			  }catch( SQLException ex ) 	    {
					System.err.println("** SQL exec error in getAllAccounts() : " + ex.getMessage() );
				}	
				
			  return snacks;			  
		  }
		  public static ResultSet getAllSnackRS()  {
				Vector Snacks = new Vector();
				
				snack s;
				String sql;
				
				ResultSet rs = null;
				
				try {
				   // Statement 생성 
					Statement stmt = con.createStatement();
				    
				   sql = "select * from snack";
				   System.out.println("   >> SQL : " + sql + "\n");
				
				   rs = stmt.executeQuery(sql);  		   
				} catch( SQLException ex ) 	    {
				    System.err.println("** SQL exec error in getAllSnackRS() : " + ex.getMessage() );
				}
			
				return rs;	
			}
			public static snack getSnackFromRS(ResultSet rs) {  
				snack s = new snack();

				try {
					s.snackmenu = rs.getString("snackmenu");
					s.snackprice = rs.getInt("snackprice");
					
				} catch( SQLException ex ) 	    {
					System.err.println("\n  ??? SQL exec error in getAccountFromRS() : " + ex.getMessage() );
				}

				return s;
			}
		  
		  //theater
		  public static void inserttheater(theater theater) {
				int updateCnt =0;

				try{
					//SQl 질의문을 수행한다.
					String sql = "insert into theater(theaterno, theatername, address values (?, ?, ?);" ;
					System.out.println("\n  << for debug >> SQL : " + sql + "\n");
					PreparedStatement prStmt = con.prepareStatement(sql);

					prStmt.setString(1, theater.theaterno);
					prStmt.setString(2, theater.theatername);
					prStmt.setString(3, theater.address);

					updateCnt = prStmt.executeUpdate();

					 }catch( SQLException ex ) {

						System.err.println("\n  ??? SQL exec error in inserttheater(): " + ex.getMessage() );
					}

				 	if(updateCnt >0)
						 System.out.println("\n  << for debug >> " + updateCnt+" new theater was inserted into theater table\n\n");
						else
							System.out.println("  >> Error in Inserting New theater\n\n"); 

						
					 
				 }



		
			
		// 타이틀과 ResultSet 객체가 주어지면 ResultSetMetaData 객체를 이용하여 주어진 결과를 출력
		// 이 메소드는 select 문의 모든 수행결과를 출력할 수 있는 메소드임
		static void outputResultSet(String title, ResultSet rs) {
			try {		
				System.out.println("\n" + title + "\n");

				ResultSetMetaData  rsm = rs.getMetaData();   // 메타데이터 객체를 가져옴

				int cnt = rsm.getColumnCount() ;    // 메타데이터 객체에서 ResultSet 객체의 애트리뷰트 개수를 가져옴

				while(rs.next()) {  //  ResultSet 객체의 모든 투플에 대하여
					for (int i=1; i<=cnt; i++) {
						String attrTitle = rsm.getColumnLabel(i);        // 애트리뷰트의 타이틀을 가져옴
						Object obj = rs.getObject(i);                    // getObject(): 타입을 고려하지 않고 애트리뷰트 값을 가져옴
						System.out.print("\t" + attrTitle + ": " + obj); // Object 객체 obj의 overriding 메소드인 
						//     toString()을 통하여 문자열로 출력됨
					}
					System.out.println();
				}
			}
			catch (SQLException e) {
				System.err.println("\n  ?? SQL문 결과출력 에러:" + e.getMessage() );
			}

			System.out.println("\n");
		}
		
		public static void reivewDelete(String ticketingno) {
		       int deleteCnt=0;
		       
		       try {
		          
		          String sql="delete from ticketing where ticketingno='"+ticketingno+"'";
		          PreparedStatement prStmt = con.prepareStatement(sql);
		          deleteCnt=prStmt.executeUpdate();
		          
		       }catch(SQLException ex) {
		          System.out.println("\n  ??? SQL exec error in deletereviewstory(): " + ex.getMessage());
		          
		       }if (deleteCnt > 0)
		            System.out.println("\n  << for debug >> " + deleteCnt+" and "+ticketingno+" was deleted into ticketing table\n\n");
		         else
		            System.out.println("  >> Error in deleting reviewstory\n\n"); 

		         //System.out.println("\n  << for debug >> total "+getNumreviewstory()+" reviewstory in cancle table\n");
		      }
		
		//극장관리
		public static Vector getAlltheater() {
            Vector theaters = new Vector();

            theater t;

            ResultSet rs=getAlltheaterRS();
            
            try {
                while (rs.next())  {
                      t = getAlltheaterFromRS(rs);
                      theaters.addElement(t);
                  }

            }catch( SQLException ex )         {
                  System.err.println("** SQL exec error in getAlltheater() : " + ex.getMessage() );
              }

            return theaters;
        }
		
		public static ResultSet getAlltheaterRS() {
			Vector theaters= new Vector();
			
			theater t;
			String sql;
			
			ResultSet rs = null;
			
			try {
			   // Statement 생성 
				Statement stmt = con.createStatement();
			    
			   sql = "select screentheater.theatername, theater.address,count( screentheater.screentheatername), sum(screentheater.screentheaterseat)\r\n" + 
			   		"from screentheater, theater\r\n" + 
			   		"where screentheater.theatername=theater.theatername\r\n" + 
			   		"group by screentheater.theatername";
			   
			   System.out.println("   >> SQL : " + sql + "\n");
			
			   rs = stmt.executeQuery(sql);  		   
			} catch( SQLException ex ) 	    {
			    System.err.println("** SQL exec error in getAlltheaterRS() : " + ex.getMessage() );
			}
		
			return rs;	
		}
		
		public static theater getAlltheaterFromRS(ResultSet rs){
			theater t=new theater();
			try {
				t.theaterno=rs.getString("theaterno");
				t.theatername=rs.getString("theatername");
				t.address=rs.getString("address");
			}catch( SQLException ex ) 	    {
				System.err.println("\n  ??? SQL exec error in gettheaterFromRS() : " + ex.getMessage() );
			}

			return t;
			
		}
		
		 public static void theaterDelete(String theatername) {
			 int deleteCnt=0;
			 
			 try {
				 
				 String sql="delete from theater where theatername='"+theatername+"'";
				 PreparedStatement prStmt = con.prepareStatement(sql);
				 deleteCnt=prStmt.executeUpdate();
				 
			 }catch(SQLException ex) {
				 System.out.println("\n  ??? SQL exec error in deletetheater(): " + ex.getMessage());
				 
			 }
		    	 if (deleteCnt > 0)
					System.out.println("\n  << for debug >> " + deleteCnt+" and "+theatername+" was deleted into theater table\n\n");
				else
					System.out.println("  >> Error in deleting  theater\n\n"); 

			}
		 
		//상영 추가 
		 public static Vector getAllscreen() {
		            Vector screens = new Vector();
		            
		            screen s;
		            
		            ResultSet rs=getAllscreenRS();
		            try {
		               while (rs.next())  {
		                   s = getscreenFromRS(rs);
		                   screens.addElement(s);
		                }
		               
		            }catch( SQLException ex )        {
		                System.err.println("** SQL exec error in getAllscreen() : " + ex.getMessage() );
		             }   
		             
		            return screens;           
		         }
		 
		 
		 public static screen getscreenFromRS(ResultSet rs) {
			 screen s = new screen();

				try {
					s.screendate=rs.getDate("screendate");
					s.screentime=rs.getTime("screentime");
					s.title=rs.getString("title");
					s.screenround=rs.getInt("screenround");
					s.theatername=rs.getString("theatername");
					s.screentheatername=rs.getString("screentheatername");
				} catch( SQLException ex ) 	    {
					System.err.println("\n  ??? SQL exec error in getscreenFromRS() : " + ex.getMessage() );
				}

				return s;
			}



		//상영 삭제 
		public static void screenDelete(String title) {
		       int deleteCnt=0;
		       
		       try {
		          
		          String sql="delete from screen where title='"+title+"'";
		          PreparedStatement prStmt = con.prepareStatement(sql);
		          deleteCnt=prStmt.executeUpdate();
		          
		       }catch(SQLException ex) {
		          System.out.println("\n  ??? SQL exec error in deletescreen(): " + ex.getMessage());
		          
		       }if (deleteCnt > 0)
		            System.out.println("\n  << for debug >> " + deleteCnt+" and "+title+" was deleted into screen table\n\n");
		         else
		            System.out.println("  >> Error in deleting New screen\n\n"); 
		      }
		
		//상영관 삭제
		public static void screentheaternameDelete(String screentheatername,String theatername) {
		       System.out.println("\n  ** Deleting a student tuple **\n");
		      // ResultSet rs = null;
		       
		       try { 
		    	  Statement stmt = con.createStatement();
		          String sql="delete from screentheater where screentheatername ='"+screentheatername+"'and theatername='"+theatername+"'";
		          System.out.println("  * sql = "+ sql);
		          stmt.executeUpdate(sql);
		          
		       }catch(SQLException ex) {
		          System.out.println("\n  ??? SQL exec error in deletescreentheatername(): " + ex.getMessage());
		          
		         }
		      }
		//상영 삭제 
		public static void screenDelete(Date date, Time time, String theatername, String screentheatername) {
		       int deleteCnt=0;
		       
		       try {
		          
		          String sql= "delete from screen where screendate="+date+" and screentheatername="+screentheatername+" and screentime ="+time+"and theatername="+theatername;
		          PreparedStatement prStmt = con.prepareStatement(sql);
		          deleteCnt=prStmt.executeUpdate();
		          
		       }catch(SQLException ex) {
		          System.out.println("\n  ??? SQL exec error in deletescreen(): " + ex.getMessage());
		          
		       }
		}
		
		  public static theater gettheaterFromRS(ResultSet rs) {
			 theater t =new theater();
			  
			  try {
					t.theatername = rs.getString("theatername");
					
				} catch( SQLException ex ) 	    {
					System.err.println("\n  ??? SQL exec error in getAccountFromRS() : " + ex.getMessage() );
				}

				return t;
		  }
		  
		  public static ResultSet getAllreviewRS2(String title)  {
				Vector ticketing = new Vector();
				
				ticketing acc ;
				String sql;
				
				ResultSet rs = null;
				
				try {
				   // Statement 생성 
					Statement stmt = con.createStatement();
				    
				   sql = "select title, memberno, reviewstory from ticketing,movie where "+
				   "movie.movieno=ticketing.movieno and movie.title='"+title+"'; ";
				   System.out.println("   >> SQL : " + sql + "\n");
				
				   rs = stmt.executeQuery(sql);  		   
				} catch( SQLException ex ) 	    {
				    System.err.println("** SQL exec error in getAllreviewRS() : " + ex.getMessage() );
				}
			
				return rs;	
			}
		  public static ResultSet getticketingg(String memberno) {
		        
		        ticketing t;     
		        String sql;   
		        ResultSet rs = null;
		         
		        
		        try {
		            // Statement 생성 
		            Statement stmt = con.createStatement();

		            //sql = "select * from ticketing where memberno = '"+memberno+"'";
		            sql = "select ticketing.date as 날짜, movie.title as 영화, theater.theatername as 극장,"+
		            "ticketing.screentheatername as 상영관, ticketing.time as 시간,"
		            		+ " ticketing.ticketingseats as 좌석 from ticketing,movie,theater "
		            				+ "where movie.movieno=ticketing.movieno and theater.theaterno="
		            						+ "ticketing.theaterno and memberno = '"+memberno+"'";
		         
		            System.out.println("\n  << for debug >> SQL : " + sql + "\n");

		            rs = stmt.executeQuery(sql);  

		         } catch( SQLException ex )        {
		            System.err.println("\n  ??? SQL exec error in getTransactions() : " + ex.getMessage() );
		         }

		         return rs;   
		     }

		public static ResultSet getfastorderr(String memberno) {

		         fastorder tr;
		         String sql;
		         
		         ResultSet rs = null;
		         
		         try {
		            // Statement 생성 
		            Statement stmt = con.createStatement();

		            //sql = "select * from fastorder where memberno = '"+memberno+"'";
		            sql = "select fastorder.snackmenu as 상품, fastorder.snackstatus as 상태 "+
		            "from fastorder where memberno = '"+memberno+"'";

		            System.out.println("\n  << for debug >> SQL : " + sql + "\n");

		            rs = stmt.executeQuery(sql);  


		         } catch( SQLException ex )        {
		            System.err.println("\n  ??? SQL exec error in getfastorder() : " + ex.getMessage() );
		         }

		         return rs;
		     }

		public static ResultSet getAllrevieww(String memberno) {
		           
		           ticketing m;
		           
		           ResultSet rs=null;
		           String sql;
		           
		           try {
		              
		                 Statement stmt = con.createStatement();

		               //sql = "select * from ticketing where memberno = '"+memberno+"'";
		                 sql = "select ticketing.date as 날짜, movie.title as 영화, "+
		                		 "ticketing.grade as 평점, ticketing.reviewstory as 리뷰내용"+
		                		 "from ticketing,movie,theater where movie.movieno=ticketing.movieno "+
		                		 "and theater.theaterno=ticketing.theaterno and memberno = '"+memberno+"'";
		               System.out.println("\n  << for debug >> SQL : " + sql + "\n");

		               rs = stmt.executeQuery(sql); 
		               
		            } catch( SQLException ex )        {
		               System.err.println("** SQL exec error in getAllmovie() : " + ex.getMessage() );
		            }   
		            
		            return rs;     
		        }
		
		//선택한 영화제목에 따른 극장,상영관, 날짜, 시간  뽑기
		  public static ResultSet choicemovie_after_other2(String title) {
			 
			  ticketing t;	  
			  String sql;	
			  ResultSet rs = null;
			  
			  try {
					// Statement 생성 
					Statement stmt = con.createStatement();

					sql = "select screen.theatername, screen.screentheatername,screen.screendate, screen.screentime from screen where title = "+ title;
					System.out.println("\n  << for debug >> SQL : " + sql + "\n");

					rs = stmt.executeQuery(sql);  
					

				} catch( SQLException ex ) 	    {
					System.err.println("\n  ??? SQL exec error in getTransactions() : " + ex.getMessage() );
				}

				return rs;
		  }
	}