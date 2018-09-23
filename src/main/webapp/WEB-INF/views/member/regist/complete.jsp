<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Humming | 会員登録</title>
<link rel="stylesheet" href="../../../../css/header.css">
<link rel="stylesheet" href="../../../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../../../css/style-regist.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../../common/header.jsp"%>
        <div class="wrapper row">
            <div class="panel panel-default">
                <div class="panel-body title">
                    <span>Humming 会員登録</span>
                </div>
            </div>
            <div class="row row-steps mb-20 step-bar">
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <h3 class="steps-title">
                        <b>1. 会員情報の入力</b>
                    </h3>
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-steps">
                    <h3 class="steps-title">
                        <b>2. 入力内容の確認</b>
                    </h3>
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-steps">
                    <h3 class="steps-title-active">
                        <b>３. 会員登録の完了</b>
                    </h3>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body complete text-center">
                    <span>会員登録が完了しました</span><br>
                    <br> <a href="/"><span>お買い物を始める</span></a>
                </div>
            </div>
        </div>
        <%@ include file="../../common/footer.jsp"%>
    </div>
    <script src="../../js/jquery-3.1.1.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>