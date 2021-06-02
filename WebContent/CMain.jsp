
<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
	</style>
</head>
    <%  member member = (member) session.getAttribute("memberLogin"); 
       System.out.println("  << for debug >> in rootTopMenu: customer = " + member.getID()); 
 
       %>
        


<header>
		<div><a id="goMain" href="CMain.jsp"><h1>CGV_HS</a></h1>
		
        <button type="button" onclick="location.href='LoginForm.html' ">로그아웃</button>
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

	</div>
	</section>

</body>
</html>
</html>