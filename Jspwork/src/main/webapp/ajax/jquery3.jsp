<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('button').click(function(){
			$('#demo').html("<h2>jquery를 이용한 Ajax 테스트</h2>");
		});
	});
</script>
</head>
<body>
	<div id="demo">
		<h2>버튼을클릭하면바뀜</h2>
	</div>
	<button type='button'>클릭</button>
</body>
</html>