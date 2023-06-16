<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
<title>회원 목록</title>
</head>

<body>
	<jsp:include page="../hearder.jsp" />
	<div id="container">
		<section id="eventpage">
            <h1>행운의 추첨(Good Luck)</h1>
            <img src="/resources/images/123.png" alt="브롱스">
            <br>
            <button type="button" onclick="play()">추첨</button>
            <p id="display"></p>
        </section>
        <script src="/resources/js/event.js">         
        </script>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>