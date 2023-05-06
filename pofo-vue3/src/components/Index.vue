<script setup>
import Header from './inc/Header.vue';

import { reactive, onMounted, ref, watch } from 'vue';

    // --- Variables ---------------------------------------
    let state = reactive({
        portfolioViewList: [], // 포트폴리오 리스트를 담을 변수
        sort: '최신순', // 정렬방식을 나타내는 변수, 최신순이 디폴트
        collaboration: null, // 협업여부를 나타내는 변수, 전체가 디폴트

        nextOffset: 0, // 다음 리스트를 가져오기 위한 오프셋 값
        limit: 15, // 한 번에 가져올 리스트의 개수
    });
    
  
    // --- Life Cycles -------------------------------------
    onMounted(fetchPortfolios);
    watch(() => [state.sort, state.collaboration], fetchPortfolios); // 변수가 변경될 때마다 함수 실행
    
    // --- Event Handlers ----------------------------------
    async function fetchPortfolios() {
        const url = new URL('http://localhost:8080/index');
        url.searchParams.set('sort', state.sort); // URL의 query string을 처리하는 함수
        if (state.collaboration !== null) { // 협업여부를 선택한 경우 쿼리 파라미터를 추가함
            url.searchParams.set('collaboration', state.collaboration);
        }
        let response = await fetch(url);
        let json = await response.json();
        state.portfolioViewList = json.result;
    }
</script>

<template>
    <Header />
    <main>
        <!-- 이번주 인기 TOP 10 포트폴리오 리스트 -->
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

            <!-- 필터링 -->
            <section class="category-section">
                <h1>POFO의 인기 개발언어를 선택해 보세요.</h1>
                <!-- 프로그래밍 언어별 -->
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
                    <!-- 정렬방식 -->
                    <div class="sorting-options">
                        <span>정렬방식:</span>
                        <button class="sort-button" 
                                :class="{active: state.sort === '최신순'}" 
                                @click="state.sort = '최신순'">최신순</button>
                        <button class="sort-button"
                                :class="{active: state.sort === '좋아요순'}" 
                                @click="state.sort = '좋아요순'">좋아요순</button>
                    </div>
                    <!-- 협업여부 -->
                    <div class="collaboration-options">
                        <span>협업여부:</span>
                        <button class="collaboration-button"
                                :class="{active: state.collaboration === null}" 
                                @click="state.collaboration = null">전체</button>
                        <button class="collaboration-button"
                                :class="{active: state.collaboration === 0}" 
                                @click="state.collaboration = 0">팀</button>
                        <button class="collaboration-button"
                                :class="{active: state.collaboration === 1}" 
                                @click="state.collaboration = 1">개인</button>
                    </div>
                </div>
            </section>

            <!-- 리스트 섹션 -->
            <section class="list-section">
                <h1 class="d-none">포트폴리오 리스트 섹션</h1>
                <ul>
                    <li v-for="(portfolio, index) in state.portfolioViewList" :key="index">
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