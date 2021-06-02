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
		a {text-decoration:none;}
		#write {width:350px; text-align:center; margin:0 auto;}
		fieldset{width:50%; margin-left:24%;}
	</style>
</head>
<body>
	<header>
		<div><a id="goMain" href="Main.html"><h1>CGV_HS</a></h1>		<!-- 자뱌ㅏ로 수정해야됨 -->
		<div>
		<button><a href="Main.html">로그아웃</a></button></div>
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
		<h4 id="show">마이페이지  &gt; 영화 후기 &gt; 후기 작성</h4>
		<fieldset><form id="write" action="ScreenTheatermg.jsp" method=post> 
		<br><b>어벤져스 : 엔드게임</b><br><br>
		 평점  <select>
        				<option>1점</option>
        				<option>2점</option>
        				<option>3점</option>
        				<option>4점</option>
        				<option>5점</option>
      				</select><br><br>
      			리뷰 내용  <br><textarea cols="30" rows="6"></textarea>
    			<br><br><input type="submit" value="작성완료">
    			<input type="reset" value="초기화"> <br><br>
			</form></fieldset>
	</div>
	</section>
</body>
</html>