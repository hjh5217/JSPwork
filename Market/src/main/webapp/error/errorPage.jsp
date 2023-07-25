<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 오류 처리</title>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="container my-3">
		<div class="text-center">
			<h1 class="alert-danger">요청하신 페이지를 찾을 수 없습니다.</h1>
			<h3><%=request.getRequestURL() %></h3>
		</div>
		<p><a href="/productList.do" class="btn btn-secondary">&raquo;상품 목록 페이지&laquo;</a></p>
	</div>
	
	<jsp:include page="../footer.jsp"/>
</body>
</html>