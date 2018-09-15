<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="container-top" class="row">
    <c:if test="${member.name == null}">
                    こんにちは、<span class="name">ゲスト</span>&nbsp;様 | Humming&nbsp;へようこそ!
            </c:if>
    <c:if test="${member.name != null}">
                    こんにちは、
                    <span class="name"> <c:out
                value="${member.name}" />
        </span>&nbsp;様
            </c:if>
</div>

<nav class="row navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarEexample6">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">
                <img src="/img/humming_logo.png">
            </a>
        </div>

        <div class="collapse navbar-collapse" id="navbarEexample6">
            <form action="/search" class="navbar-form navbar-left input-group" role="search">
                <div class="form-group">
                    <input type="text" name="singerOrSong" class="form-control" placeholder="検索キーワード" style="border-radius: 0; width: 300px;">
                <span class="input-group-btn">
                <button type="submit" class="btn btn-default" style="border-radius: 0"><img src="/img/search.png" width="20px"></button>
                </span>
                </div>
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
                <li><a href="/member/contact"><span class="material-text">問い合わせ</span></a></li>
            </ul>
        </div>
    </div>
</nav>