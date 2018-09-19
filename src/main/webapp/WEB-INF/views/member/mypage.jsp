<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | マイページ</title>
<link rel="stylesheet" href="../../../css/style-mypage.css">
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
    <div id="content-main">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                <div class="panel-body title">
                    <span>マイページ</span>
                </div>
            </div>
            <div class="row">
                    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="main panel panel-default">
                            <div class="panel-heading text-left">
                                <b>注文履歴</b>
                            </div>
                            <div class="panel-body">
                                <c:choose>
                                    <c:when test="${empty orderList}">
                                        <div id="message">注文履歴はありません</div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="order" items="${orderList}">
                                            <div class="item">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading text-left">
                                                        <b>注文日「<fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd" />」</b>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading text-left">
                                                                <b>アイテム情報</b>
                                                            </div>
                                                            <div class="panel-body">
                                                            <c:forEach var="orderItem" items="${orderItemList}">
                                                                <c:if test="${order.id == orderItem.orderId}">
                                                                    <c:forEach var="item" items="${itemList}">
                                                                        <c:if test="${orderItem.itemId == item.id}">
                                                                                <div class="confirm">
                                                                                    <table class="table table-bordered">
                                                                                        <tr>
                                                                                            <th>アイテム名</th>
                                                                                            <td><c:out value="${item.song}" /> / <c:out
                                                                                                    value="${item.singer}" /></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <th>アイテム価格</th>
                                                                                            <td><fmt:formatNumber value="${item.price}"
                                                                                                    pattern="###,###" />円</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <th>注文数量</th>
                                                                                            <td><c:out value="${orderItem.quantity}" /></td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </div>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:if>
                                                            </c:forEach>
                                                            </div>
                                                        </div>
                                                        <div class="history">
                                                            <div class="panel panel-default">
                                                                <div class="panel-heading text-left">
                                                                    <b>送付先情報</b>
                                                                </div>
                                                                <div class="panel-body">
                                                                    <table class="table table-bordered">
                                                                        <tr>
                                                                            <th>送付先氏名</th>
                                                                            <td><c:out value="${order.deliveryName}" /></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>送付先住所</th>
                                                                            <td><c:out value="${order.deliveryAddress}" /></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>送付日時</th>
                                                                            <td><fmt:formatDate
                                                                                    value="${order.deliveryTime}"
                                                                                    pattern="yyyy/MM/dd HH:00" /></td>
                                                                        </tr>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                            <div class="text-right price">
                                                                購入金額
                                                                <fmt:formatNumber value="${order.totalPrice}"
                                                                    pattern="###,###" />
                                                                円
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <div class="main panel panel-info">
                            <div class="panel-heading text-center">
                                <b>こんにちは、<c:out value="${member.name}" />さん</b>
                            </div>
                            <div class="panel-body">
                                <div class="logout-btn">
                                   <a href="/member/logout" class="btn btn-default">ログアウト</a>
                                </div>
                                <div class="withdraw-btn">
                                    <a href="/member/withdraw/confirm" class="btn btn-default">Humming を退会する</a>
                                </div>
                            </div>
                        </div>
                    </div>
        </div>
        </div>
        <p class="row" id="scrollTop">
            <a href="#" style="text-decoration: none;">page top</a>
        </p>
        <div class="row">
            <%@ include file="../common/footer.jsp"%>
        </div>
        </div>
    </div>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/scrolltop.js"></script>
</body>
</html>