<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
<center>
	<form action="update" method="post">
		<input type="hidden" value="${bv.BOARD_NUM }" name="BOARD_NUM" id="BOARD_NUM">
		<input type="hidden" value="${bv.BOARD_SUBJECT }" name="BOARD_SUBJECT" id="BOARD_SUBJECT">
		<input type="hidden" value="${bv.BOARD_CONTENT }" name="BOARD_CONTENT" id="BOARD_CONTENT">
		비밀번호를 입력하세요<br>
		<input type="password" name="BOARD_PASS" id="BOARD_PASS"><br>
		<input type="submit" value="확인">
		<input type="button" value="뒤로" onclick="self.close()">
	</form>
</center>
</body>
</html>