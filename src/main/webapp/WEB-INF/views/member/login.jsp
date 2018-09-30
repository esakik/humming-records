<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Humming | ログイン</title>
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../../css/style-login.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div class="wrapper row">
            <form:form modelAttribute="loginForm" action="/member/login/complete" class="form-container">
                <c:if test="${not empty loginErrorMessage}">
                    <div class="alert alert-danger">
                        <c:out value="${loginErrorMessage}" />
                    </div>
                </c:if>
                <spring:hasBindErrors name="loginForm">
                    <div class="alert alert-danger">
                        <form:errors path="email" element="div" />
                        <form:errors path="password" element="div" />
                    </div>
                </spring:hasBindErrors>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-header">
                            <h2>Humming にログイン</h2>
                        </div>
                        <div class="service">このサービスをご利用になるにはログインしてください。</div>
                        <div class="form-title">メールアドレス</div>
                        <form:input class="form-field" path="email" placeholder="Email" />
                        <br />
                        <div class="form-title">パスワード</div>
                        <form:password class="form-field" path="password" placeholder="Password" />
                        <div class="submit-container">
                            <input class="submit-button" type="submit" value="ログイン" />
                        </div>
                    </div>
                </div>
            </form:form>
            <div class="regist">
                <p>まだ登録されていない方へ</p>
                <a href="/member/regist/input" class="regist-btn">新規会員登録はこちらから</a>
                <div class="about-humming">
                    <a href="#">Humming会員とは？</a>
                </div>
            </div>
        </div>
        <%@ include file="../common/footer.jsp"%>
    </div>
    <script src="../../../js/jquery-3.1.1.min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
</body>
</html>