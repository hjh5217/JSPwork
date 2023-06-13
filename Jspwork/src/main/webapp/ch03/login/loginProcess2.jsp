<%@page import="beans.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

	request.setCharacterEncoding("utf-8");

	// 폼에입력된 데이터 가져오기
	String uid=request.getParameter("userid");
	String pw = request.getParameter("passwd");

	LoginBean login = new LoginBean();
	login.setUserid(uid);
	login.setPasswd(pw);

	boolean result = login.checkUser();
	if(result){
		out.println(login.getUserid()+"환영합니다!");
	}else{
		out.println("아이디와 비밀번호를 다시 확인해주세요");
	}
%>

<p>아이디: <%=login.getUserid() %></p>
<p>비밀번호: <%=login.getPasswd() %> </p>

