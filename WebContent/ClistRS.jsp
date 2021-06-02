<%@page import="java.sql.Date"%>
<%@page import="com.sun.tools.javac.util.Convert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.sql.*,CGV_HS.*, java.util.* "%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
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

  
  	   <br>
	   <H3 align=center > <% out.print(request.getAttribute("title"));   %> </H3>
	    
 	<%	
 	 	   request.setCharacterEncoding("euc-kr");
 	
 	       ResultSet rs = (ResultSet) request.getAttribute("RS");
 	      ResultSet rs2 = (ResultSet) request.getAttribute("RS2");
 	       
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
       
		
			if (cntTuples == 1) { // 투플 개수가 1이면 한 라인에 애트리뷰트명과 애트리뷰트 이름을 출력
			    out.print("<table align=center valign=top border=1 cellpadding=8 cellspacing=0 bordercolor=#999999>");
			
				rs.next(); // ResultSet의 커서 이동

				for(int i=0; i<columns.length; i++){
					out.print("<tr><td bgcolor=#DDDDDD>" + columns[i]  + "</td >" + "<td > &nbsp;" +  rs.getObject(columns[i])  + "&nbsp;</td></tr>");	
				}
				
				out.print("</table>");

				return;
			}
			
	    out.print("<table align=center valign=top border=1 cellpadding=8 cellspacing=0 bordercolor=#999999>");
			out.print("<tr bgcolor=#DDDDDD>" );
			for(int i=0; i<columns.length; i++){
				out.print("<th>" + columns[i]  + "</th>" );	
			}
			out.print("<th>예매</th>" );	
			out.print("</tr>" );

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
						out.print("<td align=right>" + obj + "</td>");	
					else if (columnTypes[i].equals("VARCHAR") && ((String) obj).equals(""))
					     out.print("<td> &nbsp; </td>");	
					else if (columnTypes[i].equals("VARCHAR") )
						out.print("<td align=left>"+ obj + "</td>");	
 			     	else
					     out.print("<td>" + obj + "</td>");	
					
				}
				Object col_0[] = new Object[50];
				col_0[n]= rs.getObject(columns[0]);
				out.print("<td>" );
				
				System.out.println(columns[0]);
				
				if(rs.getObject(columns[0]).equals("극장판 귀멸의 칼날-무한열차편"))
				{ %>
				<button type="button" onclick="location.href='MovieChoice4-movie1.jsp' ">영화선택</button> 
				<button type="button" onclick="location.href='MovieChoice4-movie1-review1.jsp' ">리뷰보기</button> <%
				}
				else if(rs.getObject(columns[0]).equals("서복")){%>
				
				<button type="button" onclick="location.href='MovieChoice4-movie2.jsp' ">영화선택</button> 
				<button type="button" onclick="location.href='MovieChoice4-movie1-review2.jsp' ">리뷰보기</button>
				<%
				}else if(rs.getObject(columns[0]).equals("내일의 기억")){%>
				<button type="button" onclick="location.href='MovieChoice4-movie3.jsp' ">영화선택</button> 
				<button type="button" onclick="location.href='MovieChoice4-movie1-review3.jsp' ">리뷰보기</button>
				<% 
				}else if(rs.getObject(columns[0]).equals("명탐정 코난-비색의 탄환")){ %>
					<button type="button" onclick="location.href='MovieChoice4-movie4.jsp' ">영화선택</button>
					<button type="button" onclick="location.href='MovieChoice4-movie1-review4.jsp' ">리뷰보기</button>
					<% 
				}

			
		
			n++;
			}
			 
     %>
	  </table >
	 
	  
	  
  </body>
</html>
