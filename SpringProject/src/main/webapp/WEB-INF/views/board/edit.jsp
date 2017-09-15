<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form action="/board/edit" method="POST">
			<table>
				<tr>
					<td>제목</td><td><input type="text" value="${read.title}" name="title"></td>
				</tr>
				<tr>
					<td>내용</td><td><input type="text" value="${read.content}" name="content"></td>
					<input type="hidden" value="${read.bno}" name="bno">
					<input type="hidden" value="${read.viewcnt}" name="viewcnt">
					<input type="hidden" value="${page}" name="page">
				</tr>	
			</table>
			<input type="submit" value="수정하기">
		</form>
	</div>
</body>
</html>