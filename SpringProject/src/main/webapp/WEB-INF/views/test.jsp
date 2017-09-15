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
	<h2>Ajax Test Page</h2>
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
	$(document).ready(function(){
		var bno = 33;
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
						getAllList();
					}
				}
			});
		});
		function getAllList(){
			$.getJSON("/replies/all/"+bno, function(data){
				var str ="";
				$(data).each(
					function(){
						str+="<li data-rno='"+this.rno+"'>"+this.replyer+":"+this.replytext+"</li>";
					});
				$("#replies").html(str);
			});
		}
	});
	
	</script>
</body>
</html>