<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 예제</title>
</head>
<body>
	<h2>JSTL 예제</h2>
	<hr>
	<h3>set, out</h3>
	<c:set var="product1" value="삼성 galaxy"></c:set>
	<c:set var="product2" value="IPHONE"></c:set>
	<c:set var="intArray" value="${{1,2,3,4}}"></c:set>
	<p>
		product1: <c:out value="${product1 }"></c:out>
	</p>
	<p>
		product2: <c:out value="${product2 }"></c:out>
	</p>
	<h3>배열출력</h3>
	<ul>
		<c:forEach items="${intArray}" var="num">
			<li>${num }</li>
		</c:forEach>
	</ul>
	<h3>if</h3>
	<c:set var="checkout" value="true"></c:set>
	<c:if test="${checkout }">
		<p>주문 제품: ${product2}</p>
	</c:if>
	<c:if test="${!checkout }">
		<p>주문 제품이 아님</p>
	</c:if>
	<c:if test="${not empty product1 }">
		<p>${product1} 이미 추가됨!</p>
	</c:if>
	
	<h3> choose,when,otherwise </h3>
	<c:choose>
		<c:when test="${checkout }">
			<p>주문 제품: ${product2 }</p>
		</c:when>
		<c:otherwise>
			<p>주문 제품이 아님</p>
		</c:otherwise>
	</c:choose>
	
	
	<h3>forTokens</h3>
	<c:forTokens items="egg/coffee/water" delims="/"
		var="cart" varStatus="i">
		${i.index }:
	</c:forTokens>
</body>
</html>