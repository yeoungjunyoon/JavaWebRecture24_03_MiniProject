<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/_head.jspf" %>
  	<style>
    	td { text-align: center; }
	</style>
</head>
  	
  <div class="container" style="margin-top: 50px;">
    <div class="row">
      <div class="col-4"></div>
      <div class="col-4">
        <div class="card">
          <div class="card-body">
            <div class="card-title text-center"><h3><strong>로그인</strong></h3></div>
            <hr>
            <form action="/mini/team3/user/login" method="post">
              <table class="table table-borderless">
                <tr>
                  <td>
                  	<input type="text" name="uid" class="form-control" placeholder="아이디 입력...">
                  </td>
                </tr>
                <tr>
                  <td><input type="password" name="pwd" class="form-control" placeholder="비밀번호 입력..."></td>
                </tr>
                <tr>
                  <td colspan="2">
                    <button class="btn btn-primary" type="submit">로그인</button>
                    <button class="btn btn-secondary" type="reset">취소</button>
                  </td>
                </tr>
              </table>
            </form>
            <p class="mt-3 text-center">
              <span>아이디가 없으신가요?&nbsp;&nbsp;</span><a href="/mini/team3/user/register">회원 가입</a>
            </p>
          </div>
        </div>
        </div>
      </div>
      <div class="col-4"></div>
    </div>
  </div>
  
  <%@ include file="../common/_bottom.jspf" %>
</body>
</html>