<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | ログイン</title>
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../../css/style-login.css">
</head>
<body style="background-color: whitesmoke;">
    <%@ include file="../common/header.jsp"%>
    <div class="container-fluid">
        <div id="content">
            <div class="row">
                <div class="error">
                    <c:out value="${error}" />
                </div>
                <div class="main panel panel-default">
                    <div class="panel-body">
                        <form:form modelAttribute="loginForm" action="/member/login"
                            class="form-container">
                            <div class="form-title">
                                <h2>Hummingにログイン</h2>
                            </div>
                            <div class="form-title">メールアドレス</div>
                            <form:input class="form-field" path="mailAddress"
                                placeholder="Email" />
                            <span class="errer"><form:errors path="mailAddress" /></span>
                            <br />
                            <div class="form-title">パスワード</div>
                            <form:password class="form-field" path="password"
                                placeholder="Password" />
                            <span class="errer"><form:errors path="password" /></span>
                            <br />
                            <a href="/member/registForm">新規会員登録はこちらから</a>
                            <div class="submit-container">
                                <input class="submit-button" type="submit" value="ログイン" />
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>