<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*,CGV_HS.*, java.util.Date"
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
		fieldset {text-align:center; height:300px; background:rgb(241, 240, 240); padding:20px; margin-bottom:30px; float:left;}
		a{text-decoration:none;}
		#first{margin-left:190px;}
	</style>
</head>
<body>
	<header>
	<% String ID = (String)session.getAttribute("id");
	

/* String ID = request.getParameter("ID") */%>
		<div><a id="goMain" href="Main.html"><h1>CGV_HS</a></h1>	
		
		<button><a href="Main.html">로그아웃</a></button></div>
		</div>
	</header>

	<nav>
		<div><ul>
			<li><a href="MovieChoice.jsp">영화 예매</a></li>
			<li><a href="FastOrder.jsp">패스트 오더</a></li>
			<li><a href="MyPage.jsp">마이 페이지</a></li>
		</ul></div>
	
	
	<section>
	<div id="showContent">
		<h4 id="show">영화예매  &gt; 영화선택 &gt; 예매/정보 &gt; 예매하기</h4>
		
		
		<fieldset id="first"><form action="LoginMain.jsp" method=post>	
			<strong>영화</strong><br><br>
			<select name="movie">	
			<option value="1">내일의 기억</option>
			<option value="2">서복</option>
			<option value="3">노매드랜드</option>
			</select>
		</form></fieldset>
	<fieldset><form action="LoginMain.jsp" method=post>
			<strong>극장</strong><br><br>
			<select name="theater">	
			<option value="1">강남</option>
			<option value="2">수원</option>
			<option value="3">부천</option>
			</select><br><br>
			<select name="screentheater">	
			<option value="1">1관</option>
			<option value="2">2관</option>
			<option value="3">3관</option>
			</select>
		</form></fieldset>
		<fieldset><form action="LoginMain.jsp" method=post>
			<strong>날짜 및 시간</strong><br><br>
			<input type="date"><br><br>
			<input type="time">
		</form></fieldset>
		<fieldset><form action="LoginMain.jsp" method=post>
			<strong>기타</strong><br><br>
			<select name="movie1">	
			<option value="1">성인</option>
			<option value="2">청소년</option>
			</select><br><br>
			<select name="movie2">	
			<option value="1">카드결제</option>
			<option value="2">현금결제</option>
			</select>
		</form></fieldset>
		<fieldset><form action="LoginMain.jsp" method=post>
			<strong>좌석</strong><br><br>
						<input type="radio" name="seat" value="1">A1
			<input type="radio" name="seat" value="1">A2
			<input type="radio" name="seat" value="1">A3
			<input type="radio" name="seat" value="1">A4
			<input type="radio" name="seat" value="1">A5
			<input type="radio" name="seat" value="1">A6
			<input type="radio" name="seat" value="1">A7<br>
						<input type="radio" name="seat" value="1">B1
			<input type="radio" name="seat" value="1">B2
			<input type="radio" name="seat" value="1">B3
			<input type="radio" name="seat" value="1">B4
			<input type="radio" name="seat" value="1">B5
			<input type="radio" name="seat" value="1">B6
			<input type="radio" name="seat" value="1">B7<br>
						<input type="radio" name="seat" value="1">C1
			<input type="radio" name="seat" value="1">C2
			<input type="radio" name="seat" value="1">C3
			<input type="radio" name="seat" value="1">C4
			<input type="radio" name="seat" value="1">C5
			<input type="radio" name="seat" value="1">C6
			<input type="radio" name="seat" value="1">C7<br>
						<input type="radio" name="seat" value="1">D1
			<input type="radio" name="seat" value="1">D2
			<input type="radio" name="seat" value="1">D3
			<input type="radio" name="seat" value="1">D4
			<input type="radio" name="seat" value="1">D5
			<input type="radio" name="seat" value="1">D6
			<input type="radio" name="seat" value="1">D7<br>
						<input type="radio" name="seat" value="1">E1
			<input type="radio" name="seat" value="1">E2
			<input type="radio" name="seat" value="1">E3
			<input type="radio" name="seat" value="1">E4
			<input type="radio" name="seat" value="1">E5
			<input type="radio" name="seat" value="1">E6
			<input type="radio" name="seat" value="1">E7<br>
						<input type="radio" name="seat" value="1">F1
			<input type="radio" name="seat" value="1">F2
			<input type="radio" name="seat" value="1">F3
			<input type="radio" name="seat" value="1">F4
			<input type="radio" name="seat" value="1">F5
			<input type="radio" name="seat" value="1">F6
			<input type="radio" name="seat" value="1">F7<br><br><br><br><br><br>
			<input type="submit" value="예매하기 "> <br>		
			<%-- <% 
			 request.setCharacterEncoding("euc-kr");
			
			
			String title=request.getParameter("movie");
			
			String theater=request.getParameter("theater");
			String screentheater=request.getParameter("screentheater");
			
			String date=request.getParameter("date");
			date = (date.length() == 1) ? "000" + date : (date.length() == 2) ? "00" + date : (date.length() == 3) ? "0" + date : date;
			
			String month = SkScanner.getString("  o 월 > ");
			String day = SkScanner.getString("  o 일 > ");
			
			//java.sql.Date dateSelection = Date.valueOf(date + "-" + month + "-" +  day);
			
			
			
			String time=request.getParameter("time");
			
			String age=request.getParameter("movie1");
			int price;
			//연령에 따른 요금 변환
			if(age.equals("청소년")) {
				price=8000;
			}
			else price=12000;
			
			String pay=request.getParameter("movie2");
			String seat=request.getParameter("seat");
			
			
			String hour = SkScanner.getString("  o 시 > ");
			String minute = SkScanner.getString("  o 분 > ");
			
			java.sql.Time timeSelection = Time.valueOf(hour + ":" + minute + "00");
			
			//ticketing t=new ticketing("t11","c1",title,theater,screentheater,timeSelection,dateSelection,seat,price,pay);
			
			//DB.insertticketing(t);
			
			%> --%>
			
		</form></fieldset>
	</div>
	</section>
</body>
</html>