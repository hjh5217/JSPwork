<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
	<h2>파일 업로드</h2>
	<!-- enctype 반드시 명시해야함. -->
	<form action="fileupload01_process.jsp" method="post" enctype="multipart/form-data">
		<p>이 름(유저): <input type="text" name="name"></p>
		<p>파일 제목: <input type="text" name="title"></p>
		<p>파일 이름: <input type="file" name="fileName"></p>
		<p><input type="submit" value="파일 올림"></p>
	</form>
</body>
</html>