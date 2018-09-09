<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script>
    function selectQuantity() {
        var num = document.shoppingCartForm.quantity.selectedIndex;
        var quantity = document.shoppingCartForm.quantity.options[num].value;
        var value = "<c:out value="${item.price}" />";
        document.getElementById("totalAmount").innerHTML = [ quantity * value ].toString().replace(/(\d)(?=(\d{3})+$)/g, '$1,');
    }
</script>
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                <div class="panel-body">
                <div class="item">
                <div class="img">
                    <img src="data:jpg;base64, ${item.image}" class="img-thumbnail" width="180px;">
                    </div>
                    <div class="singer">
                        <c:out value="${item.singer}" />
                        /
                        <c:out value="${item.song}" />
                    </div>
                    <div class="description">
                        <c:out value="${item.description}" />
                    </div>
                    <div id="detail">
                    <div class="panel panel-default">
                <div class="panel-body">
                <div class="price">
                        価格
                        <fmt:formatNumber value="${item.price}" pattern="###,###" />
                        円
                    </div>
                    <form:form modelAttribute="shoppingCartForm"
                        action="${pageContext.request.contextPath}/order/cart/add"
                        name="shoppingCartForm">
                        <div class="quantity">
                            数量: <select name="quantity" id="select" onClick="selectQuantity()">
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
                                <input type="hidden" name="itemId" value="${item.id}"> <input
                                    type="submit" value="カートに入れる" class="cart-btn">
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
        </div>

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>