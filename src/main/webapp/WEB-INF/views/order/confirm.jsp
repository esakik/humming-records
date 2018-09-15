<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | 注文内容のご確認</title>
<link rel="stylesheet" href="../../../css/style-order-confirm.css" />
<link rel="stylesheet" href="../../../css/header.css" />
<link rel="stylesheet" href="../../../css/bootstrap.min.css" />
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                <div class="panel-body title">
                    <span>注文内容の確認</span>
                </div>
            </div>

            <div class="row">
                <ul class="progressbar_02">
                    <li>アイテムの追加</li>
                    <li class="active">注文内容の確認</li>
                    <li>注文内容の確定</li>
                </ul>
            </div>

            <div class="row">
            <form:form modelAttribute="orderForm"
                action="${pageContext.request.contextPath}/order/redirect">
                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                    <div class="main panel panel-default">
                        <div class="panel-heading text-left">
                            <b>注文アイテム</b>
                        </div>
                        <div class="panel-body">
                            <c:forEach var="item" items="${itemList}">
                                <div class="item">
                                    <div class="img">
                                        <img src="data:jpg;base64, ${item.image}"
                                            class="img-thumbnail" width="140px;">
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="singer">
                                                <c:out value="${item. singer}" />
                                                /
                                                <c:out value="${item. song}" />
                                            </div>
                                            <div class="price">
                                                <c:forEach var="orderItem" items="${orderItemList}">
                                                    <c:if test="${item.id == orderItem.itemId}">
                                                価格: <fmt:formatNumber
                                                            value="${item.price}" pattern="###,###" /> 円<br>
                                                数量: <c:out
                                                            value="${orderItem.quantity}" /> 個<br>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading text-left">
                            <b>送付先</b>
                        </div>
                        <div class="panel-body">
                            <table class="table table-bordered">
                                <tbody>
                                    <tr>
                                        <th>
                                            お名前<form:errors path="deliveryName" cssStyle="color:red" element="div" />
                                        </th>
                                        <th>
                                            <input type="text" name="deliveryName" style="width: 100%;">
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>
                                            メールアドレス<form:errors path="deliveryEmail" cssStyle="color:red" element="div" />
                                        </th>
                                        <th>
                                            <input type="text" name="deliveryEmail" style="width: 100%;">
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>
                                            住所<form:errors path="deliveryAddress" cssStyle="color:red" element="div" />
                                        </th>
                                        <th>
                                            <input type="text" name="deliveryAddress" style="width: 100%;">
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>
                                            電話番号<form:errors path="deliveryTelephone" cssStyle="color:red" element="div" />
                                        </th>
                                        <th>
                                            <input type="text" name="deliveryTelephone" style="width: 100%;">
                                        </th>
                                    </tr>
                                    <tr>
                                        <th colspan="2">
                                            <div class="text-center">ご希望の配達日時を入力してください</div>
                                        </th>
                                    </tr>
                                    <tr>
                                        <td align="center" colspan="2">
                                            <div class="form-group">
                                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                                        <input type="date" name="deliveryTime" id="name" min="2018-09-14" class="form-control input-sm" required />
                                                    </div>
                                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                                        <label class="radio-inline"> <input type="radio"
                                                            name="deliverySpecifiedTime" value="10" checked="checked">
                                                            10時
                                                        </label> <label class="radio-inline"> <input type="radio"
                                                            name="deliverySpecifiedTime" value="11"> 11時
                                                        </label> <label class="radio-inline"> <input type="radio"
                                                            name="deliverySpecifiedTime" value="12"> 12時
                                                        </label><br> <label class="radio-inline"> <input
                                                            type="radio" name="deliverySpecifiedTime" value="13">
                                                            13時
                                                        </label> <label class="radio-inline"> <input type="radio"
                                                            name="deliverySpecifiedTime" value="14"> 14時
                                                        </label> <label class="radio-inline"> <input type="radio"
                                                            name="deliverySpecifiedTime" value="15"> 15時
                                                        </label><br> <label class="radio-inline"> <input
                                                            type="radio" name="deliverySpecifiedTime" value="16">
                                                            16時
                                                        </label> <label class="radio-inline"> <input type="radio"
                                                            name="deliverySpecifiedTime" value="17"> 17時
                                                        </label> <label class="radio-inline"> <input type="radio"
                                                            name="deliverySpecifiedTime" value="18"> 18時
                                                        </label>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="main" style="position: fixed;">
                        <input type="submit" value="注文を確定する" class="submit-btn"> <input
                            type="hidden" name="totalPrice" value="${order.totalPrice}">
                        <div class="main panel panel-default">
                            <div class="panel-body">
                                <div class="total text-center">
                                    <span>総合計</span> <span class="totalPrice"><fmt:formatNumber
                                            value="${order.totalPrice}" pattern="###,###" />円</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
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