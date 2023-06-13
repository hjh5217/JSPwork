<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파람1 데이터입니다~~</title>
</head>
<body>
	<!-- request는 내장 객체 getParameter(name속성)
		 데이터 받을 때 사용함.  -->
	<p>아이디: <%= request.getParameter("id") %></p>
	<p>비밀번호: <%= request.getParameter("pw") %></p>
</body>
</html>