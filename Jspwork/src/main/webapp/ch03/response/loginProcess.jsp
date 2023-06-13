<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<% 
	request.setCharacterEncoding("utf-8");//한글인코딩
	String id = request.getParameter("userid");
	String pw = request.getParameter("passwd");
	
	if (id.equals("admin") && pw.equals("1234")  ){
		/* out.println("로그인을 성공"); */
		
		response.sendRedirect("loginSuccess.jsp");
	}else{
		response.sendRedirect("loginFail.jsp"); 
		/* out.println("로그인을 실패"); */
	}
%>
