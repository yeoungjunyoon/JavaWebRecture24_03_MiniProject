<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/view/common/_head.jspf" %>
	<style>
    	td { text-align: center; }
	</style>	
</head>
<body>
	<%@ include file="/WEB-INF/view/common/_top.jspf" %>
	
	<div class="container" style="margin-top:110px">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-9">
				<h3 class="d-inline"><strong>마이 페이지</strong></h3>
				<hr>
				<div class="row">
					<div class="col-1"></div>
					<div class="col-10 mx-auto">
						<input type="hidden" name="uid" value="${user.uid}">
						<input type="hidden" name="hashedPwd" value="${user.pwd}">
						<table class="table table-borderless">
							<tr>
			                  <td style="width: 40%;">사용자 ID</td>
			                  <td style="width: 60%;"><input type="text" name="uid" value="${user.uid}" disabled></td>
			                </tr>
			                <tr>
			                  <td>사용자 이름</td>
			                  <td><input type="text" name="uname" value="${user.uname}" disabled></td>
			                </tr>
			                <tr>
			                  <td>이메일</td>
			                  <td><input type="text" name="email" value="${user.email}" disabled></td>
			                </tr>
			                <tr>
			                  <td class="pt-2" colspan="2">
			                    <button class="btn btn-primary" onclick="location.href='/mini/team3/user/update?uid=${user.uid}'">개인정보 수정</button>
			                  </td>
			                </tr>
						</table>
					</div>
				<div class="col-1"></div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/view/common/_bottom.jspf" %>
</body>
</html>