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
<link rel="stylesheet" href="../../../../css/header.css">
<link rel="stylesheet" href="../../../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../../../css/style-regist.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../../common/header.jsp"%>
        <div id="content">
            <div class="row">
                <div class="main panel panel-default">
                    <div class="panel-body title">
                        <span>Humming 会員登録</span>
                    </div>
                </div>

                <div class="row row-steps mb-20 step-bar">
                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <h3 class="steps-title-active">
                            <b>1. 会員情報の入力</b>
                        </h3>
                    </div>
                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-steps">
                        <h3 class="steps-title">
                            <b>2. 入力内容の確認</b>
                        </h3>
                    </div>
                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-steps">
                        <h3 class="steps-title">
                            <b>３. 会員登録の完了</b>
                        </h3>
                    </div>
                </div>

                <form:form modelAttribute="registForm" action="/member/regist/confirm"
                    class="form-container">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <b>お客様の基本情報</b>
                        </div>
                        <table class="table table-bordered">
                            <tr>
                                <th>お名前<span class="error"><form:errors path="name"
                                            element="div" /></span></th>
                                <th><form:input class="form-control input-sm form-field"
                                        path="name" placeholder="（例）田中太郎" /></th>
                            </tr>
                            <tr>
                                <th>住所<span class="error"><form:errors
                                            path="address" element="div" /></span></th>
                                <th><form:input class="form-control input-sm form-field"
                                        path="address" placeholder="（例）東京都杉並区高円寺1-1-1" /></th>
                            </tr>
                            <tr>
                                <th>電話番号<span class="error"><form:errors
                                            path="telephone" element="div" /></span></th>
                                <th><form:input class="form-control input-sm form-field"
                                        path="telephone" placeholder="（例）080-1234-5678" /></th>
                            </tr>
                        </table>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <b>ログイン情報</b>
                        </div>
                        <table class="table table-bordered">
                            <tr>
                                <th>メールアドレス<span class="error"><form:errors path="email" element="div" /></span><br><span class="error"><c:out value="${emailError}"/></span></th>
                                <th><form:input class="form-control input-sm form-field" path="email" placeholder="（例）user@gmail.com" /></th>
                            </tr>
                            <tr>
                                <th>パスワード<span class="error"><form:errors path="password" element="div" /></span><br><span class="error"><c:out value="${passwordError}" /></span></th>
                                <th><form:password class="form-control input-sm form-field" path="password" placeholder="8文字以上半角英数字" /></th>
                            </tr>
                            <tr>
                                <th>パスワード (確認)<span class="error"><form:errors path="confirmationPassword" element="div" /></span></th>
                                <th><form:password class="form-control input-sm form-field" path="confirmationPassword" placeholder="8文字以上半角英数字" /></th>
                            </tr>
                        </table>
                    </div>
                    <div class="submit-container">
                        <input class="submit-button" type="submit" value="入力内容の確認へ" />
                    </div>

                </form:form>
            </div>
        </div>
        <div class="row">
        <%@ include file="../../common/footer.jsp"%>
        </div>
    </div>

    <script src="../../js/jquery-3.1.1.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>