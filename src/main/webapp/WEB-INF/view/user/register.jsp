<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../common/_head.jspf" %>
  	<style>
    	td { text-align: center; }
	</style>	
</head>
<body class="bg-light">

 
  <div class="container" style="margin-top: 50px;">
    <div class="row">
      <div class="col-4"></div>
      <div class="col-4">
        <div class="card">
          <div class="card-body">
            <div class="card-title text-center"><h3><strong>사용자 가입</strong></h3></div>
            <hr>
            <form action="/mini/team3/user/register" method="post">
              <table class="table table-borderless">
                <tr>
                  <td style="width: 45%;"><label class="col-form-label">아이디 입력</label></td>
                  <td style="width: 55%;"><input type="text" name="uid" value="${Suid}" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">비밀번호</label></td>
                  <td><input type="password" name="pwd" value="${Spwd}" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">비밀번호 확인</label></td>
                  <td><input type="password" name="pwd2" value="${Spwd2}" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">닉네임</label></td>
                  <td><input type="text" name="uname" value="${Suname}" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">이메일</label></td>
                  <td><input type="text" name="email" value="${Semail}" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">주소</label></td>
                  <td><input type="text" name="address" value="${Saddress}" class="form-control"></td>
                </tr>
                <tr>
                  <td colspan="2">
                    <button class="btn btn-primary" type="submit">확인</button>
                    <button class="btn btn-secondary" type="reset">취소</button>
                  </td>
                </tr>
              </table>
            </form>
            <p class="mt-3 text-center">
             <span>이미 아이디가 있다면?&nbsp;&nbsp;</span><a href="/mini/team3/user/login">로그인</a>
            </p>
          </div>
        </div>
      </div>
      <div class="col-4"></div>
    </div>
  </div>
  
  <%@ include file="../common/_bottom.jspf" %>
</body>
</html>