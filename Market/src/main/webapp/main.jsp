<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome~</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<script src="resources/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div class="container my-3">
		<h2>Green Market에 오신 걸 환영합니다.</h2>
		<div class="card mb-3">
		  <img src="resources/images/main.jpg" class="card-img" alt="메인">
		  <div class="card-body">
		    <h5 class="card-title">하이</h5>
		    <p class="card-text">바이</p>
		    <p class="card-text"><small>3분전</small></p>
		  </div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>