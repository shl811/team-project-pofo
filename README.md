# team-project-pofo
- ### 5/5   
DB에서 가져온 포트폴리오 데이터를 인덱스 화면에 리스트 형태로 표시했다.   
~새로고침한 경우에만 새로운 데이터가 반영되도록 reactive 기능을 이용하지 않았다.~   
➡️(5/6) 사용자가 필터조건을 변경할 때마다 fetchPortfolios() 함수가 실행되어야 하기 때문에 reactive 기능을 이용했다.
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
   ***
- ### 5/6   
프로그래밍언어별을 제외한 2가지 필터 구현에 성공했다. (정렬방식, 협업여부)   
먼저 세 개의 테이블을 조인해서 view로 만드는 작업 진행했는데 포트폴리오의 디폴트 리스트를 생각하니 적용할 컬럼들이 추려졌다.   
프로그래밍언어별 필터를 적용하면 또 바꿔야겠지만, 그림을 그려야 구글링도 할 수 있다는 것을 다시 한번 깨달았다.   
(SQL을 공부하고 싶은 욕망이 가득해졌다. 아쉽지만 급한 불인 프로젝트&이력서 마무리를 하고 시작해야지. 평생 해버릴 거다!)
```sql
VIEW `index_portfolio_view` AS
    SELECT 
        `p`.`id` AS `id`,
        `p`.`thumbnail` AS `thumbnail`,
        `p`.`title` AS `title`,
        `p`.`hit` AS `hit`,
        `p`.`collaboration` AS `collaboration`,
        `p`.`reg_date` AS `reg_date`,
        `m`.`nickname` AS `nickname`,
        `m`.`image` AS `member_image`,
        COUNT(`l`.`portfolio_id`) AS `like_count`
    FROM
        ((`portfolio` `p`
        LEFT JOIN `member` `m` ON (`p`.`member_id` = `m`.`id`))
        LEFT JOIN `like` `l` ON (`p`.`id` = `l`.`portfolio_id`))
    GROUP BY `p`.`id`
```
쿼리스트링에 '?collaboration=팀' 이런식으로 보였으면 해서 DB에서는 int지만 서버 코드에서는 String으로 설정했더니
'java.lang.NumberFormatException: For input string: "팀"' 오류가 발생해서 자료형을 통일했다. 
```xml
<select id="findViewAll" resultMap="portfolioViewResultMap">
    select *
    from index_portfolio_view
    where
    <choose>
        <when test="collaboration == 0">
            collaboration = 0
        </when>
        <when test="collaboration == 1">
            collaboration = 1
        </when>
        <otherwise> <!-- 협업여부 디폴트 == 전체 -->
            1 = 1
        </otherwise>
    </choose>
    <choose>
        <when test="sort == '좋아요순'">
            ORDER BY like_count DESC
        </when>
        <otherwise> <!-- 정렬방식 디폴트 == 최신순 -->
            ORDER BY reg_date DESC
        </otherwise>
    </choose>
    <if test="offset != null">
        limit #{size} offset #{offset}
    </if>
</select>
```
처음 생각과 다르게 사용자가 필터를 클릭할 때마다 포트폴리오 리스트르를 새로 불러와야해서 reactive 기능을 이용했다.  
두 가지 조건이 결합된 결과물이 화면에 보일 때 나도 모르게 환호를 질렀다.  
URL의 query string을 처리하는 searchParams() 함수가 신기했는데, set을 이용해서 파라미터를 추가하는 것을 보고 기본기 생각을 안 할 수 없었다.
```javascript
// --- Variables ---------------------------------------
let state = reactive 
({
    portfolioViewList: [], // 포트폴리오 리스트를 담을 변수
    sort: '최신순', // 정렬방식을 나타내는 변수, 최신순이 디폴트
    collaboration: null, // 협업여부를 나타내는 변수, 전체가 디폴트
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
```
사실 리스트 자체는 난이도가 낮다 생각한다. 훨씬 어려운 기능을 구현하거나, 이미 이 기능을 예전에 성공한 학생이 있다.(현재 국비 프로그램 이수 중임) 그래도 내 페이스를 유지하면서 기획한 것을 완료하고 시간이 남는다면 멈추지 않고 확장시킬 것이다.
   ***
- ### 5/7  
portfolio 리스트에서 3가지의 필터(프로그래밍언어, 정렬방식, 협업여부)를 제공하기 때문에 총 4개의 테이블과 조인했다.
- like 테이블을 따로 뺐기 때문에 4개의 테이블과 조인함
- skill 테이블에 프로그래밍언어(영어,한글)가 정리되어 있고, 실제로 portfolio 테이블과 조인하는 것은 used_skill 테이블임
```sql
VIEW `temp_index_portfolio_view` AS
SELECT 
    `p`.`id` AS `id`,
    `p`.`thumbnail` AS `thumbnail`,
    `p`.`title` AS `title`,
    `p`.`hit` AS `hit`,
    `p`.`collaboration` AS `collaboration`,
    `p`.`reg_date` AS `reg_date`,
    `m`.`nickname` AS `nickname`,
    `m`.`image` AS `member_image`,
    COUNT(`l`.`portfolio_id`) AS `like_count`,
    `us`.`skill_id` AS `skill_id`
FROM
    (((`portfolio` `p`
    LEFT JOIN `member` `m` ON (`p`.`member_id` = `m`.`id`))
    LEFT JOIN `like` `l` ON (`p`.`id` = `l`.`portfolio_id`))
    LEFT JOIN `used_skill` `us` ON (`p`.`id` = `us`.`portfolio_id`))
GROUP BY `p`.`id` , `us`.`skill_id`
```
DB 테스트 시 결과가 잘 나왔는데, Mybatis에서 문제다.
프로그래밍언어(전체), 협업여부(전체) 둘 중 하나라도 전체인 경우 오류나는 것을 확인했다.
어떤 경우에도 where 절은 실행되고 경우에따라 and절이 실행될텐데 400 오류가 발생한다.  
고쳐보자...
```xml
where 1=1
<if test="collaboration != null">
    and collaboration = #{collaboration}
    <if test="skillId != null">
        and skill_id = #{skillId}
    </if>
</if>
<if test="collaboration == null and skillId != null">
    and skill_id = #{skillId}
</if>
```
