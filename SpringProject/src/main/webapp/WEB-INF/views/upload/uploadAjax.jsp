<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.fileDrop{
	width : 100%; height : 200px; border : 1px dotted blue;
	}
	small{ margin-left:3px; font-weight : bold; color : gray;
	}
</style>
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script>
	$(document).ready(function(){
		$(".fileDrop").on("dragenter dragover", function(event){
			event.preventDefault();
		});
		$(".fileDrop").on("drop", function(event){
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			
			var formData = new FormData();
			formData.append("file", file);
			
			$.ajax({
				url : '/upload/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data){
					alert(data);
					var str ="";
					if(checkImageType(data)){
						str="<div><a href='displayFile?fileName="+getImageLink(data)+"'><img src='displayFile?fileName="+data+"'/>"+getOriginalName(data)+"</a><small data-src="+data+">X</small></div>";
					}else{
						str = "<div><a href='displayFile?fileName="+data+"'>"+getOriginalName(data)+"</a><small data-src="+data+">X</small></div>";
					}
					$(".uploadedList").append(str);
				}
			});
		});
		$(".uploadedList").on("click", "small", function(){
			var del = $(this);
			$.ajax({
				url:'/upload/deleteFile',
				type:'post',
				data:{fileName:$(this).attr("data-src")},
				dataType:'text',
				success : function(result){
					if(result=='delete'){
						alert('delete');
						del.parent("div").remove();
					}
				}
			});
		});
		function getImageLink(fileName){
			if(!checkImageType(fileName)){
				return;
			}
			var front = fileName.substr(0,12);
			var end = fileName.substr(14);
			return front+end;
		}
		function getOriginalName(fileName){
			var idx = fileName.lastIndexOf("_")+1;
			return fileName.substr(idx);
		}
		function checkImageType(fileName){
			var pattern = /jpg$|gif$|png$jepg$/i;
			
			return fileName.match(pattern);
		}
	});
</script>
</head>
<body>
<h3>Ajax File upload</h3>
<hr>
<div class="fileDrop"></div>
<div class="uploadedList"></div>
</body>
</html>