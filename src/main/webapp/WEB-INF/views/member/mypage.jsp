<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | CDを選ぶ・楽しむ</title>
<link rel="stylesheet" href="../../../css/style-mypage.css">
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div id="main">
                        <div id="title">マイページ</div>
                        <div class="item"><a href="/order/history">ご注文履歴</a></div>
                        <div class="item"><a href="/member/logout">ログアウト</a></div>
                        <div class="item"><a href="/member/withdraw/confirm">退会をご希望される方</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>