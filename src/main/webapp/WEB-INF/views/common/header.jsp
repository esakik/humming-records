<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<header class="row">
    <c:choose>
        <c:when test="${member.name == null}">
            こんにちは、ゲスト&nbsp;様 | Humming&nbsp;へようこそ!
        </c:when>
        <c:otherwise>
            こんにちは、<span class="name"><c:out value="${member.name}" /></span>&nbsp;様
        </c:otherwise>
    </c:choose>
</header>
<nav class="navbar navbar-default row">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarTarget">
                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"> <img src="/img/humming_logo.png"></a>
        </div>
        <div class="collapse navbar-collapse" id="navbarTarget">
            <form action="/search" class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" name="singerOrSong" class="form-control" placeholder="検索キーワード" />
                </div>
                <button type="submit" class="btn btn-default">
                    <img src="/img/search.png">
                </button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${member.name != null}">
                    <li><a href="/member/logout"><span class="material-text">ログアウト</span></a></li>
                </c:if>
                <c:if test="${member.name == null}">
                    <li><a href="/member/login/input"><span class="material-text">ログイン</span></a></li>
                </c:if>
                <c:if test="${member.name != null}">
                    <li><a href="/member/mypage"><span class="material-text">マイページ</span></a></li>
                </c:if>
                <c:if test="${member.name == null}">
                    <li><a href="/member/regist/input"><span class="material-text">会員登録</span></a></li>
                </c:if>
                <li><a href="/order/cart"><span class="material-text">買い物かご</span></a></li>
                <li><a href="/contact/input"><span class="material-text">問い合わせ</span></a></li>
            </ul>
        </div>
    </div>
</nav>