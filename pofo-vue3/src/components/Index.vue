<script setup>
import { reactive, onMounted, ref, watch, defineComponent } from 'vue';
import Header from './Header.vue';
import 'vue3-carousel/dist/carousel.css'
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'

    // --- Variables ---------------------------------------
    let state = reactive({
        list: [], // 포트폴리오 리스트를 담을 변수
        sort: 'latest', // 정렬방식을 나타내는 변수, 최신순이 디폴트
        collaboration: null, // 협업여부를 나타내는 변수, 전체가 디폴트
        language: null, // 프로그래밍언어를 나타내는 변수, 전체가 디폴트
        query: null, // 검색어를 담을 변수, 

        weeklyPopularList: [], // 이번주 인기 포트폴리오 리스트를 담을 변수

        nextOffset: 0, // 다음 리스트를 가져오기 위한 오프셋 값
        limit: 15, // 한 번에 가져올 리스트의 개수
    });

    // --- Life Cycles -------------------------------------
    onMounted(fetchPortfolios);
    watch(() => [state.sort, state.collaboration, state.language, state.query], fetchPortfolios); // 변수가 변경될 때마다 함수 실행

    // --- Event Handlers ----------------------------------
    function queryUpdateHandler(query) {
        state.query = query;
    }

    async function fetchPortfolios() {
        const url = new URL('http://localhost:8080/index');
        url.searchParams.set('sort', state.sort); // URL의 query string을 처리하는 함수
        if (state.collaboration !== null) { // 협업여부를 선택한 경우 쿼리 파라미터를 추가함
            url.searchParams.set('collaboration', state.collaboration);
        }
        if (state.language !== null) { // 프로그래밍언어를 선택한 경우 쿼리 파라미터를 추가함
            url.searchParams.set('language', state.language);
        }
        if (state.query !== null) { // 검색한 경우 쿼리 파라미터를 추가함
            url.searchParams.set('query', state.query);
        }

        let response = await fetch(url);
        let json = await response.json();
        state.list = json.list;
        state.weeklyPopularList = json.weeklyPopularList;
    }

</script>

<template>
    <Header @query-updated="queryUpdateHandler" />
    <main>
        <!-- 이번주 인기 TOP 10 포트폴리오 리스트 -->
        <section v-if="state.query == null" class="slider-container">
            <h1 class="d-none">이번주 인기 TOP 10</h1>
            <div class="slider">
                <Carousel :itemsToShow="3.125" :wrapAround="true" :transition="500">
                    <Slide v-for="(portfolio, index) in state.weeklyPopularList" :key="index">
                        <div class="carousel__item">
                            <img :src="'image/' + portfolio.thumbnail" alt="포트폴리오 섬네일 이미지">
                            <div class="overlay">
                                <h2>{{ portfolio.title }}</h2>
                                <p>{{ portfolio.nickname }}</p>
                            </div>
                        </div>
                    </Slide>

                    <template #addons>
                        <navigation />
                        <pagination />
                    </template>
                </Carousel>
            </div>
        </section>

        <!-- 포트폴리오 리스트 -->
        <div class="portfolio-container">

            <!-- 필터링 -->
            <section class="category-section">
                <h1 v-if="state.query == null">POFO의 인기 개발언어를 선택해 보세요.</h1>
                <h1 v-if="state.query != null">'{{ state.query }}' 에 대한 검색 결과입니다.</h1>
                <!-- 프로그래밍 언어별 -->
                <ul v-if="state.query == null" class="category-list">
                    <li class="category-item entire" :class="{ active: state.language === null }"
                        @click="state.language = null">
                        <button>
                            <span>전체</span>
                        </button>
                    </li>
                    <li class="category-item java" :class="{ active: state.language === 1 }" @click="state.language = 1">
                        <button>
                            <span>Java</span>
                        </button>
                    </li>
                    <li class="category-item javascript" :class="{ active: state.language === 2 }"
                        @click="state.language = 2">
                        <button>
                            <span>JavaScript</span>
                        </button>
                    </li>
                    <li class="category-item python" :class="{ active: state.language === 3 }" @click="state.language = 3">
                        <button>
                            <span>Python</span>
                        </button>
                    </li>
                    <li class="category-item c" :class="{ active: state.language === 4 }" @click="state.language = 4">
                        <button>
                            <span>C</span>
                        </button>
                    </li>
                    <li class="category-item c-sharp" :class="{ active: state.language === 5 }" @click="state.language = 5">
                        <button>
                            <span>C#</span>
                        </button>
                    </li>
                    <li class="category-item visual-basic" :class="{ active: state.language === 6 }"
                        @click="state.language = 6">
                        <button>
                            <span>Visual Basic</span>
                        </button>
                    </li>
                </ul>

                <div class="options-wrap">
                    <!-- 정렬방식 -->
                    <div class="sorting-options">
                        <span>정렬방식:</span>
                        <button class="sort-button" :class="{ active: state.sort === 'latest' }"
                            @click="state.sort = 'latest'">최신순</button>
                        <button class="sort-button" :class="{ active: state.sort === 'likes' }"
                            @click="state.sort = 'likes'">좋아요순</button>
                    </div>
                    <!-- 협업여부 -->
                    <div class="collaboration-options">
                        <span>협업여부:</span>
                        <button class="collaboration-button" :class="{ active: state.collaboration === null }"
                            @click="state.collaboration = null">전체</button>
                        <button class="collaboration-button" :class="{ active: state.collaboration === 0 }"
                            @click="state.collaboration = 0">팀</button>
                        <button class="collaboration-button" :class="{ active: state.collaboration === 1 }"
                            @click="state.collaboration = 1">개인</button>
                    </div>
                </div>
            </section>

            <!-- 리스트 섹션 -->
            <section class="list-section" ref="listSection">
                <h1 class="d-none">포트폴리오 리스트 섹션</h1>
                <ul>
                    <li v-for="(portfolio, index) in state.list" :key="index">
                        <div class="thumbnail" :data-title="portfolio.title">
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

<style scoped>
@import url("/css/index.css");
</style>