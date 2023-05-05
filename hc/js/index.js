const sliderContainer = document.querySelector('.slider-container');
const slider = sliderContainer.querySelector('.slider');
const prevButton = sliderContainer.querySelector('.prev-button');
const nextButton = sliderContainer.querySelector('.next-button');
const indicators = sliderContainer.querySelectorAll('.indicator');

const sliderItems = document.querySelectorAll('.slider a');
const indicatorContainer = document.querySelector('.indicator-container');
const indicatorList = document.querySelector('.indicator-list');
//const indicators = document.querySelectorAll('.indicator');
const slideWidth = sliderItems[0].getBoundingClientRect().width;

let currentIndex = 0;
const maxIndex = slider.children.length - 1;

// 슬라이드를 이동하는 함수
function moveSlider(index) {
  if (index < 0) {
    index = maxIndex;
  } else if (index > maxIndex) {
    index = 0;
  }
  slider.style.transform = `translateX(-${index * 33.333333}%)`;
  indicators[currentIndex].classList.remove('active');
  indicators[index].classList.add('active');
  currentIndex = index;

  // const sliderWidth = slider.offsetWidth;
  // const slideWidth = sliderWidth * 0.3;
  // const offset = slideWidth * (maxIndex - index) + sliderWidth * 0.3;

  // slider.style.transform = `translateX(${offset}px)`;
  // indicators[currentIndex].classList.remove('active');
  // indicators[index].classList.add('active');
  // currentIndex = index;

  // 인디케이터 활성화
  Array.from(indicators).forEach(indicator => indicator.classList.remove('active'));
  indicators[currentIndex].classList.add('active');
}

// 이전 버튼 클릭 이벤트 핸들러
prevButton.addEventListener('click', () => {
  moveSlider(currentIndex - 1);
});

// 다음 버튼 클릭 이벤트 핸들러
nextButton.addEventListener('click', () => {
  moveSlider(currentIndex + 1);
});

// 자동 슬라이드 기능
// let intervalId = setInterval(() => {
//   moveSlider(currentIndex + 1);
// }, 3000);

// 슬라이드에 마우스를 올렸을 때, 자동 슬라이드를 멈추도록 설정
sliderContainer.addEventListener('mouseenter', () => {
  clearInterval(intervalId);
});

// 슬라이드에서 마우스를 내렸을 때, 자동 슬라이드를 다시 시작하도록 설정
// sliderContainer.addEventListener('mouseleave', () => {
//   intervalId = setInterval(() => {
//     moveSlider(currentIndex + 1);
//   }, 3000);
// });

// 인디케이터 생성
// for (let i = 0; i < sliderItems.length; i++) {
//   const indicator = document.createElement('li');
//   indicator.classList.add('indicator');
//   if (i === 0) {
//     indicator.classList.add('active');
//   }
//   indicatorList.appendChild(indicator);
// }

// 인디케이터 클릭 이벤트 추가
indicatorList.addEventListener('click', function(e) {
  if (e.target && e.target.nodeName === 'LI') {
    // 클릭된 인디케이터의 인덱스 파악
    const targetIndex = Array.from(indicatorList.children).indexOf(e.target);
    // 해당 인덱스의 이미지로 이동
    moveSlider(targetIndex);
  }
});
