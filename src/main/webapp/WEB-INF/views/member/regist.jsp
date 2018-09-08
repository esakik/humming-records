<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | 会員登録</title>
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../../css/style-regist.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="row">
            <div class="error">
                <c:out value="${error}" />
            </div>
            <div class="main panel panel-default">
                    <div class="panel-body">
                <form:form modelAttribute="registForm" action="/member/regist" class="form-container">
                    <div class="form-title"><h2>会員登録</h2></div>

                    <div class="form-title">お名前</div>
                    <span class="error"><form:errors path="name" cssStyle="color:red" element="div"/></span>
                    <form:input class="form-field" path="name" placeholder="Name" /><br/>

                    <div class="form-title">メールアドレス</div>
                    <span class="error"><form:errors path="email" cssStyle="color:red" element="div"/></span>
                    <form:input class="form-field" path="email" placeholder="Email" /><br/>

                    <div class="form-title">住所</div>
                    <span class="error"><form:errors path="address" cssStyle="color:red" element="div"/></span>
                    <form:input class="form-field" path="address" placeholder="Address" /><br/>

                    <div class="form-title">電話番号</div>
                    <span class="error"><form:errors path="telephone" cssStyle="color:red" element="div"/></span>
                    <form:input class="form-field" path="telephone" placeholder="Telephone" /><br/>

                    <div class="form-title">パスワード</div>
                    <span class="error"><font color="red"><c:out value="${password}" /></font></span>
                    <span class="error"><form:errors path="password" cssStyle="color:red" element="div" /></span>
                    <form:password class="form-field" path="password" placeholder="Password" /><br/>

                    <div class="form-title">確認用パスワード</div>
                    <span class="error"><form:errors path="confirmationPassword" cssStyle="color:red" element="div" /></span>
                    <form:password class="form-field" path="confirmationPassword" placeholder="Password" />

                    <div class="submit-container">
                        <input class="submit-button" type="submit" value="登録" />
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