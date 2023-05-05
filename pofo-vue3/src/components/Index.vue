<script setup>
import Header from './inc/Header.vue';

import { reactive, onMounted, ref } from 'vue';

    // --- Variables ---------------------------------------
    let portfolios = []; // 포트폴리오 리스트를 담을 변수

  
    // --- Life Cycles ---------------------------------------
    onMounted(() => {
        fetchPortfolios(); // 인덱스 페이지 접속 시 포트폴리오 리스트 가져오기
    })
    
    // --- Event Handlers ---------------------------------------
    async function fetchPortfolios() {
        let response = await fetch("http://localhost:8080/index");
        let json = await response.json();
        portfolios = json.result;
        console.log(portfolios);
    }
</script>

<template>
    <Header />
    <main>
        <!-- 이번주 인기 TOP 10 -->
        <section class="slider-container">
            <h1 class="d-none">이번주 인기 TOP 10</h1>
            <div class="slider">
                <a href="/your-post-url-10"><img src="image/A3rzk7ZMMuKmrv9y.jpg" alt="포트폴리오 섬네일 이미지"></a>
                <a href="/your-post-url-1"><img src="image/92sBdzoNV6uRcMTr.11.gif" alt="포트폴리오 섬네일 이미지"></a>
                <a href="/your-post-url-2"><img src="image/F9C9aBXD9WcJL9eU.png" alt="포트폴리오 섬네일 이미지"></a>
                <a href="/your-post-url-3"><img src="image/BctLFrYLdnFPix7w.jpg" alt="포트폴리오 섬네일 이미지"></a>
                <a href="/your-post-url-4"><img src="image/BctLFrYLdnFPix7w.jpg" alt="포트폴리오 섬네일 이미지"></a>
                <a href="/your-post-url-5"><img src="image/BctLFrYLdnFPix7w.jpg" alt="포트폴리오 섬네일 이미지"></a>
                <a href="/your-post-url-6"><img src="image/BctLFrYLdnFPix7w.jpg" alt="포트폴리오 섬네일 이미지"></a>
                <a href="/your-post-url-7"><img src="image/BctLFrYLdnFPix7w.jpg" alt="포트폴리오 섬네일 이미지"></a>
                <a href="/your-post-url-8"><img src="image/BctLFrYLdnFPix7w.jpg" alt="포트폴리오 섬네일 이미지"></a>
                <a href="/your-post-url-9"><img src="image/BctLFrYLdnFPix7w.jpg" alt="포트폴리오 섬네일 이미지"></a>
            </div>
            <div class="slider-controls">
                <button class="prev-button"></button>
                <button class="next-button"></button>
            </div>
            <div class="indicator-container">
                <ul class="indicator-list">
                    <li class="indicator active"></li>
                    <li class="indicator"></li>
                    <li class="indicator"></li>
                    <li class="indicator"></li>
                    <li class="indicator"></li>
                    <li class="indicator"></li>
                    <li class="indicator"></li>
                    <li class="indicator"></li>
                    <li class="indicator"></li>
                    <li class="indicator"></li>
                </ul>
            </div>
        </section>

        <!-- 포트폴리오 리스트 -->
        <div class="portfolio-container">

            <!-- 카테고리 & 정렬 -->
            <section class="category-section">
                <h1>POFO의 인기 개발언어를 선택해 보세요.</h1>
                <!-- <div class="category-slider"> -->
                <ul class="category-list">
                    <li class="category-item entire active">
                        <a href="#전체">
                            <span>전체</span>
                        </a>
                    </li>
                    <li class="category-item java">
                        <a href="#전체">
                            <span>Java</span>
                        </a>
                    </li>
                    <li class="category-item javascript">
                        <a href="#전체">
                            <span>JavaScript</span>
                        </a>
                    </li>
                    <li class="category-item python">
                        <a href="#전체">
                            <span>Python</span>
                        </a>
                    </li>
                    <li class="category-item c">
                        <a href="#전체">
                            <span>C</span>
                        </a>
                    </li>
                    <li class="category-item c-sharp">
                        <a href="#전체">
                            <span>C#</span>
                        </a>
                    </li>
                    <li class="category-item visual-basic">
                        <a href="#전체">
                            <span>Visual Basic</span>
                        </a>
                    </li>
                </ul>
                
                <div class="options-wrap">

                    <div class="sorting-options">
                        <span>정렬방식:</span>
                        <button class="sort-button active">최신순</button>
                        <button class="sort-button">좋아요순</button>
                    </div>
    
                    <div class="collaboration-options">
                        <span>협업여부:</span>
                        <button class="collaboration-button active">전체</button>
                        <button class="collaboration-button">팀</button>
                        <button class="collaboration-button">개인</button>
                    </div>
                </div>
            </section>

            <!-- 리스트 섹션 -->
            <section class="list-section">
                <h1 class="d-none">포트폴리오 리스트 섹션</h1>
                <ul>
                    <li v-for="(portfolio, index) in portfolios" :key="index">
                        <div class="thumbnail">
                            <img :src="'image/' + portfolio.thumbnail" alt="포트폴리오 섬네일 이미지">
                        </div>
                        <div class="information">
                            <div class="portfolio-info-profile">
                                <!-- <img :src="'image/' + portfolio.memberImage" alt="프로필 이미지"> -->
                                <img src="image/BctLFrYLdnFPix7w.jpg" alt="프로필 이미지">
                                <span class="nickname">{{ portfolio.nickname }}</span>
                            </div>
                            <div class="portfolio-info-counts">
                                <img src="image/eye.png" alt="조회수 이미지">
                                <span class="hit">{{ portfolio.hit }}</span>
                                <img src="image/heart.png" alt="하트 이미지">
                                <span class="like">{{ portfolio.likeCount }}</span>
                            </div>
                        </div>
                    </li>
                </ul>
            </section>
        </div>
    </main>
</template>