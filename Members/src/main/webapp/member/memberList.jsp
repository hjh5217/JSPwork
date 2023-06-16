<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
<title>회원 목록</title>
</head>

<body>
	<jsp:include page="../hearder.jsp" />
	<div id="container">
		<section id="memberlist">
			<h2>회원 목록입니다.</h2>
			<div class="logout">
				<p>
					<a href="logout.do">[관리자 로그아웃]</a>
				</p>
			</div>
			<table id="tbl_list">
				<thead>
					<tr>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>이름</th>
						<th>성별</th>
						<th>가입일</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${memberList}" var="member">
						<tr>
							<td><a href="/memberView.do?memberId=${member.memberId}">
									<c:out value="${member.memberId }" />
							</a></td>

							<td><c:out value="${member.passwd}" /></td>
							<td><c:out value="${member.name}" /></td>
							<td><c:out value="${member.gender}" /></td>
							<td><fmt:formatDate value="${member.joinDate}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><a href="/deleteMember.do?memberId=${member.memberId}"
								onclick="return confirm('정말로 삭제하시겠습니까?')">
									<button type="button">삭제</button>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<h2>이벤트 추첨 링크</h2>
			<div id="eventlink">
				<a href="/member/event.jsp" id="eventlink"> 
				<img src="/resources/images/123.png" alt="브롱스" />
				</a>
			</div>
		</section>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>