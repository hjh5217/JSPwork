<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 폼에입력된 자료를 받아오기
	String name = request.getParameter("uname");
%>
<p>이름: <%= name%></p>