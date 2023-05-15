<script setup>
import Header from '../../Header.vue'
import { reactive } from 'vue';
import { useRouter, useRoute } from 'vue-router';

let router = useRouter();
let route = useRoute();


// --- Variables ---------------------------------------
let state = reactive({
    id: null,
    memberId: 2, // 이후에 로그인한 회원의 정보 가져와야함
    title: "",
    locationType: 0,
    locationInfo: "",
    period: "",
    teamSize: 0,
    thumbnail: "",
    regDate: null,
    deleteDate: null,
    hit: 0

});

// --- Life Cycles -------------------------------------

// --- Event Handlers ----------------------------------
async function registerHandler() {
    const url = new URL("http://localhost:8080/member/community-post/register");

    // state 객체를 복사하여 새로운 객체 생성
    const requestData = JSON.parse(JSON.stringify(state));

    let response = await fetch(url, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        body: JSON.stringify(requestData) 
    })

    let result = await response.text();


    if (result == "true") {
        alert("Post created successfully.");
    } else {
        alert("Failed to create post.");
    }

}

</script>
<template>
    <Header></Header>
    <div class="container">
        <div class="border-blue"></div>
        <section class="team-c-title margin-top-2">
            <h1 class="team-title margin-top-7">팀 개설하기 안내</h1>
            <img class="team-img" src="/src/assets/images/diversity.png" alt="">
            <p class="text margin-top-5">새로운 주제로 팀 프로젝트를 진행하고 싶으신가요 ? <br>
                <br>

                <strong>프로젝트 목표!</strong>
                <br>
                이 목표는 당신의 팀이 어떤 프로젝트를 하고 싶은지,<br>
                그리고 그 목표를 달성하기 위해 필요한 사람들을 모으는 것입니다.<br>
                목표를 설정하면 팀을 개설하고 활동을 계획하는 데 도움이 됩니다.<br>
                아래 양식에 맞게 입력을 해주시면 새로운 팀 프로젝트가 진행됩니다!<br>
                자세한 팀원 모집은 알림과 채팅 기능을 이용해보세요!
                <img class="noti" src="/src/assets/images/notification.png" alt="">
            </p>
        </section>
        <form class="team-c-form" action="" method="post">
            <main class="team-c-main margin-top-1">
                <div class="first-img-box">
                    <div class="img-box">
                        <input type="file" class="d-none">
                        <img class="hover" src="/src/assets/images/img.png" alt="">
                        <span>썸네일 대표 이미지를 추가해주세요.</span>
                        <img class="hover d-none" src="/src/assets/images/fff-img.png" alt="">
                    </div>
                </div>
                <div class="text-box margin-top-3">
                    <h2>제목</h2>
                    <input class="team-c-input" type="text" placeholder="내 답변" v-model="state.title">
                </div>
                <div class="text-box margin-top-3">
                    <h2>닉네임</h2>
                    <!-- 고정값 -->
                    <input class="team-c-input" type="text" placeholder="해당 회원의 닉네임" readonly>
                </div>
                <div class="button-box margin-top-3">
                    <h2>온·오프라인</h2>
                    <div class="btn-box margin-top-5">
                        <button class="btn btn-1 margin-right-10" @click.prevent="state.locationType = 0">ON-LINE</button>
                        <button class="btn btn-1" @click.prevent="state.locationType = 1">OFF-LINE</button>
                    </div>
                </div>
                <div class="text-box margin-top-3">
                    <h2>장소</h2>
                    <input class="team-c-input" type="text" placeholder="내 답변" v-model="state.locationInfo">
                </div>
                <div class="text-box margin-top-3">
                    <h2>기간</h2>
                    <input class="team-c-input" type="text" placeholder="내 답변" v-model="state.period">
                </div>
                <div class="text-box margin-top-3">
                    <h2>모집인원</h2>
                    <input class="team-c-input" type="text" placeholder="내 답변" v-model="state.teamSize">
                </div>
                <div class="submit-box margin-top-2">
                    <button class="submit" type="submit" @click.prevent="registerHandler">등록</button>
                    <button class="reset">양식지우기</button>
                </div>
            </main>
        </form>
    </div>
</template>
<style scoped>
@import url("/src/assets/css/component/team-register.css");
</style>