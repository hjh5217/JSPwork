<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나는 오늘의 날짜요</title>
	<script >
	 setInterval(() => {
			let date = new Date();
			let now = date.toLocaleTimeString();
			document.getElementById("demo").innerHTML = now;
		}, 1000); 
	</script>
</head>
<body>
	<h2>오늘의 날짜</h2>
	
	<%
		LocalDateTime now = LocalDateTime.now();
		
	%>
	<%= now %>
	<p id="demo"></p>
	
</body>
</html>