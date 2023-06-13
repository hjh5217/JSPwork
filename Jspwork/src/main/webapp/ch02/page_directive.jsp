<!-- 페이지 디렉티브 태그  -->
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 디렉티브 태그 연습</title>
</head>
<body>
	<%
		// 배열 선언
		int[] arr = {10, 20, 30};
		out.println(arr[0]);	
		
		// 전체조회
		for(int i=0; i<arr.length; i++){
			out.println(arr[i]);
		}
		out.println("<br>");
		// ArrayList클래스
		ArrayList<String> fruitList = new ArrayList<>();
		fruitList.add("바나나");
		fruitList.add("참외");
		fruitList.add("수박");
		
		// 참외 찾기
		out.println( fruitList.get(1) + "<br>");
		
		// 전제 조회
	 	/* for(int i=0;i<fruitList.size();i++){
			String fruit = fruitList.get(i);
			out.println(fruit);
		}  */
		
		for(String s1 : fruitList){
			out.println(s1);
		}
		
	%>
</body>
</html>