<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.sql.*,CGV_HS.*, java.util.* "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	 request.setCharacterEncoding("euc-kr");
	
	String ID = request.getParameter("id");
	String password = request.getParameter("pw");
	
	System.out.println("  <<for debug >> loginID: '" + ID + "', password='" + password + "'\n");      
	
	if (ID.equals(" ") || password.equals(" ")) {
	     out.print("<script>alert('로그인 아이디와 패스워드가 모두 입력되지 않았습니다.')</script>");
	     
	     out.print("<script>window.top.frames[0].location='topFrameForLogin.html'</script>");
	}
	 
	String encriptedPassword = member.encriptPassword(password);  // 은행원 패스워드를 암호화하여 암호화된 패스워드를 구함
	  member memberLogin = DB.getmember(ID, encriptedPassword);
	System.out.println("  <<for debug >> loginID: '" + ID + "', password='" + password + "', encriptedPassword = '" + encriptedPassword + "'\n");      
	
	if (memberLogin == null) {
	     out.print("<script>alert('아이디: " + ID + ", 패스워드: " + password + " - 잘못된 아이디 또는 패스워드입니다.')</script>");     
	     
	     out.print("<script>window.top.location='systemForLogin.html'</script>");
	
	}
	else {
	
	     System.out.println("  <<for debug >> 로그인한 member 아이디 : " + memberLogin.getID() + ", member 이름: " + memberLogin.getname() + "\n");
	
	     session.setAttribute("memberLogin", memberLogin);  // // 세션 객체에 로그인한 은행원 객체 bankerLogin를 이름 "bankerLogin"로 저장
	  
	     if (memberLogin instanceof Manager){
	        out.print("<script>window.top.location='MgMain.jsp'</script>"); 
	      }
	     else if (memberLogin instanceof Customer){
	        out.print("<script>window.top.location='CMain.jsp'</script>"); 
	     }
	}   
    /* DB.loadConnectCGVhs();

   String ID = request.getParameter("id");
   String PW = request.getParameter("pw");
   
   
 
   //로그인 ID,PW 있는지 확인
   
   if(DB.getmember(ID, PW)!=null){
   if( DB.getmember(ID, PW).getmemberno().contains("c")){
   //아이디가 관리자인지 고객인지 구분
   //고객이면 CMain으로
   //관리자이면 MgMain으로
           
		   response.sendRedirect("CMain.jsp");
   
   }
   else if( DB.getmember(ID, PW).getmemberno().contains("mg")){

	      response.sendRedirect("MgMain.jsp");
   }}
   else  response.sendRedirect("LoginForm.html");  */
 
%>
</body>
</html>