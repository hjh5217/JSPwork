<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	List<String> productList = (ArrayList)session.getAttribute("cartList");
	
	if(productList == null){
		productList = new ArrayList<>();
		session.setAttribute("cartList", productList);
	}
	
	String product = request.getParameter("product");

	productList.add(product);
%>

<script>
	alert("<%=product%>가(이) 추가되었습니다.");
	history.go(-1);
</script>