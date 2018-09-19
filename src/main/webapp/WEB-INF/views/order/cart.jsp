<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | 買い物かご</title>
<link rel="stylesheet" href="../../../css/style-cart.css" />
<link rel="stylesheet" href="../../../css/header.css" />
<link rel="stylesheet" href="../../../css/bootstrap.min.css" />
<link rel="stylesheet"
    href="https://fonts.googleapis.com/icon?family=Material+Icons" />
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
    <div id="content-main">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                    <div class="panel-body title">
                        <span>買い物かご</span>
                    </div>
            </div>

            <div class="row">
                <ul class="progressbar_02">
                    <li class="active">アイテムの追加</li>
                    <li>注文内容の確認</li>
                    <li>注文内容の確定</li>
                </ul>
            </div>

            <div class="main panel panel-default">
                <div class="panel-body">
                    <c:choose>
                        <c:when test="${empty orderItemList}">
                            <div id="message">アイテムが入っていません</div>
                            <div id="back">
                                <a href="/" class="btn btn-default">Humming トップへ</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                            <c:forEach var="orderItem" items="${orderItemList}">
                                <div class="item">
                                    <div class="img">
                                        <img src="data:jpg;base64, ${orderItem.itemInfo.image}"
                                            class="img-thumbnail" width="140px;">
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="singer">
                                                <c:out value="${orderItem.itemInfo.singer}" />
                                                /
                                                <c:out value="${orderItem.itemInfo.song}" />
                                            </div>
                                            <div class="price">
                                                <span><fmt:formatNumber value="${orderItem.itemInfo.price}" pattern="###,###" /></span> <font style="color: red; margin-right: 15px;">円</font>
                                                数量: <c:out value="${orderItem.quantity}" /> 個<br>
                                                <form:form modelAttribute="deleteItemForm" action="${pageContext.request.contextPath}/order/cart/delete">
                                                    <input type="hidden" name="id" value="${orderItem.id}">
                                                    <input type="hidden" name="itemId" value="${orderItem.itemId}">
                                                    <input type="hidden" name="orderId" value="${orderItem.orderId}">
                                                    <input type="hidden" name="quantity" value="${orderItem.quantity}">
                                                    <input type="submit" value="削除する" class="btn btn-default delete-btn">
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            </div>
                            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 purchase">
                                <div>
                                    <a href="${pageContext.request.contextPath}/order/confirm" class="purchase-btn">ご購入手続き</a>
                                </div>
                                <c:if test="${order.totalPrice > 0}">
                                    <div class="total">
                                        小計 <fmt:formatNumber value="${order.totalPrice}" pattern="###,###" /> 円
                                    </div>
                                </c:if>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="row">
            <%@ include file="../common/footer.jsp"%>
        </div>
        </div>
    </div>
    <script src="../../../js/jquery-3.1.1.min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
</body>
</html>