<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>
	<div id="container">
		<h2>계산하기</h2>
		<p>선택한 상품 목록
		<hr>
		<%
			
			ArrayList<String> productList
				= (ArrayList)session.getAttribute("cartList");
			for(String product : productList)
				out.println(product+"<br>");
		%>
	</div>
</body>
</html>