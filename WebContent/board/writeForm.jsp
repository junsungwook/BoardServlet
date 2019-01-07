<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
<div class="container">
	<h2>게시판글등록</h2>
	<form action="insert.bo" method="post" enctype="multipart/form-data" name="boardform">
		<table class="table table-striped">
			<tr>
				<td class="td_left"><label for="board_name">글쓴이</label></td>
				<td class="td_right"><input type="text" name="board_name" id="board_name"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_pass">비밀번호</label></td>
				<td class="td_right"><input name="board_pass" type="password" id="board_pass"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_subject">제 목</label></td>
				<td class="td_right"><input name="board_subject" type="text" id="board_subject"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_content">내 용</label></td>
				<td><textarea id="board_content" name="board_content" cols="40" rows="15"></textarea></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_file"> 파일 첨부 </label></td>
				<td class="td_right"><input name="board_file" type="file" id="board_file"></td>
			</tr>
		</table>
			<input type="submit" value="등록">&nbsp;&nbsp; <input	type="reset" value="다시쓰기">
	</form>
</div>
</body>
</html>