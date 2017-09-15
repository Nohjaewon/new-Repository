<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	li{
		display : inline-block;
	}
	a{
		color : black;
		text-decoration: none;
	}
	.active{
		background-color:red;
	}
</style>
<script>
	$(document).ready(function(){
		$('#searchBtn').click(function(){
			location();
		});
		$('#keywordInput').keydown(function(key){
			if(key.which==13){
				location();
			}
			else{
				return;
			}
		});
		var location = function(){
			self.location = "list"
				+'${pageMaker.makeQuery(1)}'
				+'&searchType='
				+$("select option:selected").val()
				+"&keyword=" +$('#keywordInput').val();
		};
	});
	
</script>
</head>
<body>
	<div align="center">
		<h1>게시판</h1>
		<hr>
			<form method="POST">
				<table border="1">
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
						<td><a href="/board/read?bno=${board.bno}&page=${page.page}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.regdate}</td>
						<th>${board.viewcnt}</th>
					</tr>
				</c:forEach>			
				</table>
			</form>
	</div>
	<div>
		<ul>
			<c:if test="${pageMaker.prev}">
				<li>
					<a href="list${pageMaker.makeSearch(pageMaker.startPage-1)}">&laquo;</a>
				</li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li <c:out value="${pageMaker.cri.page==idx?'class=active':''}"/>>
					<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next}">
				<li>
					<a href="list${pageMaker.makeSearch(pageMaker.endPage+1)}">&raquo;</a>
				</li>
			</c:if>
		</ul>
	</div>
<select name="searchType">
	<option value="n" <c:out value="${cri.searchType==null?'selected':''}"/>>
	---
	</option>
	<option value="t" <c:out value="${cri.searchType eq 't'?'selected':''}"/>>
	Title
	</option>
	<option value="c" <c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
	Content
	</option>
	<option value="w" <c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
	Writer
	</option>
	<option value="tc" <c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
	Title OR Content
	</option>
	<option value="cw" <c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
	Content OR Writer
	</option>
	<option value="tcw" <c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
	Title OR Content OR Writer
	</option>
</select>
<input type="text" name="keyword" id="keywordInput" value="${cri.keyword}">
<button id="searchBtn">Search</button>
<input type="button" value="Write" onclick="location.href='/board/write'">
</body>
</html>