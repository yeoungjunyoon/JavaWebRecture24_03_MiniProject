<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/_head.jspf"%>
<script>
function increaseQuantity() {
    var quantityInput = document.querySelector('[name="quantity"]');
    var currentQuantity = parseInt(quantityInput.value, 10);
    quantityInput.value = currentQuantity + 1;
}

function decreaseQuantity() {
    var quantityInput = document.querySelector('[name="quantity"]');
    var currentQuantity = parseInt(quantityInput.value, 10);

    // 수량이 1 미만으로 내려가지 않도록 확인
    if (currentQuantity > 1) {
        quantityInput.value = currentQuantity - 1;
    }
}
</script>
</head>
<body style="height: 1000px">
	<section class="py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="row gx-4 gx-lg-5 align-items-center">
				<div class="col-md-6">
                	<img class="card-img-top mb-5 mb-md-0" src="/mini/img/logo_noBG.png">
				</div>
				<div class="col-md-6">
					<h1 class="display-5 fw-bolder">${item.name}</h1>
					<div class="fs-5 mb-5">
						<span>상품분류:${item.category}</span> <br> <span>등록일자:${fn:replace(item.itemTime, "T", " ")}</span>
						<br> <span><h1>${item.price}원</h1></span>
					</div>
					<hr class="my-4">
					<form action="/mini/team3/item/detail?mid=${item.mid}"
						class="d-flex" method="post">
						<div class="input-group fs-5 mb-5">
							<div class="input-group-prepend">
								<span class="input-group-text">주문 수량</span>
							</div>
							<button class="btn btn-outline-dark" type="button" onclick="decreaseQuantity()">
               				 -
           					 </button>
							<input name="quantity" class="form-control text-center"
								value="1" style="max-width: 10rem"/ >
							 <button class="btn btn-outline-dark" type="button" onclick="increaseQuantity()">
				                +
				            </button>
							<button class="btn btn-outline-dark" type="submit" style="margin-left:20px" >
							바로 구매하기
							</button>
							<button class="btn btn-outline-dark flex-shrink-0" type="button" style="margin-left:10px">
							장바구니 담기
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<section class="py-5 bg-light">
		<div class="container">
			<table class="table">
				<thead>
					<tr>
						<th class="bg-secondary p-3">상품상세</th>
						<th>상품평</th>
						<th>상품문의</th>
						<th>배송안내</th>
					</tr>
				</thead>
			</table>
		</div>
	</section>
	<p style="margin-left: 77px; font-size: 25px;">${item.description}</p>
	<%@ include file="../common/_bottom.jspf"%>
</html>