<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//쿠키를 가져와서 삭제하기
	Cookie[] cookies = request.getCookies();
	for(int i=0; i<cookies.length; i++){
		cookies[i].setMaxAge(0);//쿠키삭제
		response.addCookie(cookies[i]);//유효기간 만료된 쿠키 생성
	}
	
	response.sendRedirect("cookies02.jsp");
%>