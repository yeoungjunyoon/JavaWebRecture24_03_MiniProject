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
         <h3 class="strong" style="text-decoration: underline; text-align: center;">주문내역</h3>
            <hr>

            <table class="table">
               <tr class="table-warning">
                  <th style="width: 15%">주문번호</th>
                  <th style="width: 35%">등록날짜</th>
                  <th style="width: 20%">총 가격</th>
                  <th style="width: 15%">상태</th>
                  <th style="width: 15%">행동</th>
               </tr>
				<c:forEach var="order" items="${orderList}">
					<tr>
						<td><a href="/mini/team3/user/orderDetail?oid=${order.oid}" class="text-dark">${order.oid}</a></td>
						<td><a href="/mini/team3/user/orderDetail?oid=${order.oid}" class="text-dark">${fn:replace(order.orderTime, "T", " ")}</a></td>
						<td>${order.totalPrice}</td>
						<td>
							<c:if test="${order.status eq -1}">
								취소
							</c:if>
							<c:if test="${order.status eq 0}">
								대기
							</c:if>
							<c:if test="${order.status eq 1}">
								요청
							</c:if>
							<c:if test="${order.status eq 2}">
								운송
							</c:if>
							<c:if test="${order.status eq 3}">
								완료
							</c:if>
						</td>
						<td>
						
							<c:if test="${order.status eq 0}">
								<form action="/mini/team3/user/order?uid=${uid}&be=ins&oid=${order.oid}" style="display: inline;" method="post" class="me-1">
									<button type="submit"  class="btn btn-primary">확정</button>
								</form>
							</c:if>
							
							<c:if test="${order.status eq 0}">
								<form action="/mini/team3/user/order?uid=${uid}&be=del&oid=${order.oid}" style="display: inline;" method="post">
									<button type="submit" class="btn btn-secondary">취소</button>
								</form>
							</c:if>
						</td>
					</tr>
				</c:forEach>
            </table>
            
         </div>
      </div>
   </div>
   <%@ include file="../common/_bottom.jspf" %>
   
</body>
</html>