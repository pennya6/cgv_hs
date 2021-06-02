<%@page import="java.sql.Date"%>
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
      
     Object title = session.getAttribute("title");
     String[] col_0 = request.getParameterValues("col_0");
     String[] col_1 = request.getParameterValues("col_1");
     String[] col_2 = request.getParameterValues("col_2");
     String[] col_4 = request.getParameterValues("col_4");
     String[] col_5 = request.getParameterValues("col_5");


     if(title.equals("모든 고객 조회")){
    	 
      	 for(int i=0;i<col_0.length;i++){
      		 DB.memberDelete(col_0[i]);
      	 }

	        ResultSet rs = DB.getAllmemberRS();
	       
	        request.setAttribute("title", title); 	        
	        request.setAttribute("RS", rs); 
	        
	        request.getRequestDispatcher("MglistRS.jsp").forward(request, response);

     }
     else if(title.equals("모든 영화 조회")){
   	     
    	 for(int i=0;i<col_0.length;i++){
    		DB.movieDelete(col_0[i]);
      	 }
   	     
    	 ResultSet rs = DB.getAllmovieRS();
	       
	        request.setAttribute("title", title); 	        
	        request.setAttribute("RS", rs); 
	        
	        request.getRequestDispatcher("MglistRS.jsp").forward(request, response);
     }
     else if(title.equals("모든 극장 조회")){
   	  
   	     
    	 for(int i=0;i<col_0.length;i++){
    		 DB.theaterDelete(col_0[i]);

    		 System.out.println(col_0[i]);
      	 }
    	 
    	 ResultSet rs = DB.getAlltheaterRS();
	       
	        request.setAttribute("title", title); 	        
	        request.setAttribute("RS", rs); 
	        
	        request.getRequestDispatcher("MglistRS.jsp").forward(request, response);
     }
     else if(title.equals("모든 상영관 조회")){
    	 
    	 
    	 for(int i=0;i<col_0.length;i++){
    		 DB.screentheaternameDelete(col_0[i], col_2[i]);
      	 } 
    	 
    	 ResultSet rs = DB.getAllscreentheaterRS();
	       
	        request.setAttribute("title", title); 	        
	        request.setAttribute("RS", rs); 
	        
	        request.getRequestDispatcher("MglistRS.jsp").forward(request, response);
     }
     else if(title.equals("모든 상영 조회")){
   	    //DB.screenDelete(date, time, theatername, screentheatername);
   	     
       
   	    
    	/*  for(int i=0;i<col_0.length;i++){
              Date d = Date.valueOf(col_0[i]);
              Time t = Time.valueOf(col_1[i]);
    		 DB.screenDelete(d, t, col_4[i], col_5[i]);
      	 }  */
    	 ResultSet rs = DB.getAllscreenRS();
	       
	        request.setAttribute("title", title); 	        
	        request.setAttribute("RS", rs); 
	        
	        request.getRequestDispatcher("MglistRS.jsp").forward(request, response);
     }
     else if(title.equals("모든 리뷰 조회")){
    	    
    	 for(int i=0;i<col_0.length;i++){
    		 DB.reivewDelete(col_0[i]);
      	 }
     	 ResultSet rs = DB.getAllreviewRS();
 	       
 	        request.setAttribute("title", title); 	        
 	        request.setAttribute("RS", rs); 
 	        
 	        request.getRequestDispatcher("MglistRS.jsp").forward(request, response);
      }
     
%>

</body>
</html>