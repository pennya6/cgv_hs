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


     if(title.equals("영화 리뷰 목록")){
 	    
 	 for(int i=0;i<col_0.length;i++){
 		 DB.reivewDelete(col_0[i]);
   	 }
  	 ResultSet rs = DB.getticketingg("c1");
	       
	        request.setAttribute("title", title); 	        
	        request.setAttribute("RS", rs); 
	        
	        request.getRequestDispatcher("listRS.jsp").forward(request, response);
   }

     else if(title.equals("패스트오더 목록")){
  	    
     	 for(int i=0;i<col_0.length;i++){
     		 DB.reivewDelete(col_0[i]);
       	 }
      	 ResultSet rs = DB.getfastorderr("c1");
    	       
    	        request.setAttribute("title", title); 	        
    	        request.setAttribute("RS", rs); 
    	        
    	        request.getRequestDispatcher("listRS.jsp").forward(request, response);
       }
     else if(title.equals("영화 리뷰 목록")){
   	    
     	 for(int i=0;i<col_0.length;i++){
     		 DB.reivewDelete(col_0[i]);
       	 }
      	 ResultSet rs = DB.getAllreviewRS();
    	       
    	        request.setAttribute("title", title); 	        
    	        request.setAttribute("RS", rs); 
    	        
    	        request.getRequestDispatcher("listRS.jsp").forward(request, response);
       }
     else
%>

</body>
</html>