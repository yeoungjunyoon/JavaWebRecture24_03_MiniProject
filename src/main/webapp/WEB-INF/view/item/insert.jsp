<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
	<div class="container mx-auto" style="margin-top:80px">
    	<div class="row">
			<!-- //// 본문 영역 //// -->
	       	<div class="col-2"></div>
			<div class="col-8">
				<h3><strong class="me-5">상품 등록</strong>
				</h3>
       			<hr>
		       	<div class="col-1"></div>
		       	<div class="col-10 mx-auto">
		       	<form action="/mini/team3/item/insert" method="post">
					<table class="table table-borderless">
					<tr>
						<select class="form-select w-25" name="category">
							<option value="간식" ${Scategory eq '간식' ? 'selected' : ''}>간식</option>
							<option value="미용" ${Scategory eq '미용' ? 'selected' : ''}>미용</option>
							<option value="침구" ${Scategory eq '침구' ? 'selected' : ''}>침구</option>
							<option value="기타" ${Scategory eq '기타' ? 'selected' : ''}>기타</option>
						</select><br><br>
					</tr>
					<tr>
					  	<input class="form-control w-50" type="text" name="name" value="${Sname}" placeholder="상품명"><br>
						<input class="form-control w-50" type="text" name="price" value="${Sprice}" placeholder="가격"><br><br>
					</tr>
					<tr>
						<textarea class="form-control-plaintext border border-2 border-dark h-25" rows="10" name="description" placeholder=" 상품 설명을 입력하세요...">${Sdescription}</textarea><br><br>
					</tr>
					<tr>
						<td colspan="2">
						    <button class="btn btn-primary" type="submit">확인</button>
						    <button class="btn btn-secondary" type="reset">취소</button>
						</td>
					</tr>
              		</table>
		       	</form>
		       	</div>
		       	<div class="col-1"></div>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
	<%@ include file="../common/_bottom.jspf" %>
</body>
</html>