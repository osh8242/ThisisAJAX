<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			
		});
	</script>
</head>

<form action="register.do" method="post">
	<table class="container table table-borderless">
		<thead class="text-center">
				<h3>상세보기</h3>
		</thead>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating">
				  <input type="text" class="form-control" id="seq" name="seq" value="${requestScope.board.seq}" required>
				  <label for="floatingInput">seq</label>
				</div>
			</td>
			<td class="col-md-4"></td>
		</tr>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating">
				  <input type="text" class="form-control" id="title" name="title" value="${requestScope.board.title}" required>
				  <label for="floatingInput">title</label>
				</div>
			</td>
			<td class="col-md-4"></td>
		</tr>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating">
				  <input type="text"class="form-control" id="content" name="content" value="${requestScope.board.content}">
				  <label for="floatingInput">content</label>
				</div>				
			</td>
			<td class="col-md-4"></td>
		</tr>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating">
				  <input type="email" class="form-control" id="regdate" name="regdate" value="${requestScope.board.regdate}">
				  <label for="floatingInput">regdate</label>
				</div>	
			</td>
			<td class="col-md-4"></td>		
		</tr>
	</table>
</form>
<hr>
<form>
	<h5>댓글존</h5>
	<input type="text" id="commentTxt" name="commentTxt" placeholder="댓글을 입력해주세용">
	<input type="button" id="submit" value="댓글입력">
	<table class="table">
		<c:set var="commentlist" value="${requestScope.commentlist}"></c:set>
		<c:forEach var="comment" items="${commentlist}">
			<tr>
				<td>${comment.boardSeq}</td>
				<td>${comment.seq}</td>
				<td>${comment.content}</td>
			</tr>
		</c:forEach>
	</table>
</form>

</body>
</html>