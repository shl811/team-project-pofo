<script setup>

</script>
<template>
    <!-- <header class="com-header">
        <ul class="menu-bar">
            <li><a href=""><img class="header-logo" src="/imgs/Pofo.svg" alt="logo"></a></li>
            <li class="pro"><a href="">프로젝트</a></li>
            <li class="community"><a href="">커뮤니티</a></li>
        </ul>
        <div class="search-box"><img class="search-img" src="/imgs/free-icon-search-482631-3.svg" alt="돋보기"><input class="header-search" type="text" placeholder="검색어를 입력해보세요"></div>
        <ul class="m-menu user-bar">
            <li class="login-li" ><a class="login" href="">로그인</a></li>
            <li class="sign-up-li"><a href=""><button class="btn btn-3 sign-up">회원가입</button></a></li>
            <li><img class="hamburger-bar" src="/imgs/menu.png" alt=""></li>
        </ul>
    </header> -->
    <!-- 맴버 -->
    <header class="com-header">
        <ul class="menu-bar">
            <li><router-link to="/"><img class="header-logo" src="/src/assets/images/Pofo.svg" alt="logo"></router-link>
            </li>
            <li class="pro">
                <router-link to="/index">프로젝트</router-link>
            </li>
            <li class="community"><router-link to="/community">커뮤니티</router-link></li>
        </ul>
        <div class="search-box"><img class="search-img" src="/src/assets/images/free-icon-search-482631-3.svg" alt="돋보기">
            <input class="header-search" type="text" placeholder="검색어를 입력해보세요" @keydown.enter="(event) => { $emit('query-updated', event.target.value) }">
        </div>
        <ul class="m-menu member-bar">
            <li><router-link to="/pofo/reg"><button class="btn btn-3 prj-register">프로젝트 등록</button></router-link></li>
            <li><img class="dm" src="/src/assets/images/email.png" alt="DM"></li>
            <li><img class="bell" src="/src/assets/images/bell.png" alt="알림"></li>
            <li><img class="header-profile" src="/src/assets/images/proflie.svg" alt="마이프로필"></li>
            <li><img class="hamburger-bar" src="/src/assets/images/menu.png" alt=""></li>
        </ul>
    </header>
</template>
<style>
/* @import url("/src/header.css"); */
@import url("/css/header.css");
</style>