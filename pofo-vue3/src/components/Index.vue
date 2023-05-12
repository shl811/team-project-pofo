<script>
import { reactive, onMounted, ref, watch, defineComponent } from 'vue';
import Header from './Header.vue';
import 'vue3-carousel/dist/carousel.css'
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'

export default defineComponent({
    components: {
        Header,
        Carousel,
        Slide,
        Pagination,
        Navigation
    },
    setup() {
        // --- Variables ---------------------------------------
        let state = reactive({
            list: [], // 포트폴리오 리스트를 담을 변수
            sort: 'latest', // 정렬방식을 나타내는 변수, 최신순이 디폴트
            collaboration: null, // 협업여부를 나타내는 변수, 전체가 디폴트
            language: null, // 프로그래밍언어를 나타내는 변수, 전체가 디폴트
            query : '', // 검색어를 담을 변수, 

            weeklyPopularList: [], // 이번주 인기 포트폴리오 리스트를 담을 변수

            nextOffset: 0, // 다음 리스트를 가져오기 위한 오프셋 값
            limit: 15, // 한 번에 가져올 리스트의 개수
        });


        // --- Life Cycles -------------------------------------
        onMounted(fetchPortfolios);
        watch(() => [state.sort, state.collaboration, state.language, state.query], fetchPortfolios); // 변수가 변경될 때마다 함수 실행

        // --- Event Handlers ----------------------------------
        function queryUpdateHandler(query){
            state.query = query;
        }

        async function fetchPortfolios() {
            console.log(state.query);

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

        return { 
            state,
            queryUpdateHandler
        };
    }
})

</script>

<template>
    <Header @query-updated="queryUpdateHandler" />
    <main>
        <!-- 이번주 인기 TOP 10 포트폴리오 리스트 -->
        <section class="slider-container">
            <h1 class="d-none">이번주 인기 TOP 10</h1>
            <div class="slider">
                <Carousel :itemsToShow="3" :wrapAround="true" :transition="500">
                    <Slide v-for="(portfolio, index) in state.weeklyPopularList" :key="index">
                        <img class="carousel__item" :src="'image/' + portfolio.thumbnail" alt="포트폴리오 섬네일 이미지">
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
                <!-- 검색된 경우 멘트 수정돼야함. -->
                <h1>POFO의 인기 개발언어를 선택해 보세요.</h1>
                <!-- 프로그래밍 언어별 -->
                <ul class="category-list">
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
            <section class="list-section">
                <h1 class="d-none">포트폴리오 리스트 섹션</h1>
                <ul>
                    <li v-for="(portfolio, index) in state.list" :key="index">
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

<style scoped>
/* 슬라이더 컨테이너 */
.slider-container {
    width: 100%;
    /* overflow: hidden;
    position: relative; */

    background-color: #F6F8F8;
    /* 슬라이더의 배경색을 #F6F8F8로 설정 */
}

/* 슬라이더 */
.slider {
    margin-top: 24px;
}

.slider img {
    /* width: 100%; 
    height: 100%;
    max-height: 100%; */

    object-fit: cover;
    /* border-radius: 10px; */

}

/* 슬라이더의 각 슬라이드 아이템 */
.carousel__item {
    object-fit: cover;
    /* max-height: 100%; */
    /* padding: 0 1.66%; */
    /* min-height: 200px; */
    /* width: 100%; */
    /* border-radius: 8px; */
    width: 570px;
    height: 320px;
    border-radius: 10px;
    /* display: flex; */
    justify-content: center;
    align-items: center;
  }
.carousel__slide {
    padding: 5px;
}

.carousel__viewport {
    perspective: 1920px;
}

.carousel__track {
    transform-style: preserve-3d;
}

.carousel__slide--sliding {
    transition: 0.5s;
}

.carousel__slide {
    opacity: 0.9;
    transform: rotateY(-20deg) scale(0.9);
}

.carousel__slide--active~.carousel__slide {
    transform: rotateY(20deg) scale(0.9);
}

.carousel__slide--prev {
    opacity: 1;
    transform: rotateY(-10deg) scale(0.95);
}

.carousel__slide--next {
    opacity: 1;
    transform: rotateY(10deg) scale(0.95);
}

.carousel__slide--active {
    opacity: 1;
    transform: rotateY(0) scale(1.1);
}
</style>