<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!-- 자유게시판 -->
<!DOCTYPE html>
<html>
<head>
   <%@ include file="../common/_head.jspf" %>
   <style>
      td, th { text-align: center; }
      .disabled-link { pointer-events: none; }
   </style>
   <script>
      function search() {
         const field = $('#field').val();
         const query = $('#query').val();
         location.href = '/mini/team3/board/list?p=${currentBoardPage}&f=' + field + '&q=' + query;
         location.href = encodeURI(uri);
      }
   </script>
</head>
<body>
   
   <div class="container" style="margin-top:80px">
      <div class="row">
         
         <div class="col-12">
         <a href="/mini/team3/board/list?p=1" class="text-dark"><h3 class="strong" style="text-decoration: underline; text-align: center;">자유게시판</h3></a>
             <table style="margin-left: auto; margin-right: auto; text-align: center;" class="mt-3">
                 <tr>
                      <td style="padding-right: 40px;"><a href="#" class="text-dark"><span style="font-size: 20px;">event</span></a></td>
                      <td style="padding-right: 40px;"><a href="#" class="text-dark"><span style="font-size: 20px;">notice</span></a></td>
                      <td style="padding-right: 40px;"><a href="/mini/team3/board/list?p=1" class="text-dark"><span style="font-size: 20px; text-decoration:underline">자유게시판</span></a></td>
                  </tr>     
             </table>   
            <hr>
         <div class="float-end">
            <table>
               <tr>
                  <td style="width: 16%">
                     <select class="form-control" id="field">
                        <option value="title" ${field eq 'title' ? 'selected' : ''}>제목</option>
                        <option value="content" ${field eq 'content' ? 'selected' : ''}>본문</option>
                        <option value="uname" ${field eq 'uname' ? 'selected' : ''}>글쓴이</option>
                     </select>
                  </td>
                  <td style="width: 24%">
                  <c:if test="${empty query}">
                     <input class="form-control" type="text" id="query" placeholder="검색할 내용">
                  </c:if>
                  <c:if test="${not empty query}">
                     <input class="form-control" type="text" id="query" value="${query}">
                  </c:if>
                  </td>
                  <td style="width: 8%">
                     <button class="btn btn-outline-primary" onclick="search()">검색</button>
                  </td>
               </tr>
            </table>
         </div>

            <table class="table">
               <tr>
                  <th style="width: 8%">ID</th>
                  <th style="width: 52%">제목</th>
                  <th style="width: 14%">글쓴이</th>
                  <th style="width: 16%">수정시간</th>
                  <th style="width: 10%">조회수</th>
               </tr>
               <c:forEach var="board" items="${boardList}">
               <tr>
                  <td>${board.bid}</td>
                  <td>
                     <a href="/mini/team3/board/detail?bid=${board.bid}&uid=${board.uid}"  class="text-dark">${board.title}</a>
                     <c:if test="${board.replyCount ge 1}">
                        <span class="text-danger">[${board.replyCount}]</span>
                     </c:if>
                  </td>
                  <td>${board.uname}</td>
                  <td>${fn:substring(fn:replace(board.writeTime,"T"," "), 2, 16)}</td>
                  <td>${board.viewCount}</td>
               </tr>
               </c:forEach>
            </table>
             <div class="container mt-3">
                 <div class="row justify-content-end">
                     <div class="col-5 align-right">
                      <button onclick="location.href='/mini/team3/board/insert'" style="margin-left:350px" class="btn btn-secondary"> + 글쓰기</button>
                    </div>
                   </div>
               </div>
                
            <%-- pagination --%>
            <ul class="pagination justify-content-center mt-4">
               <li class="page-item"><a class="page-link" href="#">&laquo; 이전</a></li>
            <c:forEach var="page" items="${pageList}">
               <li class="page-item ${currentBoardPage eq page ? 'active' : ''}">
                  <a class="page-link" href="/mini/team3/board/list?p=${page}&f=${field}&q=${query}">${page}</a>
               </li>
            </c:forEach>
               <li class="page-item"><a class="page-link" href="#">다음 &raquo;</a></li>
            </ul>   
            
         </div>
      </div>
   </div>
   <%@ include file="../common/_bottom.jspf" %>
   
</body>
</html>