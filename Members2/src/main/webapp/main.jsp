<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강남 커뮤니티입니다..</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

	<jsp:include page="header.jsp" />
	<div id="container">
	   <section id="main">
		   <h2>안녕하세요~ 강남 커뮤니티입니다.</h2>
		   <div class="main_img">
		   	  <img src="resources/images/gangnam.jpg" alt="배경 사진">
		   </div>
		   <div id="index_list">
				<c:forEach var="board" items="${boardList}">
					<p>
						<c:out value="${board.bnum}" />
						<a href="/boardView.do?bnum=${board.bnum}">
							<c:out value="${board.title}" />
						</a>
						<fmt:formatDate value="${board.regDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /> 
						<c:out value="${board.hit}" />
						<c:out value="${board.memberId}" />
					</p>
				</c:forEach>
		</div>
	   </section>
	</div>
	<jsp:include page="footer.jsp" />

</body>
</html>