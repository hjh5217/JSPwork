<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link rel="stylesheet" type="text/css" href="./resources/css/style.css">
</head>
<body>
	<%-- <c:if test="${empty sessionId}">
		<script>
			alert("로그인이 필요합니다.");
			location.href = "loginForm.do";
		</script>
	</c:if> --%>
	<jsp:include page="../hearder.jsp" />
	<div id="container">
		<section id="board_detail">
			<h2>게시글 수정</h2>	
			<form action="/updateProcess.do" method="post">
			<input type="hidden" name="bnum" value="${board.bnum}">
			<c:if test="${not empty modifyDate}">
			<input type="hidden" name="modifyDate" value="${board.modifyDate}">
			</c:if>
				<table>
					<tbody>
						<tr>
							<td>
								<input type="text" name="title" value="${board.title}"/>
							</td>
						</tr>
						<tr>
							<td>
							<textarea rows="8" cols="100" name="content">${board.content}</textarea>
							</td>
						</tr>
						<tr>
							<td>
								<button type="submit">저장</button>
								
								<button type="reset">취소</button>
								
								<a href="boardList.do">
								<button type="button">목록</button>
								</a>
							</td>
						</tr>
					</tbody>
				</table>
				</form>
		</section>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>