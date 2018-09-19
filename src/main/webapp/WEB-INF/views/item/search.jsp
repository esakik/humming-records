<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | アイテム検索</title>
<link rel="stylesheet" href="../../../css/style-search.css">
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
</head>
<body style="background-color: whitesmoke;">
<div class="container-fluid">
    <div id="content-main">
    <%@ include file="../common/header.jsp"%>
    <div id="content">
        <div class="panel panel-default">
            <div class="text-left panel-body title">
                <span>アイテム検索</span>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                <div id="message">
                    <c:out value="${searchResultMessage}"/>
                </div>
                <c:forEach var="item" items="${itemList}">
                    <div class="single-item col-lg-3 col-md-3 col-sm-4 col-xs-6">
                        <div id="item">
                            <div class="img">
                                <img src="data:jpg;base64, ${item.image}" class="img-thumbnail">
                            </div>
                            <div class="singer">
                                <a href="${pageContext.request.contextPath}/detail?itemId=${item.id}" style="text-decoration: none"><c:out value="${item.singer}" /></a>
                            </div>
                            <div class="song">
                                <c:out value="${item.song}" />
                            </div>
                            <div class="price">
                                価格 <fmt:formatNumber value="${item.price}" pattern="###,###" />円
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="row">
            <%@ include file="../common/footer.jsp"%>
    </div>
    </div>
</div>

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>