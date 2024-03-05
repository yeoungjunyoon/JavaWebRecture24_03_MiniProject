<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newline", "\n"); %>


<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/_head.jspf" %>
	<style>
		th {padding: 3px; text-align: center;}
		td {padding: 3px; text-align: center;}
		.disabled-link { pointer-events: none; }
	</style>
	<script>
		function deleteFunc(bid) {
			$('#deleteBoard').val(bid); // id값이 deleteBoard인 곳의 value를 bid로 설정
			$('#deleteModal').modal('show'); // id값이 deleteModal인 것을 찾아서 show를 모달함
		}
	</script>
</head>
<body style="margin: auto;">
	<div class="container" style="margin-top:80px">
    	<div class="row">
			
			<!-- //// 본문 영역 //// -->
			<div class="col-1"></div>
			<div class="col-10 mx-auto">
				<h3><strong class="me-5">${board.title}</strong></h3>
       			<br>
       			<div class="row">	
			       	<div class="col-8">
			       		<h5>${user.uname}</h5><h6>글ID: ${board.bid} | 조회 ${board.viewCount} | 댓글 ${board.replyCount} </h6>
			       	</div>
			       	<div class="col-4 text-end">
			       		<h6>(${fn:replace(board.writeTime, "T", " ")})</h6>
			       	</div>
			       	<hr>
					<div class="col-12">
						<textarea class="form-control-plaintext" rows="15" cols="80" readonly>${board.content}</textarea> <!-- 게시글 줄바꿈 시켜주기 -->
					</div>			       	
       			</div>
       				<span class="d-flex justify-content-end" style="font-size: 16px;">
							<a href="/mini/team3/board/list?p=${currentBoardPage}&f=${field}&q=${query}"><i class="fa-solid fa-table-list ms-3"></i> 목록</a>
						<c:if test="${sessUid eq board.uid}">
							<!-- 본인만 수정 삭제 가능 -->
							<a href="/mini/team3/board/update?bid=${board.bid}"><i class="fa-solid fa-pen ms-3"></i> 수정</a>
							<a href="javascript:deleteFunc('${board.bid}')"><i class="fa-solid fa-trash ms-3"></i> 삭제</a>
						</c:if>
					</span>
       			<hr>
       			<!-- //////////////////////////////댓글 나오는 부분 ////////////////////////////// -->
       			<div>      			
					<c:forEach var="reply" items="${replyList}">
					<table class="table">
						<tr>
							<c:if test="${sessUid eq reply.uid}">
								<th class="w-25 text-start"><i class="fa-solid fa-user">&nbsp;</i>${reply.uname}</th>
							</c:if>
							<c:if test="${sessUid ne reply.uid}">
								<th class="w-25 text-start"><i class="fa-regular fa-user">&nbsp;</i>${reply.uname}</th>
							</c:if>
							<td class="w-50 text-start pb-3" colspan="3">${reply.content }</td>
							<td class="w-25 text-start">${fn:replace(reply.replyTime, "T", " ")}</td>
							<c:if test="${sessUid eq reply.uid}">
								<!-- 본인만 수정 삭제 가능 -->
							<th class="p-0">
								<form action="/mini/team3/board/detail?bid=${board.bid}&be=del&rid=${reply.rid}" method="post">
									<button class="btn btn-outline-light p-0 text-dark" type="submit" style="position: absolute;"><i class="fa-solid fa-trash"></i></button>
								</form>
							</th>
							</c:if>
						</tr>
						<tr >
						</tr>
					</table>
					</c:forEach>
       			</div>
       			<div class="col-12">
       			<form action="/mini/team3/board/detail?bid=${board.bid}&be=ins" method="post">
       				<table class="table table-borderless">
       					<tr> <!-- 댓글 입력 부 -->
       						<c:if test="${not empty sessUid}">
     						<th class="text-start" colspan="1"><i class="fa-solid fa-user">&nbsp;</i>${sessUname}</th>
     						</c:if>
       					</tr>
       					<tr>
	       					<td><textarea class="form-control-plaintext border border-secondary" rows="3" name="rc" placeholder=" 댓글 입력..."></textarea></td>
       					</tr>
       					<tr>
	       					<td class="text-end"><button class="btn btn-primary" type="submit">댓글 작성</button></td>
						</tr>
       				</table>
       			</form>
       			</div>
       			<!-- //////////////////////////////댓글 나오는 부분 끝 ////////////////////////////// -->
			</div>
			<div class="col-1"></div>
		</div>
	</div>
	<%@ include file="../common/_bottom.jspf" %>
	<div class="modal" id="deleteModal">
		<div class="modal-dialog">
		    <div class="modal-content">
			    <!-- Modal Header -->
			    <div class="modal-header">
			      	<h4 class="modal-title">글 삭제</h4>
			      	<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			    </div>
			
			    <!-- Modal body -->
		      	<div class="modal-body">
		        	<strong>삭제하시겠습니까?</strong>
		        	<div class="text-center mt-5">
		        		<form action="/mini/team3/board/delete" method="post">
		        			<input type="hidden" id="deleteBoard" name="bid">
			        		<button class="btn btn-danger" type="submit">삭제</button>
		        		</form>
		        	</div>
		      	</div>
		    </div>
		</div>
	</div>
</body>
</html>