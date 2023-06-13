<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>짝수 홀수 판별 프로그램</title>
</head>
<body>
	<h2>짝수 홀수 판별 프로그램</h2>
	<% 
		
		
		int num = 1;
		String result = "";
		
		if (num%2==0){
			result = "결과는 짝수 입니다";
		}else{
			result = "결과는 홀수 입니다";
		}
		out.println("결과는"+result);		
	%>
 	<%-- <p> <%= result %></p> --%>
</body>
</html>