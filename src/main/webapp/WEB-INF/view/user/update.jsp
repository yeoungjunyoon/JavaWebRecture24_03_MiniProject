<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../common/_head.jspf" %>
	<style>
		td { text-align: center; }
	</style>
</head>
<body>
	
	<div class="container" style="margin-top: 50px">
		<div class="row">
			<div class="col-4"></div>
		      <div class="col-4">
		        <div class="card">
		          <div class="card-body">
		            <div class="card-title text-center"><h3><strong>개인정보 수정</strong></h3></div>
						<hr>
							<form action="/mini/team3/user/update" method="post">
								<input type="hidden" name="uid" value="${user.uid}">
								<input type="hidden" name="hashedPwd" value="${user.pwd}">
								<table class="table table-borderless">
									<tr>
										<td style="width: 40%;"><label class="col-form-label">사용자 ID</label></td>
										<td style="width: 60%;"><input type="text" class="form-control" value="${user.uid}" disabled></td>
									</tr>
									<tr>
										<td><label class="col-form-label">패스워드</label></td>
										<td><input type="password" name="pwd" class="form-control" required></td>
									</tr>
									<tr>
										<td><label class="col-form-label">패스워드 확인</label></td>
										<td><input type="password" name="pwd2" class="form-control" required></td>
									</tr>
									<tr>
										<td><label class="col-form-label">닉네임</label></td>
										<td><input type="text" name="uname" class="form-control" value="${user.uname}"></td>
									</tr>
									<tr>
										<td><label class="col-form-label">이메일</label></td>
										<td><input type="text" name="email" class="form-control" value="${user.email}"></td>
									</tr>
									<tr>
										<td><label class="col-form-label">주소</label></td>
										<td><input type="text" name="address" class="form-control" value="${user.address}"></td>
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
					<div class="col-3"></div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="../common/_bottom.jspf" %>
</body>
</html>