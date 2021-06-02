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
		#goMain{text-decoration:none;color:rgb(155, 89, 182); font-size:35pt; }
		h1{color:rgb(155, 89, 182); font-size:35pt; }
		div{text-align:center;}
		div div{float:right; text-align:left;}
		
		nav{width:850px; height:40px; margin:0 auto;}
		nav ul li{list-style:none; color:black; background:rgb(222, 196, 246); float:left; line-height:40px; vertical-align:middle; text-align:center;}
		nav ul li a{text-decoration:none; color:black; font-weight:bold; display:block; width:190px; font-size:13px;}
		nav ul li a:hover{background:rgb(231, 220, 231);}
		
		#showContent{float:left; text-align:left;  margin-left:80px;margin-right:80px;}
		p{width:380px; height:400px; background:rgb(231, 220, 231); padding:10px; margin-bottom:30px; margin-right:30px; float:left;}
		a {text-decoration:none;}
		table{border:1px solid rgb(212, 205, 214); border-collapse:collapse;width:1000px;height:70px; text-align:center;}
		thead{background:rgb(241, 240, 240);}
		td,th{border:1px solid rgb(212, 205, 214);}
	</style>
</head>
<body>
	<header>
		<div><a id="goMain" href="MgMain.jsp"><h1>CGV_HS</a></h1>
		<h1><%= request.getParameter("id") %>관리자님 <small>반갑습니다.</small></h1>
        <button type="button" onclick="location.href='LoginForm.html' ">로그아웃</button>
		</div>
	</header>

	
	<nav>
		<div><ul>
			<li><a href="MgMember.jsp" >회원 관리</a></li>
			<li><a href="MgTheater.jsp">극장 관리</a></li>
			<li><a href="MgScreenTheater.jsp">상영관 관리</a></li>
			<li><a href="MgMovie.jsp">영화 관리</a></li>
			<li><a href="MgScreen.jsp">상영 관리</a></li>
			<li><a href="MgTicketing.jsp">예매 관리</a></li>
			<li><a href="MgFastOrder.jsp">패스트오더</a></li>
			<li><a href="MgReview.jsp">후기관리</a></li>
		</ul></div>
	</nav>
	
	<section>
	<div id="showContent">
		<h4 id="show">상영관리  &gt; </h4>	
		<%  
   	        request.setCharacterEncoding("euc-kr");
            
            DB.loadConnectCGVhs();
            
 	        //상영 모든 정보 
 	        ResultSet rs = DB.getAllscreenRS();
	       
	        request.setAttribute("title", "모든 상영 조회"); 	        
	        request.setAttribute("RS", rs); 

	        request.getRequestDispatcher("MglistRS.jsp").forward(request, response);
       %>    
	</div>
	</section>
</body>
</html>