<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<table border="1">
			<tr>
				<td>제목</td><td>${read.title}</td>
			</tr>
			<tr>
				<td>내용</td><td>${read.content}</td>
			</tr>
		</table>
		<input type="button" value="수정하기" onclick="location.href='/board/edit?bno=${read.bno}&page=${page}'">
		<input type="button" value="삭제하기" onclick="location.href='/board/delete?bno=${read.bno}&page=${page}'">
		<input type="button" value="목록" onclick="location.href='/board/list?page=${page}'">
	</div>
<div>
		<div>
			작성자 <input type="text" name="replyer" id="replyer">
		</div>
		<div>
			내용 <input type="text" name="replytext" id="replytext">
		</div>
		<button id="replyAddBtn">등록</button>
	</div>
	<ul id="replies">
	</ul>
	<script>
	var bno = ${read.bno};
	$(document).ready(function(){
		loadreplies();
		$('#replies').on('click', 'span', function(){
			var rno = $(this).attr('data-src');
			var del = $(this);
			$.ajax({
				type :'delete',
				url:'/replies/delete/'+rno,
				headers : {
					"Contet-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : 'text',
				data : JSON.stringify({
					rno : rno
				}),
				success : function(result){
					if(result=='SUCCESS'){
						alert("삭제되었습니다");
						del.parent('li').remove();
					}
				}
		});
		});
	});
	$('#replyAddBtn').on('click', function(){
		var replyer = $('#replyer').val();
		var replytext = $('#replytext').val();
		$.ajax({
			type:'post',
			url:'/replies',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				replytext : replytext
			}),
			success : function(result){
				if(result=='SUCCESS'){
					alert("등록 되었습니다");
					loadreplies();
				}
			}
		});
	});
	function loadreplies(){
		$.getJSON("/replies/all/"+bno, function(data){
			var str ="";
			$(data).each(
				function(){
					str+="<li data-rno='"+this.rno+"'>"+this.replyer+":"+this.replytext+" <span id='delete' data-src='"+this.rno+"'>X</span>"+"</li>";
				});
			$("#replies").html(str);
		});
	}
	</script>
</body>
</html>