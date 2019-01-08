<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
	<div class="container">
		<form action="update" method="get">
			<table class="table table-striped">
				<tr>	
					<td>글번호</td><td>${board.BOARD_NUM }</td>
					<input type="hidden" value="${board.BOARD_NUM }" name="BOARD_NUM" id="BOARD_NUM">
				</tr>
				<tr>
					<td>작성자</td>
					<td>${board.BOARD_NAME }</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" value="${board.BOARD_SUBJECT }" name="BOARD_SUBJECT" id="BOARD_SUBJECT"></td>
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
					<td><input type="text" value="${board.BOARD_CONTENT }" name="BOARD_CONTENT" id="BOARD_CONTENT"></td>
				</tr>
				<tr>
					<input type="submit" value="글수정" class="btn btn-default">
					<input type="button" value="글삭제" class="btn btn-default" onclick="location='delete?BOARD_NUM=${board.BOARD_NUM }'">
					<input type="button" value="글목록" class="btn btn-default" onclick="location='boardList.jsp'">
					<input type="button" value="답글쓰기" class="btn btn-default" onclick="location='board.jsp?BOARD_NUM=${board.BOARD_NUM }&BOARD_RE_REF=${board.BOARD_RE_REF }&BOARD_RE_SEQ=${board.BOARD_RE_SEQ }&BOARD_RE_LEV=${board.BOARD_RE_LEV }'">
				</tr>
			</table>
		</form>
	</div>	
		
		
</body>
</html>