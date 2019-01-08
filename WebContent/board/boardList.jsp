<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script  src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function getData(pageNum){
	$("#results").load("boardList.bo",{"pageNum":pageNum},function(data){
		$("#results").html(data);
	})
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
	<body>
		<div class="container" id="results">
			<div align="center" id="count">
				총 게시물 수 : ${count }
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>NO</th>
						<th>이름</th>
						<th>제목</th>
						<th>작성날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lists}" var="list">
						<tr>	
							<td>${list.BOARD_NUM }</td>
							<td>${list.BOARD_NAME }</a></td>
							<c:if test="${list.BOARD_RE_LEV gt 0 }">
								<td><a href="#" onclick="location.href='view?BOARD_NUM=${list.BOARD_NUM}'">
								<img src="../images/level.gif" width="${list.BOARD_RE_LEV*10 }" height="16">
	             				<img src="../images/re.gif">${list.BOARD_SUBJECT }</a></td>
							</c:if>
							<c:if test="${list.BOARD_RE_LEV eq 0 }">
								<td><a href="#" onclick="location.href='view?BOARD_NUM=${list.BOARD_NUM}'">${list.BOARD_SUBJECT }</a></td>
							</c:if>
							<td>${list.BOARD_DATE }</td>
							<td>${list.BOARD_READCOUNT }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div align="center">
			<!-- 이전 -->
			<c:if test="${startpage>blockpage }">
				<a href="javascript:getData(${startpage-blockpage })">[이전]</a>
			</c:if>
			<!-- 페이지출력 -->
			<c:forEach begin="${startpage }" end="${endpage }" var="i">
				<c:if test="${currentPage eq i}" >
					${i }
				</c:if>
				<c:if test="${currentPage ne i}" >
					<a href="javascript:getData(${i })">${i }</a>
				</c:if>
			</c:forEach>
			<!-- 다음 -->
			<c:if test="${endpage<totpage }">
				<a href="javascript:getData(${endpage+1 })">[다음]</a>
			</c:if>
			</div>
		</div>	
	</body>
</html>