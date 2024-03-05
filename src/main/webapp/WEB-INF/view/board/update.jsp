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
            <h2 class="text-center">게시글 수정</h2><p>&nbsp;</p>
            <form action=/mini/team3/board/update?bid=${board.bid}&uid=${board.uid}" method="post">
                <div class="table table-responsive">
                          <table class="table table-striped">
                <tr>
                    <td>작성자</td>
                    <td class="text-start">${board.uname}</td>
                    <td class="text-end">작성일 | ${fn:replace(board.writeTime, "T", " ")}</td>
                </tr>
                <tr>
                    <td>제목</td>
                    <td colspan="3"><input type="text"  class="form-control" name="title" value="${board.title}"></td>
                </tr>
                 
                <tr>
                    <td>글내용</td>
                    <td colspan="3"><textarea  name="content" class="form-control" rows="10">${board.content}</textarea></td>
                </tr>
                 
                <tr>
                	<td></td>
                    <td colspan="3"  class="text-center" style="padding-right: 100px;">
                        <input type="submit" value="글수정" class="btn btn-primary">
                        <input type="button"  class="btn btn-secondary" onclick="location.href='/mini/team3/board/list?p=1'" value="취소">
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