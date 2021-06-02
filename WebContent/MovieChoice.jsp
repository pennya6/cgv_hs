<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.sql.*,CGV_HS.*, java.util.Date" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CGV_HS</title>
<style>
body {
	text-align: center;
}

header {
	width: 99%;
	height: 150px;
	text-align: center;
}

#goMain {
	text-decoration: none;
	color: rgb(155, 89, 182);
	font-size: 35pt;
}

div {
	text-align: center;
}

div div {
	float: right;
	text-align: left;
}

nav {
	width: 850px;
	height: 40px;
	margin: 0 auto;
}

nav ul li {
	list-style: none;
	color: black;
	background: rgb(222, 196, 246);
	float: left;
	line-height: 40px;
	vertical-align: middle;
	text-align: center;
}

nav ul li a {
	text-decoration: none;
	color: black;
	font-weight: bold;
	display: block;
	width: 250px;
	font-size: 13px;
}

nav ul li a:hover {
	background: rgb(231, 220, 231);
}

#showContent {
	float: center;
	text-align: left;
	margin-left: 80px;
	margin-right: 80px;
}

#show {
	width: 100%;
	text-align: left;
	float: left;
	display: block;
}

p {
	width: 380px;
	height: 400px;
	background: rgb(241, 240, 240);
	padding: 10px;
	margin-bottom: 30px;
	margin-right: 47px;
	float: left;
}

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<header>
	<div>
		<a id="goMain" href="Main.html"><h1>CGV_HS</a>
		</h1>
		<%

        DB.loadConnectCGVhs();
        
	        //상영 모든 정보 
	        ResultSet rs = DB.getAllscreen_movieRS();
       
        request.setAttribute("title", "모든 영화 조회"); 	        
        request.setAttribute("RS", rs); 

        request.getRequestDispatcher("ClistRS.jsp").forward(request, response);
		%>
		
		<div>
			<button>
				<a href="Main.html">로그아웃</a>
			</button>
		</div>
	</div>
	</header>

	<nav>
	<div>
		<ul>
			<li><a href="MovieChoice.jsp">영화 예매</a></li>
			<li><a href="FastOrder.jsp">패스트 오더</a></li>
			<li><a href="MyPage">마이 페이지</a></li>
		</ul>
	</div>
	</nav>

	<section>
	
	</section>
</body>
</html>
