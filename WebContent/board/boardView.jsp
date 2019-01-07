<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$("#commentBtn").click(function(){
	$.ajax({
		url:"commentInsert.jsp",
		type:"post",
		data:{"msg":$("#msg").val(),"num":${board.BOARD_NUM }},
		success:function(data){
			if(data.trim()==1){
				alert("댓글 달기는 로그인 후 이용 가능합니다");
				location.href="login.jsp";
			}else{
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
			}	
		},
		error:function(e){
			alert("error : "+ e);
		}
	});
})
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<tr>	
				<td>글번호</td><td>${board.BOARD_NUM }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.BOARD_NAME }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${board.BOARD_SUBJECT }</td>
			</tr>
			<tr>
				<td>작성날짜</td>
				<td>${board.BOARD_DATE }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${board.BOARD_READCOUNT }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${board.BOARD_CONTENT }</td>
			</tr>
		</table>
		<div align="right">
		<input type="button" value="답글쓰기" class="btn btn-default" onclick="location='board.jsp?BOARD_NUM=${board.BOARD_NUM }&BOARD_RE_REF=${board.BOARD_RE_REF }&BOARD_RE_SEQ=${board.BOARD_RE_SEQ }&BOARD_RE_LEV=${board.BOARD_RE_LEV }'">
	</div>
	</div>	
</body>
</html>