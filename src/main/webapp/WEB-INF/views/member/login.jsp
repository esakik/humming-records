<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
                <form:form modelAttribute="loginForm" action="/member/login"
                    class="main form-container">
                    <c:if test="${not empty error || not empty email}">
                    <div class="alert alert-danger">
                        <c:out value="${error}" />
                    </div>
                    </c:if>
                    <spring:hasBindErrors name="loginForm">
                    <div class="alert alert-danger">
                    <form:errors path="email" element="div"/>
                    <form:errors path="password" element="div"/>
                    </div>
                    </spring:hasBindErrors>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form-header">
                                <h2>Hummingにログイン</h2>
                            </div>
                            <div class="form-title">メールアドレス</div>
                            <form:input class="form-field" path="email" placeholder="Email" />
                            <br />
                            <div class="form-title">パスワード</div>
                            <form:password class="form-field" path="password"
                                placeholder="Password" />
                            <br /> <a href="/member/registForm">新規会員登録はこちらから</a>
                            <div class="submit-container">
                                <input class="submit-button" type="submit" value="ログイン" />
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>