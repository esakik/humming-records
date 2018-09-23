<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Humming | 問い合わせ</title>
<link rel="stylesheet" href="../../../css/style-contact.css">
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div class="wrapper">
            <div class="panel panel-default">
                <div class="panel-heading text-left">
                    <b>問い合わせフォーム</b>
                </div>
                <div class="panel-body">
                    <form class="form-container">
                        <div class="form-title">件名</div>
                        <input class="form-field" placeholder="（例）Humming について" /> <br />
                        <div class="form-title">問い合わせ内容</div>
                        <textarea placeholder="問い合わせ内容を入力してください。"></textarea>
                        <br />
                        <div class="submit-container">
                            <input class="submit-button" type="submit" value="送信" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="../common/footer.jsp"%>
    </div>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>