<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Humming | アイテム検索</title>
<link rel="stylesheet" href="../../../css/style-search.css">
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div class="wrapper row">
            <div class="panel panel-default">
                <div class="text-left panel-body title">
                    <span>アイテム検索</span>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <div id="message">
                        <c:out value="${searchResultMessage}" />
                    </div>
                    <c:forEach var="item" items="${itemList}">
                        <div class="single-item col-lg-2 col-md-2 col-sm-2 col-xs-6">
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
                                    価格
                                    <fmt:formatNumber value="${item.price}" pattern="###,###" />
                                    円
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%@ include file="../common/footer.jsp"%>
    </div>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>