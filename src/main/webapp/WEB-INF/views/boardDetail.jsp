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
			$('#submit').click(function(){
				$.ajax(
						{
							url:"insertComment",
							data:{
								board_seq : "${requestScope.board.seq}",
								content : $("#commentTxt").val()
							},
							type:"POST",
							success:function(data){
								if(data <= 0) alert("등록실패!");
	                			else {
	                				alert("등록완료!");
	                				refresh();
	                			}
							},
							error:function(e){
								console.log(e);
							}
						}
				);				
			})
			
			
			$('#comment_table').on('click', '.delete', function(event){ //삭제버튼에 클릭이벤트추가
                let tr = $(this).parent().parent(); //삭제버튼이 눌러진 행 tr
                let boardSeq = parseInt($(tr).children().eq(0).text());
                let seq = parseInt($(tr).children().eq(1).text());
                $.ajax(
                	{
                		url:"deleteComment",
                		data:{boardSeq:boardSeq, seq:seq},
                		type:"POST",
                		success:function(data){
                			if(data <= 0) alert("삭제실패!");
                			else {
                				alert("삭제완료!");
                				refresh();
                			}
                		},
                		error:function(e){
                			console.log(e);
                		}
                		
                	}
                );                
				
            });
			
			
			$('#comment_table').on('click', '.update', function(event){ //삭제버튼에 클릭이벤트추가
                let tr = $(this).parent().parent(); //삭제버튼이 눌러진 행 tr
                let boardSeq = parseInt($(tr).children().eq(0).text());
                let seq = parseInt($(tr).children().eq(1).text());
                let content =  $(tr).children().eq(2).children().val();
                $.ajax(
                	{
                		url:"updateComment",
                		data:{boardSeq:boardSeq, seq:seq , content:content},
                		type:"POST",
                		success:function(data){
                			//console.log(data);
                			if(data <= 0) alert("수정실패!");
                			else {
                				alert("수정완료!");
                				refresh();
                			}
                		},
                		error:function(e){
                			console.log(e);
                		}
                		
                	}
                );        
				
            });
			
			
			function refresh() {
				$('#comment_table').empty();
			    $.ajax({
			        url: "refresh",
			        data: { board_seq: "${requestScope.board.seq}" },
			        dataType: "json",
			        success: function(data) {
			            $.each(data, function(index, item) {
			                $('#comment_table').append(
			                    "<tr><td>" + item.boardSeq + "</td>" +
			                    "<td>" + item.seq + "</td>" +
			                    '<td><input type="text" class="form-control" id="content" name="content" value="' + item.content + '"></td>' +
			                    '<td><input type="button" class="btn update" value="수정" onclick=""></td>' +
			                    '<td><input type="button" class="btn delete" value="삭제"></td></tr>'
			                );
			            });
			        }
			    });
			}
			
			refresh();
			
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
	<table class="table" id="comment_table">		
	</table>
</form>

</body>

</html>