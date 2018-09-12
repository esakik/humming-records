<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Humming | CDを選ぶ・楽しむ</title>
<link rel="stylesheet" href="../../../css/style.css">
<link rel="stylesheet" href="../../../css/header.css">
<link rel="stylesheet" href="../../../css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body style="background-color: whitesmoke;">
    <div class="container-fluid">
        <%@ include file="../common/header.jsp"%>

        <div id="content">
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                <div class="main panel panel-default">
                    <div class="panel-body">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#ranking">人気アイテム</a></li>
                            <li><a data-toggle="tab" href="#new">新着アイテム</a></li>
                            <li><a data-toggle="tab" href="#reserve">予約アイテム</a></li>
                        </ul>
                        <div class="tab-content ribbon_box row">
                            <div id="ranking" class="tab-pane fade in active">
                                <h3 class="ribbon8">人気アイテム</h3>
                                <c:out value="${itemCount}"/>
                                <div class="row">
                                    <div id="item">
                                        <c:forEach var="item" items="${itemList}">
                                            <div class="single-item col-lg-3 col-md-3 col-sm-4 col-xs-6">
                                                <img src="data:jpg;base64, ${item.image}" class="img-thumbnail">
                                                <div class='singer'>
                                                    <a href="/detail?itemId=${item.id}" style="text-decoration: none"><c:out value="${item.singer}"/></a>
                                                </div>
                                                <div class="song">
                                                    <c:out value="${item.song}"/>
                                                </div>
                                                <div class="price">
                                                    <c:out value="${item.price}"/> 円
                                                </div>
                                            </div>
                                    </c:forEach>
                                    </div>
                                </div>

                                <div id="paging" class="row">
                                    <c:if test="${activePage != 1}">
                                        <a href="/page?number=${activePage - 1}" class="paging_btn">前の12件</a>
                                    </c:if>
                                    <c:forEach begin="1" end="${pagingButtonCount}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${activePage == status.index}" >
                                                <a href="/page?number=${status.index}" class="active-page paging_btn"><c:out value="${status.index}"/></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="/page?number=${status.index}" class="paging_btn"><c:out value="${status.index}"/></a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${activePage != pagingButtonCount}">
                                        <a href="/page?number=${activePage + 1}" class="paging_btn">次の12件</a>
                                    </c:if>
                                </div>
                            </div>
                            <div id="new" class="tab-pane fade">
                                <h3 class="ribbon8">新着アイテム</h3>
                                <div id="newList" class="row" style="clear: both;">
                                    <div style="margin-top: 30px;"></div>
                                    新着アイテムはありません。
                                </div>
                                <div id="paging-new" class="row"></div>
                                <div style="margin-bottom: 30px;"></div>
                            </div>
                            <div id="reserve" class="tab-pane fade">
                                <h3 class="ribbon8">予約アイテム</h3>
                                <div id="reserveList" class="row" style="clear: both;">
                                    <div style="margin-top: 30px;"></div>
                                    予約アイテムはありません。
                                </div>
                                <div id="paging-reserve" class="row"></div>
                                <div style="margin-bottom: 30px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--
                <div class="main panel panel-default">
                    <div class="panel-body">
                        <div class="tab-content ribbon_box row">
                            <div class="tab-pane fade in active">
                                <h3 class="ribbon8">最近チェックした商品</h3>
                                <div id="attentionList" class="row" style="clear: both;"></div>
                                <div id="paging-attention" class="row"></div>
                                <div style="margin-bottom: 30px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
                 -->
            </div>
            <div  class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                <div class="row panel panel-default">
                    <div class="panel-body">
                        <img src="../img/delivery.jpg" width="80%">
                    </div>
                </div>

                <div class="row panel panel-info">
                    <div class="panel-heading"><span style="font-weight: bold;">カテゴリ一覧</span></div>
                    <div class="list-group category">
                        <a href="#" class="list-group-item">アーティスト</a>
                        <a href="#" class="list-group-item">ランキング</a>
                        <a href="#" class="list-group-item">お気に入り</a>
                        <a href="#" class="list-group-item">ジャンル</a>
                        <a href="#" class="list-group-item">値下げアイテム</a>
                    </div>
                </div>

                <div class="row panel panel-info">
                    <div class="panel-heading">
                        <a href="#panelCollapse" data-toggle="collapse" style="font-weight: bold;text-decoration: none;">
                            <span style="margin-right: -16px;">Hummingとは</span>
                            <span class="material-icons" style="float: right;font-size: 16px;">touch_app</span>
                        </a>
                    </div>
                    <div class="panel-body">
                        <div id="panelCollapse" class="collapse" style="text-align: left;">
                            1. HummingはCDを取り扱うことを仮定としたECサイトのモックです。そのため、実際の商品はございません。<br/>
                            2. 商品をカートに追加して購入するまでの一覧の流れを無料でご利用可能です。決済処理等はございません。<br/>
                            3. 会員登録しますと、マイページからご注文履歴を閲覧することができます。退会手続きも簡単にできますので、ご気軽にお試しください。
                        </div>
                    </div>
                </div>

                <div class="row panel panel-danger">
                    <div class="panel-heading"><span style="font-weight: bold;">お知らせ</span></div>
                    <div class="panel-body">
                    最新のお知らせはありません。
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

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/scrolltop.js"></script>
</body>
</html>