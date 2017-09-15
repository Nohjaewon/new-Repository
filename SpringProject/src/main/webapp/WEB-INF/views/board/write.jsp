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
		<h1>글쓰기</h1>
			<form method="POST" action="/board/write">
				<table>
					<tr>
						<td>제목</td><td><input type="text" name="title"></td>
					<tr>
						<td>내용</td><td><input type="text" name="content"></td>
					<tr>
						<td>작성자</td><td><input type="text" name="writer"></td>
					</tr>
				</table>
				<input type="submit" value="작성완료">
			</form>
	</div>
</body>
</html>