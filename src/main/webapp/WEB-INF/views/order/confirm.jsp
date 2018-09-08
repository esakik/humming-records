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
<link rel="stylesheet" href="../../../css/style-order-confirm.css" />
<link rel="stylesheet" href="../../../css/header.css" />
<link rel="stylesheet" href="../../../css/bootstrap.min.css" />
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>
        <div id="content">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="title">注文内容のご確認</div>

                    <c:forEach var="item" items="${itemList}">
                        <div class="item">
                            <div class="img">
                                <img src="data:jpg;base64, ${item.image}" class="img-thumbnail"
                                    width="180px;">
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="singer">
                                        <c:out value="${item. singer}" />
                                        /
                                        <c:out value="${item. song}" />
                                    </div>
                                    <div class="price">
                                        <c:forEach var="item2" items="${orderItemList}">
                                            <c:if test="${item.id == item2.itemId}">
                                価格: <fmt:formatNumber
                                                    value="${item.price}" pattern="###,###" /> 円
                                <br>
                                数量: <c:out value="${item2.quantity}" /> 個
                                <br>

                                                <script>
                                                            var delimit = function(
                                                                    n) {
                                                                return String(n)
                                                                        .replace(
                                                                                /(\d)(?=(\d{3})+(?!\d))/g,
                                                                                '$1,');
                                                            };
                                                            var num1 = "<c:out value="${item2.quantity}" />";
                                                            var num2 = "<c:out value="${item.price}" />";
                                                            var result = num1
                                                                    * num2;
                                                            document
                                                                    .write("合計: "
                                                                            + delimit(result)
                                                                            + " 円");
                                                        </script>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <div class="total text-right">
                        &nbsp;&nbsp;&nbsp;合計金額: <span class="totalPrice"><fmt:formatNumber
                                value="${order.totalPrice}" pattern="###,###" /></span> 円 <br>
                        <script>
            var tax = Math.round(${order.totalPrice} * 0.08 / 1.08);
            document.write("(うち消費税: " + delimit(tax) + " 円)");
            </script>
                    </div>

                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form:form modelAttribute="orderForm"
                        action="${pageContext.request.contextPath}/order/complete">
                        <!-- table -->
                        <div class="title">&nbsp;&nbsp;お届け先情報</div>
                        <br>
                        <div class="row">
                            <div
                                class="table-responsive col-lg-offset-3 col-lg-6 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">

                                <table class="table table-striped">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <div class="text-center">お名前:</div>
                                            </td>
                                            <td><form:errors path="deliveryName"
                                                    cssStyle="color:red" element="div" /> <input type="text"
                                                name="deliveryName"></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="text-center">メールアドレス:</div>
                                            </td>
                                            <td><form:errors path="deliveryEmail"
                                                    cssStyle="color:red" element="div" /> <input type="text"
                                                name="deliveryEmail"></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="text-center">住所:</div>
                                            </td>
                                            <td><form:errors path="deliveryAddress"
                                                    cssStyle="color:red" element="div" /> <input type="text"
                                                name="deliveryAddress"></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="text-center">電話番号:</div>
                                            </td>
                                            <td><form:errors path="destinationTel"
                                                    cssStyle="color:red" element="div" /> <input type="text"
                                                name="deliveryTelephone"></td>
                                        </tr>
                                        <tr>
                                            <td align="center" colspan="2"><br>
                                                <div class="text-center">ご希望の配達日時を入力してください</div></td>

                                        </tr>
                                        <tr>

                                            <td align="center" colspan="2">
                                                <div class="form-group">
                                                    <div class="row">
                                                        <div class="col-sm-5">
                                                            <input type="date" name="deliveryTime" id="name"
                                                                min="2017-11-30" class="form-control input-sm" required />
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <label class="radio-inline"> <input type="radio"
                                                                name="deliverySpecifiedTime" value="10"
                                                                checked="checked"> 10時
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
                                                            </label><br>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!-- table -->
                        <div class="text-right">
                            <input type="submit" value="この内容で注文する" class="submit-btn">
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>