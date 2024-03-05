<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../common/_head.jspf" %>
    <script>
		function search() {
			const field = $('#field').val();
			const query = $('#query').val();
			location.href = '/mini/team3/item/list?p=${currentItemPage}&f=' + field + '&q=' + query;
			<!-- location.href = encodeURI(uri); -->
		}
	</script>
</head>
<body>
	 <div class="container mt-3" style="margin-right: 117px">
        <div class="row justify-content-end">
        <div class="col-1"></div>
        <div class="col-6">
        	<c:if test="${not empty sessUid}">
        	<a class="btn btn-primary" href="/mini/team3/item/insert">상품 등록하기</a>
        	</c:if>
        	<c:if test="${empty sessUid}">
        	<a class="btn btn-primary" href="/mini/team3/user/login" >상품 등록하기</a>
        	</c:if>
        </div>
            <div class="col-5">
                <!-- 검색 입력란 추가 -->
                <table class="w-70">
                    <tr>
                    	<td style="width: 16%">
							<select class="form-control" id="field">
								<option value="name" ${field eq 'name' ? 'selected' : ''}>상품명</option>
								<option value="category" ${field eq 'category' ? 'selected' : ''}>분류</option>
							</select>
						</td>
                        <td style="width: 70%">
                            <c:if test="${empty query}">
                                <input class="form-control" type="text" id="query" placeholder="검색할 내용">
                            </c:if>
                            <c:if test="${not empty query}">
                                <input class="form-control" type="text" id="query" value="${query}">
                            </c:if>
                        </td>
                        <td style="width: 14%">
                            <button class="btn btn-outline-primary" onclick="search()">검색</button>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>	
    <div class="container mt-3">
	    <c:forEach var="item" items="${itemList}" varStatus="loopStatus">
	        <c:if test="${loopStatus.index % 3 eq 0}">
	            <div class="row">
	        </c:if>
			
	        <div class="col-4">
	        	<a href="/mini/team3/item/detail?mid=${item.mid}">
	            <div class="card mb-3 mx-auto shadow pt-1" style="width:18em">
	                <img class="mx-auto" src="/mini/img/logo_noBG.png" width="100px" height="100px">
	                <div class="card-body">
	                	<h5 class="card-title mb-0" style="color: black;">${item.name}</h5>
	                    <hr>
	                    <p class="card-text text-start"><h4 class="d-inline">${item.price}</h4>&nbsp;원</p>
	                    <p class="card-text text-end">${item.category}</p>
	                </div>
	            </div>
	            </a>
	        </div>
	
	        <c:if test="${loopStatus.index % 3 eq 2 or loopStatus.last}">
    			</div>
	        </c:if>
	    </c:forEach>
	    <ul class="pagination justify-content-center mt-4">
	        <li class="page-item"><a class="page-link" href="#">&laquo; 이전</a></li>
	        <c:forEach var="page" items="${pageList}" varStatus="pageStatus">
	           	                <li class="page-item ${currentUserPage eq page ? 'active' : ''}">
	                    <a class="page-link" href="/mini/team3/item/list?p=${page}">${page}</a>
	                </li>
	        </c:forEach>
	        <li class="page-item"><a class="page-link" href="#">다음 &raquo;</a></li>
	    </ul>
	</div>
</body>
</html>
