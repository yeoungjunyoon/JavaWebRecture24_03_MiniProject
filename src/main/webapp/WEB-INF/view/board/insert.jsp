<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	</script>
</head>
<body>
	<div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h2 class="text-center">게시글 작성</h2><p>&nbsp;</p>
            	<form action="/mini/team3/board/insert" method="post">
                <div class="table table-borderless">
                          <table class="table table-borderless">
                <tr>
                    <td style="width: 10%">제목</td>
                    <td style="width: 90%" colspan="3"><input type="text"  class="form-control" name="title" value="${board.title}"></td>
                </tr>
                <tr>
                    <td>글내용</td>
                    <td colspan="3"><textarea  name="content" class="form-control" rows="10">${board.content}</textarea></td>
                </tr>
                 
                <tr>
						<td colspan="2">
						    <button class="btn btn-primary" type="submit">확인</button>
						    <button class="btn btn-secondary" type="reset">취소</button>
						</td>
					</tr>
              </table>
             
         
                </div>
            </form>   
        </div>
    </div>
	<%@ include file="../common/_bottom.jspf" %>
</body>
</html>