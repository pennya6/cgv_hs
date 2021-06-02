<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.sql.*,CGV_HS.*, java.util.* "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- <script>
 function enrolIdPassword() {
	 window.top.location='enrolIdPassword/systemEnrolIdPassword.html'; 
 }
</script> -->
<body>
         
<%
      request.setCharacterEncoding("euc-kr");

    
      DB.loadConnectCGVhs();
      
      member m = new member();
      
     //insert
      m.setmemberno(request.getParameter("memberno"));//c+투플갯수;
      m.setID(request.getParameter("id"));
      m.setPW(request.getParameter("pw"));
      m.setname( request.getParameter("name"));
      m.setphoneno(request.getParameter("number"));
      m.setDatebirth(java.sql.Date.valueOf(request.getParameter("birth")));
      
      DB.insertmember(m);
		 

	  response.sendRedirect("LoginForm.html");
     
%> 


</body>
</html>