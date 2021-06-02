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

       request.setCharacterEncoding("utf-8"); 

       String stname = request.getParameter("stname");
       int seats = Integer.parseInt(request.getParameter("seats"));
       String t_name = request.getParameter("t_name");
       
       screentheater st = new screentheater();
       
       System.out.println(stname);
       System.out.println(seats);
       System.out.println(t_name);
       
       st.setscreentheatername(stname);
       st.setscreentheaterseat(seats);
       st.settheatername(t_name);
       
       DB.insertscreentheater(st);
      
       request.getRequestDispatcher("MgScreenTheaterAdd.jsp").forward(request, response);
%>
</body>
</html>