<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 컨트롤 쉬프트 슬래쉬가 주석  -->
<%-- jsp 주석 : <% page %>는 지시어 디렉티브 태그로 
	 jsp 언어, 문서 유형, 인코딩 등의 정보를 서버에게 알림--%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시간이란...</title>
<style >
	#content{width: 90%, margin:20px auto;text-align: center;}
	h2{color: #00f;}
</style>
<script>
 	 setInterval(() => {
		let date = new Date();
		let now = date.toLocaleTimeString();
		document.getElementById("demo").innerHTML = now;
	}, 1000); 
	
	
	
</script>
</head>
<body>
	<div id="content">
		<h2>시간이란...</h2>
		<h3>
			내일 죽을 것 처럼 오늘을 살고 <br>
			영원히 살 것 처럼 내일을 꿈꾸어라
		</h3>
		<img alt="손시계" src="../resources/images/1.jpg">
		<p id="demo"></p>
	</div>

	
</body>
</html>