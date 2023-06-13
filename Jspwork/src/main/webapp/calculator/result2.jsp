<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="calc" class="beans.Calculator"/>
<jsp:setProperty property="num1" name="calc"/>
<jsp:setProperty property="num2" name="calc"/>
<jsp:setProperty property="op" name="calc"/>
<link rel="stylesheet" href="../resources/css/Calcuatorstyle.css">

<div id="container">
	<h2>계산기</h2>
	<hr>
	<%
		calc.calculate();
	%>

	<p>계산 결과: <jsp:getProperty property="result" name="calc"/></p>
</div>