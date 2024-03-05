<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/_head.jspf" %>
<style>
	#mItemThumbnail {
		white-space: nowrap; 
		overflow:hidden; 
		text-overflow: ellipsis;
	}
	#mItemThumbnail:hover {
		transition: background-color 0.5s;
		background-color: silver;
	}
</style>
</head>
<body>
	<body style="height: 1000px;">
<%@ include file="../common/_top.jspf" %>
  <!--carousel-->
  <div id="demo" class="carousel slide" data-bs-ride="carousel">

    <!-- Indicators/dots -->
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
      <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
      <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
    </div>
    
    <!-- The slideshow/carousel -->
    <div class="carousel-inner mt-3">
      <div class="carousel-item active">
        <img src="/mini/img/main_img_1.png" class="d-block" id="main_carousel">
        <div class="carousel-caption">
        <!-- <h3>1번 화면</h3>
          <p>대충 그럴싸 한 내용</p> --> 
        </div>
      </div>
      <div class="carousel-item">
        <img src="/mini/img/main_img_2.png" class="d-block" id="main_carousel">
        <div class="carousel-caption">
          <h3>2번 화면</h3>
          <p>그럭저럭 괜찮은 내용</p>
        </div> 
      </div>
      <div class="carousel-item">
        <img src="/mini/img/main_img_3.png" class="d-block" id="main_carousel">
        <div class="carousel-caption">
 		<!-- <h3>3번 화면</h3>
          <p>대충 마지막이라는 내용</p> --> 
        </div>  
      </div>
    </div>
    
    <!-- Left and right controls/icons -->
    <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
      <span class="carousel-control-prev-icon"></span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
      <span class="carousel-control-next-icon"></span>
    </button>
  </div>
  
  <div class="container-fluid mt-5 pt-5">
    <a href="/mini/team3/item/list?p=1" style="color: black;"><h3 style="text-align: center;">상품</h3></a>
  </div>
  <!--
    상품 이미지를 쓴다면
    <ul>
        <c:forEach var="product" items="${Products}" varStatus="loop">
            <c:if test="${loop.index < 3}">
                <li>${product.productName} - ${product.price}</li>
            </c:if>
        </c:forEach>
    </ul>
  -->
  <!--style="margin-left: 40px;위치 설정--> 
  <div class="image-container">
   	<a href="/mini/team3/item/detail?mid=1" style="text-align: center; display: block;" class="text-dark w-25">
	    <img src="/mini/img/item_1.jpg" width="100px" height="100px" style="display: inline-block">
	    <span id="mItemThumbnail" style="display: block; margin-top: 5px;">트라이벌 프레쉬 프레스드 칠면조 노령견 2.5kg (일반키블)</span>
	</a>
   	<a href="/mini/team3/item/detail?mid=2" style="text-align: center; display: block;" class="text-dark w-25">
	    <img src="/mini/img/item_2.jpg" width="100px" height="100px" style="display: inline-block">
	    <span id="mItemThumbnail" style="display: block; margin-top: 5px;">맥아담스 방목 치킨 시니어/라이트 사료 2kg</span>
	</a>
    <a href="/mini/team3/item/detail?mid=3" style="text-align: center; display: block;" class="text-dark w-25">
	    <img src="/mini/img/item_3.jpg" width="100px" height="100px" style="display: inline-block">
	    <span id="mItemThumbnail" style="display: block; margin-top: 5px;">어거스틴 슈퍼부스트 비타민&미네랄 종합영양제 110g</span>
	</a>
   	<a href="/mini/team3/item/detail?mid=4" style="text-align: center; display: block;" class="text-dark w-25">
	    <img src="/mini/img/item_4.jpg" width="100px" height="100px" style="display: inline-block">
	    <span id="mItemThumbnail" style="display: block; margin-top: 5px;">카이쿠라 블렌드 고관절 슬개골탈구 뼈 강아지 관절영양제 210g</span>
	</a>
  </div>


  <!--bottom-->
  <%@ include file="../common/_bottom.jspf" %>
 
</body>
</html>