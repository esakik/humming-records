<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | カート</title>
<link rel="stylesheet" href="../../../../css/style-withdraw-confirm.css" />
<link rel="stylesheet" href="../../../../css/header.css" />
<link rel="stylesheet" href="../../../../css/bootstrap.min.css" />
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div id="main">
                        <div id="title">退会の手続き</div>
                        <p>
                            退会をご希望される方へ<br> 退会されますと、本サービスがご利用できなくなります。<br>
                            確認の上、「退会」ボタンをクリックすると退会完了となります。
                        </p>
                        <form:form modelAttribute="userForm"
                            action="/member/withdraw/complete">
                            <input type="hidden" name="deleted" value="${member.id}">
                            <input type="submit" value="退会する" class="withdraw-btn">
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../../js/jquery-3.1.1.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>