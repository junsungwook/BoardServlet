<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	</div>	
</body>
</html>