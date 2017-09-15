<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>게시판</h1>
		<hr>
			<form method="POST">
				<table>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성날짜</th>
						<th>조회수</th>
					</tr>
				<c:forEach items="${board}" var="board">
					<tr>
						<td>${board.bno}</td>
						<td>${board.title}</td>
						<td>${board.writer}</td>
						<td>${board.regdate}</td>
						<th>${board.viewcnt}</th>
					</tr>
				</c:forEach>			
				</table>
			</form>
	</div>
</body>
</html>