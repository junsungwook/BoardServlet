<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	$.ajax({
		url:"C_List",
		type:"post",
		data:{"BOARD_NUM":$("#BOARD_NUM").val()},
		success:function(data){
			if(data!=null){
				data = $.parseJSON(data);
				var htmlStr="";
				htmlStr +="<table class='table table-striped table-dark'>";
				for(var i=0; i<data.length;i++){
					htmlStr +="<tr>";
					htmlStr +="<td>"+data[i].cnum+"</td>";
					htmlStr +="<td>"+data[i].userid+"</td>";
					htmlStr +="<td>"+data[i].regdate+"</td>";
					htmlStr +="<td>"+data[i].msg+"</td>";
					htmlStr +="</tr>";
				}
				htmlStr +="</table>";
				$("#result").html(htmlStr);	
			}
		},
		error:function(e){
			alert("error : "+ e);
		}
	});
	$("#commentBtn").click(function(){
		$.ajax({
			url:"C_Insert",
			type:"post",
			data:{"msg":$("#msg").val(),"BOARD_NUM":$("#BOARD_NUM").val(),"userid":$("#userid").val()},
			success:function(data){
				data = $.parseJSON(data);
				var htmlStr="";
				htmlStr +="<table class='table table-striped table-dark'>";
				for(var i=0; i<data.length;i++){
					htmlStr +="<tr>";
					htmlStr +="<td>"+data[i].cnum+"</td>";
					htmlStr +="<td>"+data[i].userid+"</td>";
					htmlStr +="<td>"+data[i].regdate+"</td>";
					htmlStr +="<td>"+data[i].msg+"</td>";
					htmlStr +="</tr>";
				}
				htmlStr +="</table>";
				$("#result").html(htmlStr);	
				$("#msg").val("");
			},
			error:function(e){
				alert("inserterror : "+ e);
			}
		});
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
	<div class="container">
		<form action="update" method="get">
			<table class="table table-striped">
				<tr>	
					<td>글번호</td><td>${board.BOARD_NUM }</td><td>작성자</td><td>${board.BOARD_NAME }</td>
					<input type="hidden" value="${board.BOARD_NUM }" name="BOARD_NUM" id="BOARD_NUM">
				</tr>
				<tr>
					<td>작성날짜</td><td>${board.BOARD_DATE }</td><td>조회수</td><td>${board.BOARD_READCOUNT }</td>
					
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" class="form-control" value="${board.BOARD_SUBJECT }" name="BOARD_SUBJECT" id="BOARD_SUBJECT"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3"><textarea class="form-control col-sm-5" rows="7" name="BOARD_CONTENT" id="BOARD_CONTENT">${board.BOARD_CONTENT }</textarea></td>
				</tr>
				<tr>
					<td>업로드 파일</td>
					<td ><a href="down?BOARD_FILE=${board.BOARD_FILE }">${board.BOARD_FILE }</a></td>
				</tr>
				<tr>
					<input type="submit" value="글수정" class="btn btn-default">&nbsp;
					<input type="button" value="글삭제" class="btn btn-default" onclick="location='delete?BOARD_NUM=${board.BOARD_NUM }'">&nbsp;
					<input type="button" value="글목록" class="btn btn-default" onclick="location='boardList.bo'">&nbsp;
					<input type="button" value="답글쓰기" class="btn btn-default" onclick="location='boardRe?BOARD_NUM=${board.BOARD_NUM }&BOARD_RE_REF=${board.BOARD_RE_REF }&BOARD_RE_SEQ=${board.BOARD_RE_SEQ }&BOARD_RE_LEV=${board.BOARD_RE_LEV }'">
				</tr>
			</table>
		</form>
		<div id="result"></div>
		<div align="right">
			아이디 입력 <input type="text" value="" name="userid" id="userid">
			<textarea rows="5" cols="50" id="msg" class="form-control"></textarea>
			<input type="button" class="btn btn-default" value="댓글쓰기" id="commentBtn" class="btn btn-default">
		</div>
	</div>	
</body>
</html>