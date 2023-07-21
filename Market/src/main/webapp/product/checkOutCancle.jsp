<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<script src="resources/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-4">
		<h2 class="alert alert-danger">주문이 취소되었습니다.</h2>
		<p><a href="/productList.do">상품 목록</a></p>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>