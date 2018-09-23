<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Humming | 注文完了</title>
<link rel="stylesheet" href="../../../css/style-order-complete.css" />
<link rel="stylesheet" href="../../../css/header.css" />
<link rel="stylesheet" href="../../../css/bootstrap.min.css" />
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div class="wrapper">
            <div class="panel panel-default">
                <div class="panel-body title">
                    <span>注文内容の確定</span>
                </div>
            </div>
            <div class="row">
                <ul class="progressbar">
                    <li>アイテムの追加</li>
                    <li>注文内容の確認</li>
                    <li class="active">注文内容の確定</li>
                </ul>
            </div>
            <div class="main panel panel-default">
                <div class="panel-body complete text-center">
                    <span>注文が確定しました</span><br> <br> <a href="/"><span>お買い物を続ける</span></a>
                </div>
            </div>
        </div>
        <%@ include file="../common/footer.jsp"%>
    </div>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>