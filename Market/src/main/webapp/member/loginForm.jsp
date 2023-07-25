<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<script src="resources/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
		<h3 class="text-left mx-4 my-4">로그인</h3>
		<div class="row">
		<form action="/processLogin.do" method="post">
			<div class="form-group row">
				<label class="col-sm-2 my-2">아이디</label>
				<div class="col-sm-3">
					<input type="text" name="mid" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 my-2">비밀번호</label>
				<div class="col-sm-3">
					<input type="password" name="passwd" class="form-control"/>
				</div>
			</div>
			<div>
				<button class="btn btn-success" type="submit">로그인</button>
			</div>
		</form>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>