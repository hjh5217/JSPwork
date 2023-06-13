<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%!
	// 전역공간 <%! 보통 해드랑 바디사이에서 작성
	int pageCount = 0;

	void addCount(){
		pageCount++;	
	}
	
%>

<%addCount();%>
<p>이 사이트 방문은<%=pageCount %></p>

