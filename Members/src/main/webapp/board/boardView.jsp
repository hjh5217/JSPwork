<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 보기</title>
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
			<h2>게시글 상세 보기</h2>	
				<table>
					<tbody>
						<tr>
							<td>
								<input type="text" name="title" value="${board.title}"/>
							</td>
						</tr>
						<tr>
							<td>
							<textarea rows="8" cols="100">${board.content}</textarea>
							</td>
						</tr>
						<tr>
							<td>
								<c:out value="글쓴이 : ${board.memberId}"/>
								<br>
								작성일 : <fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd hh:mm:ss"/> 
							</td>
						</tr>
						<tr>
							<td>
								<c:if test="${sessionId == board.memberId}">
									<button type="button">수정</button>
									<a href="/deleteBoard.do?bnum=${board.bnum}" onclick="return confirm('정말로 삭제하시겠습니까?')">
									<button type="button">삭제</button>
									</a>
								</c:if>
								<a href="/boardList.do">
								<button type="button">목록</button>
								</a>
							</td>
						</tr>
					</tbody>
				</table>
		</section>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>