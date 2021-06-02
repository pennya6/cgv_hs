<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.sql.*,CGV_HS.*, java.util.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CGV_HS</title>
	<style>
      body{text-align:center;}
      header{width:99%; height:150px; text-align:center;}
      #goMain{text-decoration:none;color:rgb(155, 89, 182); font-size:35pt;}
      div{text-align:center;}
      div div{float:right; text-align:left;}
      
      nav{width:850px; height:40px; margin:0 auto;}
      nav ul li{list-style:none; color:black; background:rgb(222, 196, 246); float:left; line-height:40px; vertical-align:middle; text-align:center;}
      nav ul li a{text-decoration:none; color:black; font-weight:bold; display:block; width:250px; font-size:13px;}
      nav ul li a:hover{background:rgb(231, 220, 231);}
      
      #showContent{float:center; text-align:center; margin-left:80px; margin-right:80px;}
      #show{width:100%; text-align:left; float:left; display:block;}
      a {text-decoration:none;}
      table{border:1px solid rgb(212, 205, 214); border-collapse:collapse; width:100%; height:70px; text-align:center;}
      thead{background:rgb(241, 240, 240);}
      td,th{border:1px solid rgb(212, 205, 214);}
   </style>

</head>
<body>
	<header>
      <div><a id="goMain" href="CMain.jsp"><h1>CGV_HS</a></h1>
      <div><%-- <%= request.getAttribute("id") %>님 반갑습니다. --%>
        <button type="button" onclick="location.href='LoginForm.html' ">로그아웃</button></div>
      </div>
   </header>

	
	<nav>
		<div><ul>
			<li><a href="MovieChoice.jsp" >영화 예매</a></li>
			<li><a href="FastOrder.jsp">패스트 오더</a></li>
			<li><a href="MyPage.jsp">마이 페이지</a></li>
		</ul></div>
	</nav>
<section>
	<div id="showContent">
		<h4 id="show"><!-- 마이 페이지  &gt; --> <% out.print(request.getAttribute("title"));   %><br><br><br> </h4><br><br>
		<!-- <h5><% out.print(request.getAttribute("title"));   %> &gt;</h5>-->

 	<%	
 	 	   request.setCharacterEncoding("euc-kr");
 	
 	       ResultSet rs = (ResultSet) request.getAttribute("RS");
 	       Object title = request.getAttribute("title");
 	       
			rs.last();  // rs 커서를 끝으로 이동하여 투플 개수 확인
			int cntTuples = rs.getRow();  // 투플 개수를 구함
			rs.beforeFirst();  // rs 커서를 처음으로 이동
System.out.print("   >> cntTuples = " + cntTuples + "\n");	
			
			if (cntTuples == 0) { // 투플 개수가 0이면
				out.println("<center>(결과 없음)</center>");
				return;
			}
			
 	       
 System.out.print("   >> rs : " + rs + "\n");	       
 
		   ResultSetMetaData md = rs.getMetaData();
		   int count = md.getColumnCount();
		   String[] columns = new String[count];
		   String[] columnTypes = new String[count];
		   for(int i=0; i<count; i++){
			   columns[i] = md.getColumnLabel(i+1);
			   columnTypes[i] = md.getColumnTypeName(i+1);
System.out.print("   >> clms : " + columns[i] + " " + columnTypes[i]+ "\n");	
     	   }
	  %>
       <%
       
		
			if (cntTuples == 0) { // 투플 개수가 1이면 한 라인에 애트리뷰트명과 애트리뷰트 이름을 출력, 이거는 없앴음
			    out.print("<table align=center valign=top border=1 cellpadding=8 cellspacing=0 bordercolor=#999999>");
			
				rs.next(); // ResultSet의 커서 이동

				for(int i=0; i<columns.length; i++){
					out.print("<tr><td bgcolor=#DDDDDD>" + columns[i]  + "</td >" + "<td > &nbsp;" +  rs.getObject(columns[i])  + "</tr>");
				}
				out.print("</table>");

				return;
			}
			
	    out.print("<table align=center valign=top border=1 cellpadding=8 cellspacing=0 bordercolor=#999999>");
			out.print("<tr bgcolor=#DDDDDD>" );
			for(int i=0; i<columns.length; i++){
				out.print("<th>" + columns[i]  + "</th>" );	
			}
			
			if(title.equals("패스트오더 주문")) {
			out.print("<th>주문</th>" );	
			out.print("</tr>" );}
			
			int n=0;
			while(rs.next()) {
				out.print("<tr>" );

				for(int i=0; i<columns.length; i++){

					Object obj= rs.getObject(columns[i]);
 // System.out.print("   >> col value : " + (obj) + "\n");
					if (obj == null)    // null 객체이면 null을 출력
						out.print("<td> null </td>");
					else if (columnTypes[i].equals("INTEGER") || columnTypes[i].equals("FLOAT")
			                             || columnTypes[i].equals("DOUBLE") || columnTypes[i].equals("BIGINT") )
						out.print("<td align=right>" + "<center>"+ obj+"</center>" + "</td>");	
					else if (columnTypes[i].equals("VARCHAR") && ((String) obj).equals(""))
					     out.print("<td> &nbsp; </td>");	
					else if (columnTypes[i].equals("VARCHAR") )
						out.print("<td align=left>"+ "<center>"+ obj+"</center>"+ "</td>");	
 			     	else
					     out.print("<td>" +"<center>"+ obj+"</center>" + "</td>");	
					
				}
				
				//delete위한 변수
				Object[] col_0 = new Object[50];
				Object[] col_2 = new Object[50];
				
				
				%> 
				<%if(title.equals("패스트오더 주문")) {
				out.print("<td>" );
				col_0[n]= rs.getObject(columns[0]); //memberno,movieno,theatername,screentheaternames%>
				<form action="Cdelete.jsp" method="post">
				<input type="checkbox" name="col_0" value="<%= col_0[n] %>")/><%
				
                if(title.equals("모든 상영관 조회")){
			    	col_2[n]= rs.getObject(columns[2]); //screentheater 삭제 때문에 
                	%><input type="hidden" name="col_2" value="<%= col_2[n] %>")/><% 
                }
                else if(title.equals("모든 상영 조회")){
                }
                out.print("</td>" );}
%>
				<script>
                function delete_click() {
                    
                    <%  session.setAttribute("title", title);%>
                    
                    location.href="Cdelete.jsp";  
               
               }
               </script>
				 <% 

				n++;
				out.print("</tr>" );
				 

			  }    %>

	  </table><br>
	  <%if(title.equals("패스트오더 주문")) {%>
				
	    <input type="submit" id="valueId" VALUE="주문" onClick="delete_click();")/><%} %>
	  
	  </form>
       </div></section>
  </body>
</html>
