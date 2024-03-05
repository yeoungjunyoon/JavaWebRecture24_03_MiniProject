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
         <a href="#" class="text-dark"><h3 class="strong" style="text-decoration: underline; text-align: center;">주문 상세내역</h3></a>
            <hr>

            <table class="table">
               <tr class="table-warning">
                  <th style="width: 15%">주문번호</th>
                  <th style="width: 35%">등록날짜</th>
                  <th style="width: 20%">총 가격</th>
                  <th style="width: 15%">상태</th>
                  <th style="width: 15%">행동</th>
               </tr>
				<tr>
					<td>${order.oid}</td>
					<td>${fn:replace(order.orderTime, "T", " ")}</td>
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
					</td>
				</tr>
            </table>
            <table class="table">
               <tr class="table-primary">
                  <th style="width: 15%">구매번호</th>
                  <th style="width: 35%">이름</th>
                  <th style="width: 20%">가격</th>
                  <th style="width: 15%">수량</th>
                  <th style="width: 15%">행동</th>
               </tr>
               <c:forEach var="sales" items="${salesList}">
					<tr>
						<td>${sales.sid}</td>
						<td><a href="/mini/team3/item/detail?mid=${sales.mid}" class="text-dark">${sales.name}</a></td>
						<td>${sales.price}</td>
						<td>${sales.quantity}</td>
						<td>
							<c:if test="${order.status eq 0}">
								<form action="/mini/team3/user/orderDetail?sid=${sales.sid}&oid=${order.oid}" method="post">
									<button type="submit"  class="btn btn-secondary">취소</button>
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