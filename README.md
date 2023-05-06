# team-project-pofo
- #### 5/5   
DB에서 가져온 포트폴리오 데이터를 인덱스 화면에 리스트 형태로 표시했다.   
새로고침한 경우에만 새로운 데이터가 반영되도록 reactive 기능을 이용하지 않았다.
```javascript
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
```
포트폴리오 관리시스템에서 중요한 것 중 하나는 3가지 종류의 필터링을 해야 한다는 것이다.   
큰 그림을 그린 상태인데, 잘 구현됐으면 좋겠다.   
   
- #### 5/6   
벌써 토요일이다.
