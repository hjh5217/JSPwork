<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제이 쿼리 예제</title>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//alert("jquery test");
		$('h2').css('color','blue');
		$('.info').css('background','yellowgreen');
		$('.info').next('p').css('font-style','italic');
		$('button').click(function(){
			alert("안녕하세여");
		});
	});
</script>
</head>
<body>
<h2> ㅎㅇ </h2>
<p class="info">ㅎㅇㄹㄹㄹㄹ</p>
<p>뉘슈</p>

</body>
</html>