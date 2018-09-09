<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | 注文履歴</title>
<link rel="stylesheet" href="../../../css/style-order-history.css" />
<link rel="stylesheet" href="../../../css/header.css" />
<link rel="stylesheet" href="../../../css/bootstrap.min.css" />
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                <div class="panel-body">
                    <c:choose>
                        <c:when test="${empty orderList}">
                            <div id="message">注文履歴はありません。</div>
                        </c:when>
                        <c:otherwise>
                            <div id="title">注文履歴</div>
                            <c:forEach var="order" items="${orderList}">
                                <div class="item">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <c:forEach var="orderItem" items="${orderItemList}">
                                                <c:if test="${order.id == orderItem.orderId}">
                                                    <c:forEach var="item" items="${itemList}">
                                                        <c:if test="${orderItem.itemId == item.id}">
                                                            <div class="panel panel-default">
                                                                <div class="panel-body">
                                                                    <div class="confirm">
                                                                        <table>
                                                                            <tr>
                                                                                <th>商品名:&nbsp;</th>
                                                                                <td><c:out value="${item.singer}" /> / <c:out
                                                                                        value="${item.song}" /></td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th>商品価格:&nbsp;</th>
                                                                                <td><fmt:formatNumber value="${item.price}"
                                                                                        pattern="###,###" /> 円</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th>注文数量:&nbsp;</th>
                                                                                <td><c:out value="${orderItem.quantity}" /></td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>

                                                </c:if>
                                            </c:forEach>

                                            <div class="history">
                                                <table>
                                                    <tr>
                                                        <th>ご注文日:&nbsp;</th>
                                                        <td><fmt:formatDate value="${order.orderDate}"
                                                                pattern="yyyy/MM/dd" /></td>
                                                    </tr>
                                                    <tr>
                                                        <th>購入金額:&nbsp;</th>
                                                        <td><fmt:formatNumber value="${order.totalPrice}"
                                                                pattern="###,###" /> 円</td>
                                                    </tr>
                                                    <tr>
                                                        <th>宛先氏名:&nbsp;</th>
                                                        <td><c:out value="${order.deliveryName}" /></td>
                                                    </tr>
                                                    <tr>
                                                        <th>Email:&nbsp;</th>
                                                        <td><c:out value="${order.deliveryEmail}" /></td>
                                                    </tr>
                                                    <tr>
                                                        <th>宛先住所:&nbsp;</th>
                                                        <td><c:out value="${order.deliveryAddress}" /></td>
                                                    </tr>
                                                    <tr>
                                                        <th>電話番号:&nbsp;</th>
                                                        <td><c:out value="${order.deliveryTelephone}" /></td>
                                                    </tr>
                                                    <tr>
                                                        <th>配達日時:&nbsp;</th>
                                                        <td><fmt:formatDate value="${order.deliveryTime}"
                                                                pattern="yyyy/MM/dd HH:00" /></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <div id="back">
                        <a href="${pageContext.request.contextPath}/">トップへ戻る </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>