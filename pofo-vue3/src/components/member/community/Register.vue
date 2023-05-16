<script setup>
import Header from '../../Header.vue'
import { reactive, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router';

let router = useRouter();
let route = useRoute();

// --- Variables ---------------------------------------
let community = reactive({
    memberId: 2, // 이후에 로그인한 회원의 정보 가져와야함
    title: "",
    onlineType: true,
    locationInfo: "",
    period: "",
    teamSize: 0,
    thumbnail: "",
});

let fileInputRef = ref(null);
let imgRef = ref(null);

// --- Life Cycles -------------------------------------

// --- Event Handlers ----------------------------------
async function registerHandler() {
    const url = new URL("http://localhost:8080/member/community-post/register");
    let form = document.querySelector("#form");
    const formData = new FormData(form);
    formData.append("onlineType",community.onlineType); 

    // community 객체를 복사하여 새로운 객체 생성
    const requestData = JSON.parse(JSON.stringify(community));

    let response = await fetch(url, {
        method: "POST",
        headers: {
            "Accept": "application/json",
        },
        // body: JSON.stringify(requestData) 
        body: formData
    })

    let result = await response.text();


    if (result == "true") {
        alert("Post created successfully.");
    } else {
        alert("Failed to create post.");
    }
}

function imageBoxClickHandler(){
    console.log("clicked");
    let fileInput = fileInputRef.value;
    
    // fileInput.click(); // 이 방법 보다는..?
    const event = new MouseEvent("click", {
    view: window,
        bubbles: true,
        cancelable: true,
    });

    fileInput.dispatchEvent(event);
}

function fileInputHandler(e) {
    console.log(e);

    const file = fileInputRef.value.files[0];

    if (file) {
        const reader = new FileReader();

        reader.onload = function (event) {
            imgRef.value.src = event.target.result;
        };

        reader.readAsDataURL(file);

    } else {
        console.log('파일이 선택되지 않았습니다.');
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
        <form class="team-c-form" action="" method="post" id="form">
            <main class="team-c-main margin-top-1">
                <div class="first-img-box">
                    <div class="img-box" @click="imageBoxClickHandler" >
                        <span v-if="!imgRef">썸네일 대표 이미지를 추가해주세요.</span>
                        <img ref="imgRef"  src="#" alt="선택한 이미지" />
                    </div>
                    <input type="file" class="d-none" ref="fileInputRef" @input="fileInputHandler" name="image">
                </div>
                <div class="text-box margin-top-3">
                    <h2>제목</h2>
                    <input class="team-c-input" type="text" placeholder="내 답변" v-model="community.title" name="title">
                </div>
                <div class="text-box margin-top-3">
                    <h2>닉네임</h2>
                    <!-- 고정값 -->
                    <input class="team-c-input" type="text" placeholder="해당 회원의 닉네임" readonly>
                </div>
                <div class="button-box margin-top-3">
                    <h2>온·오프라인</h2>
                    <div class="btn-box margin-top-5">
                        <!-- <button class="btn btn-1 margin-right-10" :class={focus: community.locationType} @click.prevent="community.locationType = 0">ON-LINE</button>
                        <button class="btn btn-1" :class={focus:!community.locationType} @click.prevent="community.locationType = 1">OFF-LINE</button> -->
                        <!-- Ekf -->
                        <button class="btn btn-1 margin-right-10" :class="{'focus': community.onlineType}" @click.prevent="community.onlineType = true">ON-LINE</button>
                        <button class="btn btn-1" :class="{'focus': !community.onlineType}" @click.prevent="community.onlineType = false">OFF-LINE</button>
                    </div>
                </div>
                <div class="text-box margin-top-3">
                    <h2>장소</h2>
                    <input class="team-c-input" type="text" placeholder="내 답변" v-model="community.locationInfo" name="locationInfo">
                </div>
                <div class="text-box margin-top-3">
                    <h2>기간</h2>
                    <input class="team-c-input" type="text" placeholder="내 답변" v-model="community.period" name="period">
                </div>
                <div class="text-box margin-top-3">
                    <h2>모집인원</h2>
                    <input class="team-c-input" type="text" placeholder="내 답변" v-model="community.teamSize" name="teamSize">
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