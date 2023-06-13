<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
	h2{background: green; text-align: center;
			padding: 15px 0;color: #eee; border-radius: 5px;}
	#regForm{
		width: 50%;
		margin: 0 auto;
		background: lightgreen;
		padding: 15px 20px;
		border-radius: 10px;
		 
	}
	
</style>
<script type="text/javascript">
	function sigup() {

		let form = document.form1
		let name = form.name.value
		let email = form.email.value

		alert("정말로 가입하시겠습니까?")
		document.getElementById('regForm').style.display = "none"
		document.getElementById('rname').innerHTML = name
		document.getElementById('rmail').innerHTML = email
	}
</script>
</head>
<body>
	<div id="container">
	<h2>회원 가입</h2>
	<hr>
	<div id="regForm">
		<form action="" method="post" name="form1">
			<label>이름</label> <input type="text" name="name" size="40">
			<hr>
			<label>이메일</label> <input type="email" name="email" size="40">
			<p>
				<button type="button" onclick="sigup()">가입</button>
			</p>
		</form>
	</div>

	<div id="result">
		<h3>가입정보</h3>
		<hr>
		<p>
			이름: <span id="rname"></span>
		</p>
		<p>
			이메일: <span id="rmail"></span>
		</p>
	</div>

	</div>
</body>
</html>