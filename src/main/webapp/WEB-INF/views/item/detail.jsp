<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | アイテム詳細</title>
<link rel="stylesheet" href="../../../css/style-detail.css">
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                <div class="panel-body title">
                    <span>アイテム詳細</span>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="item">
                        <div class="img" style="float: left;">
                            <img src="data:jpg;base64, ${item.image}" class="img-thumbnail"
                                width="180px;">
                        </div>
                        <div style="float: left;">
                        <div class="song">
                            <c:out value="${item.song}" /> / <a href="#"><c:out value="${item.singer}" /></a>
                        </div>
                        <div class="description">
                            <c:out value="${item.description}" />
                        </div>
                        </div>
                        <div id="detail" style="float: right;">
                            <div class="panel panel-default">
                                <div class="panel-heading text-center">
                                    <b>ご購入はこちらから</b>
                                </div>
                                <div class="panel-body">
                                    <div>
                                        価格 <span id="price"><fmt:formatNumber value="${item.price}" pattern="###,###" /></span>円
                                    </div>
                                    <form:form modelAttribute="addItemForm"
                                        action="${pageContext.request.contextPath}/order/cart/add"
                                        name="addItemForm">
                                        <div class="quantity">
                                            数量: <select name="quantity" id="select"
                                                onClick="calculateTotalAmount()">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                            </select>
                                            <div id="totalPrice">
                                                この商品の合計金額: <span id="totalAmount"><fmt:formatNumber value="${item.price}" pattern="###,###" /></span>円
                                            </div>
                                            <div class="cart">
                                                <input type="hidden" name="itemId" value="${item.id}">
                                                <input type="submit" value="買い物かごに入れる" class="cart-btn">
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
        <%@ include file="../common/footer.jsp"%>
        </div>
    </div>

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/calculate-total-amount.js"></script>
</body>
</html>