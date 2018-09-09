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
<link rel="stylesheet" href="../../../css/style-cart.css" />
<link rel="stylesheet" href="../../../css/header.css" />
<link rel="stylesheet" href="../../../css/bootstrap.min.css" />
<link rel="stylesheet"
    href="https://fonts.googleapis.com/icon?family=Material+Icons" />
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                <div class="panel-body">
                    <c:choose>
                        <c:when test="${empty orderItemList}">
                            <div id="message">カートに商品はありません。</div>
                            <div id="back">
                                <a href="${pageContext.request.contextPath}/">トップへ戻る </a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div id="title">カートの中身</div>
                            <c:forEach var="item" items="${itemList}">
                                <div class="item">
                                    <div class="img">
                                        <img src="data:jpg;base64, ${item.image}"
                                            class="img-thumbnail" width="180px;">
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="singer">
                                                <c:out value="${item.singer}" />
                                                /
                                                <c:out value="${item.song}" />
                                            </div>
                                            <div class="price">
                                                <c:forEach var="orderItem" items="${orderItemList}">
                                                    <c:if test="${item.id == orderItem.itemId}">
                                                        価格: <fmt:formatNumber value="${item.price}" pattern="###,###" /> 円<br>
                                                        数量: <c:out value="${orderItem.quantity}" /> 個<br>

                                                        <form:form modelAttribute="orderItemForm" action="${pageContext.request.contextPath}/order/cart/delete">
                                                            <input type="hidden" name="itemId" value="${item.id}">
                                                            <input type="hidden" name="quantity" value="${orderItem.quantity}">
                                                            <input type="submit" value="カートから削除" class="delete-btn">
                                                        </form:form>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="box">
                                <c:if test="${order.totalPrice > 0}">
                                    <div class="total">
                                        合計金額: <span class="totalPrice"><fmt:formatNumber
                                                value="${order.totalPrice}" pattern="###,###" /></span> 円
                                    </div>
                                </c:if>
                                <div class=return>
                                    <a href="${pageContext.request.contextPath}/order/confirm"
                                        class="susumu">注文確認画面へ進む </a>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>