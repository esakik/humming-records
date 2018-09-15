<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | 問い合わせ</title>
<link rel="stylesheet" href="../../../css/style-contact.css">
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="main panel panel-default">
                <div class="panel-heading text-left">
                    <b>問い合わせフォーム</b>
                </div>
                <div class="panel-body">
                    <form class="form-container">
                        <div class="form-title">件名</div>
                        <input class="form-field" placeholder="（例）Humming について" />
                        <br />
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
        <div class="row">
            <%@ include file="../common/footer.jsp"%>
        </div>
    </div>

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>