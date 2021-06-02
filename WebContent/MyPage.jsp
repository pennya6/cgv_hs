<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CGV_HS</title>
	<style>
		body{text-align:center;}
		header{width:99%; height:150px; text-align:center;}
		#goMain{text-decoration:none;color:rgb(155, 89, 182); font-size:35pt; }
		div{text-align:center;}
		div div{float:right; text-align:left;}
		
		nav{width:850px; height:40px; margin:0 auto;}
		nav ul li{list-style:none; color:black; background:rgb(222, 196, 246); float:left; line-height:40px; vertical-align:middle; text-align:center;}
		nav ul li a{text-decoration:none; color:black; font-weight:bold; display:block; width:250px; font-size:13px;}
		nav ul li a:hover{background:rgb(231, 220, 231);}
		
		#showContent{float:center; text-align:center; margin-left:80px; margin-right:80px;}
		#show{width:100%; text-align:left; float:left; display:block;}
		p{width:250px; height:250px; border-radius:150px; background:rgb(241, 240, 240); padding:10px; margin-bottom:30px;
		margin-right:47px; float:left; line-height:250px; vertical-align:middle; text-align:center;}
		a {text-decoration:none; color:black; display:block;}
		#first{margin-left:190px;}
	</style>
</head>
<body>
	<header>
		<div><a id="goMain" href="Main.html"><h1>CGV_HS</a></h1>	
		<div>
		<button><a href="LoginForm.html">로그아웃</a></button></div>
		</div>
	</header>

	<nav>
		<div><ul>
			<li><a href="MovieChoice.jsp">영화 예매</a></li>
			<li><a href="FastOrder.jsp">패스트 오더</a></li>
			<li><a href="MyPage.jsp">마이 페이지</a></li>
		</ul></div>
	</nav>
	
	<section>
	<div id="showContent">
		<h4 id="show">마이 페이지 &gt;</h4>
		<p id="first"><strong><a href="MyMovieList.jsp">나의 예매 목록</a></strong></p>
		<p><strong><a href="MyFastOrderList.jsp">패스트오더 예약 현황</a></strong></p>
		<p><strong><a href="MMReviewList.jsp">영화 리뷰 목록</a></strong></p>
	</div>
	</section>
</body>
</html>