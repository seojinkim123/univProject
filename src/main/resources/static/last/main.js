// document.addEventListener('DOMContentLoaded', function() {
//     const slides = document.querySelector('.slides');
//     const images = document.querySelectorAll('.slides img');
//     const dots = document.querySelectorAll('.dot');
//     const prevBtn = document.getElementById('prevBtn');
//     const nextBtn = document.getElementById('nextBtn');
//     let currentIndex = 0;
//
//     const slideWidth = document.querySelector('.slider').offsetWidth;
//
//     images.forEach(img => {
//         img.style.width = '100%';
//         img.style.objectFit = 'cover';
//     });
//
//     updateSlidePosition();
//
//     // 이전 버튼 클릭
//     prevBtn.addEventListener('click', function() {
//         currentIndex--;
//         if (currentIndex < 0) currentIndex = images.length - 1;
//         updateSlidePosition();
//     });
//
//     // 다음 버튼 클릭
//     nextBtn.addEventListener('click', function() {
//         currentIndex++;
//         if (currentIndex >= images.length) currentIndex = 0;
//         updateSlidePosition();
//     });
//
//     // 인디케이터 클릭
//     dots.forEach(dot => {
//         dot.addEventListener('click', function() {
//             currentIndex = parseInt(this.getAttribute('data-index'));
//             updateSlidePosition();
//         });
//     });
//
//     // 터치 슬라이드 기능
//     let touchStartX = 0;
//     let touchEndX = 0;
//
//     slides.addEventListener('touchstart', function(e) {
//         touchStartX = e.changedTouches[0].screenX;
//     });
//
//     slides.addEventListener('touchend', function(e) {
//         touchEndX = e.changedTouches[0].screenX;
//         handleSwipe();
//     });
//
//     function handleSwipe() {
//         if (touchEndX < touchStartX - 50) {
//             currentIndex++;
//             if (currentIndex >= images.length) currentIndex = 0;
//         }
//         if (touchEndX > touchStartX + 50) {
//             currentIndex--;
//             if (currentIndex < 0) currentIndex = images.length - 1;
//         }
//         updateSlidePosition();
//     }
//
//     function updateSlidePosition() {
//         slides.style.transform = `translateX(${-slideWidth * currentIndex}px)`;
//         dots.forEach(dot => dot.classList.remove('active'));
//         dots[currentIndex].classList.add('active');
//     }
// });

document.addEventListener('DOMContentLoaded', function() {
    // ========== 메인페이지 이미지 슬라이더 ==========
    const slides = document.querySelector('.slides'); // 슬라이드 컨테이너
    const images = document.querySelectorAll('.slides img'); // 모든 슬라이드 이미지
    const dots = document.querySelectorAll('.dot'); // 하단 인디케이터 점들
    const prevBtn = document.getElementById('prevBtn'); // 이전 버튼
    const nextBtn = document.getElementById('nextBtn'); // 다음 버튼
    let currentIndex = 0; // 현재 보여지는 이미지 번호

    // 슬라이더 너비: updateSlidePosition에서 동적으로 계산하도록 변경

    // 모든 슬라이드 이미지 크기 맞춤
    images.forEach(img => {
        img.style.width = '100%';
        img.style.objectFit = 'cover';
    });

    updateSlidePosition(); // 첫 화면 설정      // 이전 버튼 클릭 시 이전 이미지로 이동
    prevBtn.addEventListener('click', function() {
        currentIndex--;
        if (currentIndex < 0) currentIndex = images.length - 1; // 첫 번째에서 마지막으로
        updateSlidePosition();
    });

    // 다음 버튼 클릭 시 다음 이미지로 이동
    nextBtn.addEventListener('click', function() {
        currentIndex++;
        if (currentIndex >= images.length) currentIndex = 0; // 마지막에서 첫 번째로
        updateSlidePosition();
    });

    // 하단 인디케이터 점 클릭 시 해당 이미지로 바로 이동
    dots.forEach(dot => {
        dot.addEventListener('click', function() {
            currentIndex = parseInt(this.getAttribute('data-index'));
            updateSlidePosition();
        });
    });

    // ========== 슬라이드 위치 변경 및 활성 점 표시 ==========
    function updateSlidePosition() {
        const slideWidth = document.querySelector('.slider').offsetWidth; // 현재 슬라이더 너비
        slides.style.transform = `translateX(${-slideWidth * currentIndex}px)`; // 슬라이드 이동
        dots.forEach(dot => dot.classList.remove('active')); // 모든 점 비활성화
        dots[currentIndex].classList.add('active'); // 현재 점만 활성화
    }
    // 창 크기 변경 시 슬라이드 위치 재조정
    window.addEventListener('resize', updateSlidePosition);
    // 터치 이벤트를 위한 변수
    let touchStartX = 0;
    let touchEndX = 0;
    slides.addEventListener('touchstart', function(e) {
        touchStartX = e.changedTouches[0].screenX;
    });
    slides.addEventListener('touchend', function(e) {
        touchEndX = e.changedTouches[0].screenX;
        handleGesture();
    });
    function handleGesture() {
        if (touchEndX < touchStartX - 50) {
            nextBtn.click();
        }
        if (touchEndX > touchStartX + 50) {
            prevBtn.click();
        }
    }

    // 자동 슬라이드 5초 마다
    setInterval(() => {
        nextBtn.click();
    }, 5000);
    // ========== 월별 일정표 기능 ==========
    const scheduleContainer = document.querySelector('.schedule');
    if (scheduleContainer) {
        initSchedule(); // 일정표 시작
    }

    function initSchedule() {
        // 실제 시스템 날짜 받아와서 해당 월 표시
        const currentDate = new Date();
        let currentYear = currentDate.getFullYear(); // 현재 연도
        let currentMonth = currentDate.getMonth();   // 현재 월 (0=1월, 11=12월)

        // HTML 요소들 가져오기
        const prevMonthBtn = document.getElementById('prevMonth'); // 이전 달 버튼
        const nextMonthBtn = document.getElementById('nextMonth'); // 다음 달 버튼
        const currentMonthDisplay = document.getElementById('currentMonth'); // 월 표시 영역
        const scheduleList = document.getElementById('scheduleList'); // 일정 목록 영역
        // 샘플 일정 데이터 (나중에 서버 API로 교체 가능)
        const scheduleData = {
            '2025-2': [
                { date: 3, day: '월', content: '휴학 접수 시작' },
                { date: 12, day: '수', content: '12 (수) ~ 18 (화) 2025-1학기 수강신청' },
                { date: 21, day: '금', content: '21 (금) ~ 27 (목) 2025-1학기 등록' },
                { date: 23, day: '일', content: '졸업예배' },
                { date: 24, day: '월', content: '복학 접수 마감, 학위수여식' }
            ],
            '2025-3': [
                { date: 1, day: '토', content: '삼일절' },
                { date: 3, day: '월', content: '대체휴일' },
                { date: 4, day: '화', content: '개강' },
                { date: 6, day: '목', content: '6 (목) ~ 10 (월) 수강신청 확인 및 변경' },
                { date: 6, day: '목', content: '교무위원회' },
                { date: 12, day: '수', content: '12 (수) ~ 14 (금) 2025-1학기 추가등록' },
                { date: 17, day: '월', content: '미등록자 일반휴학 접수 마감' },
                { date: 17, day: '월', content: '17 (월) ~ 21 (금) 조기졸업 신청' }
            ],
            '2025-4': [
                { date: 3, day: '목', content: '교무위원회' },
                { date: 9, day: '수', content: '학기 1/3선' },
                { date: 14, day: '월', content: '14 (월) ~ 19 (토) 고난주간' },
                { date: 15, day: '화', content: '15 (화) ~ 17 (목) 수강철회' },
                { date: 20, day: '일', content: '부활절' },
                { date: 22, day: '화', content: '22 (화) ~ 28 (월) 중간시험' },
                { date: 28, day: '월', content: '28 (월) ~ 02 (금) 2025-2학기 캠퍼스내 소속변경 신청' },
                { date: 29, day: '화', content: '29 (화) ~ 01 (목) S/U평가 신청' }
            ],
            '2025-5': [
                { date: 29, day: '화', content: '29 (화) ~ 01 (목) S/U평가 신청' },
                { date: 1, day: '목', content: '근로자의날, 교무위원회' },
                { date: 28, day: '월', content: '28 (월) ~ 02 (금) 2025-2학기 캠퍼스내 소속변경 신청' },
                { date: 5, day: '월', content: '부처님 오신 날, 어린이날' },
                { date: 6, day: '화', content: '대체휴일' },
                { date: 7, day: '수', content: '은퇴교수의 날' },
                { date: 10, day: '토', content: '창립기념일' },
                { date: 16, day: '금', content: '학기 2/3선, 일반휴학 접수 마감' },
                { date: 19, day: '월', content: '질병휴학 접수시작' }
            ],
            '2025-6': [
                { date: 3, day: '화', content: '질병휴학 접수마감' },
                { date: 5, day: '목', content: '교무위원회' },
                { date: 6, day: '금', content: '현충일' },
                { date: 8, day: '일', content: '성령강림절' },
                { date: 10, day: '화', content: '10 (화) ~ 16 (월) 자율학습 및 보충수업 기간' },
                { date: 17, day: '화', content: '17 (화) ~ 23 (월) 학기말시험' },
                { date: 24, day: '화', content: '여름방학 시작' },
                { date: 24, day: '화', content: '24 (화) ~ 30 (월) 2025-2학기 캠퍼스내 복수전공 · 연계전공 · 융합심화전공 신청' },
                { date: 30, day: '월', content: '2025-1학기 성적제출 마감, 여름계절제 수업시작' }
            ],
            '2025-7': [
                { date: 14, day: '월', content: '복학 접수 시작' },
                { date: 21, day: '월', content: '여름계절제 수업종료' }
            ],
            '2025-8': [
                { date: 1, day: '금', content: '휴학 접수 시작' },
                { date: 11, day: '월', content: '11 (월) ~ 18 (월) 2025-2학기 수강신청' },
                { date: 15, day: '금', content: '광복절' },
                { date: 22, day: '금', content: '22 (금) ~ 28 (목) 2025-2학기 등록' },
                { date: 25, day: '월', content: '복학 접수 마감' },
                { date: 29, day: '금', content: '학위수여식' }
            ],
            '2025-9': [
                { date: 1, day: '월', content: '개강' },
                { date: 3, day: '수', content: '03 (수) ~ 05 (금) 수강신청 확인 및 변경' },
                { date: 4, day: '목', content: '교무위원회' },
                { date: 9, day: '화', content: '09 (화) ~ 11 (목) 2025-2학기 추가등록' },
                { date: 15, day: '월', content: '미등록자 일반휴학 접수 마감' },
                { date: 15, day: '월', content: '15 (월) ~ 19 (금) 조기졸업 신청' }
            ],
            '2025-10': [
                { date: 2, day: '목', content: '교무위원회' },
                { date: 3, day: '금', content: '개천절' },
                { date: 5, day: '일', content: '05 (일) ~ 07 (화) 추석연휴' },
                { date: 8, day: '수', content: '학기 1/3선, 대체휴일' },
                { date: 9, day: '목', content: '한글날' },
                { date: 13, day: '월', content: '13 (월) ~ 15 (수) 수강철회' },
                { date: 20, day: '월', content: '20 (월) ~ 25 (토) 중간시험' },
                { date: 27, day: '월', content: '27 (월) ~ 29 (수) S/U평가 신청' },
                { date: 27, day: '월', content: '27 (월) ~ 31 (금) 2026-1학기 캠퍼스내 소속변경 신청' }
            ],
            '2025-11': [
                { date: 6, day: '목', content: '교무위원회' },
                { date: 13, day: '목', content: '학기 2/3선, 일반휴학 접수 마감' },
                { date: 14, day: '금', content: '질병휴학 접수시작' },
                { date: 16, day: '일', content: '추수감사절' },
                { date: 24, day: '월', content: '24 (월) ~ 28 (금) 캠퍼스간 소속변경' }
            ],
            '2025-12': [
                { date: 1, day: '월', content: '질병휴학 접수마감' },
                { date: 4, day: '목', content: '교무위원회, 성탄절예배' },
                { date: 8, day: '월', content: '08 (월) ~ 13 (토) 자율학습 및 보충수업 기간' },
                { date: 15, day: '월', content: '15 (월) ~ 20 (토) 학기말 시험' },
                { date: 22, day: '월', content: '겨울방학시작' },
                { date: 22, day: '월', content: '22 (월) ~ 29 (월) 2026-1학기 캠퍼스내 복수전공 · 연계전공 · 융합심화전공 신청' },
                { date: 25, day: '목', content: '성탄절' },
                { date: 28, day: '일', content: '성적제출 마감' },
                { date: 29, day: '월', content: '겨울계절제 수업 시작' }
            ],
            '2026-1': [
                { date: 1, day: '목', content: '신정' },
                { date: 12, day: '월', content: '복학 접수 시작' },
                { date: 20, day: '화', content: '겨울계절제 수업 종료' }
            ],
            '2026-2': [
                { date: 2, day: '월', content: '휴학 접수 시작' },
                { date: 9, day: '월', content: '09 (월) ~ 13 (금) 2026-1학기 수강신청' },
                { date: 16, day: '월', content: '16 (월) ~ 18 (수) 설연휴' },
                { date: 22, day: '일', content: '졸업예배' },
                { date: 23, day: '월', content: '학위수여식, 복학 접수 마감' }
            ]
        };
        // 선택된 월의 일정을 화면에 표시
        function updateScheduleDisplay() {
            const monthKey = `${currentYear}-${currentMonth + 1}`; // "2025-5" 형태로 만들기
            const monthNames = ['1월', '2월', '3월', '4월', '5월', '6월',
                '7월', '8월', '9월', '10월', '11월', '12월'];

            // 상단에 "2025년 5월" 형태로 표시
            currentMonthDisplay.textContent = `${currentYear}년 ${monthNames[currentMonth]}`;

            // 해당 월의 일정 가져오기
            const schedules = scheduleData[monthKey] || [];
            // 일정이 있으면 목록으로 표시, 없으면 안내 메시지
            if (schedules.length === 0) {
                scheduleList.innerHTML = '<div class="schedule-empty"><i class="far fa-calendar-times" style="font-size: 24px; margin-bottom: 8px; display: block;"></i>이번 달 일정이 없습니다.</div>';
            } else {scheduleList.innerHTML = schedules.map(schedule => `
                    <div class="schedule-item">
                        <div class="schedule-date">${schedule.date}일</div>
                        <div class="schedule-day">(${schedule.day})</div>
                        <div class="schedule-content">
                            <i class="fas fa-calendar-day" style="margin-right: 8px; color: var(--primary-light);"></i>
                            ${schedule.content}
                        </div>
                    </div>
                `).join('');
            }
        }
        // 이전 달 버튼 클릭
        prevMonthBtn.addEventListener('click', function() {
            currentMonth--;
            if (currentMonth < 0) {
                currentMonth = 11; // 12월로
                currentYear--;
            }
            updateScheduleDisplay();
        });

        // 다음 달 버튼 클릭
        nextMonthBtn.addEventListener('click', function() {
            currentMonth++;
            if (currentMonth > 11) {
                currentMonth = 0; // 1월로
                currentYear++;
            }
            updateScheduleDisplay();
        });

        // 일정표 영역 우클릭 시 이전 달로 이동
        scheduleContainer.addEventListener('contextmenu', function(e) {
            e.preventDefault(); // 기본 우클릭 메뉴 방지
            currentMonth--;
            if (currentMonth < 0) {
                currentMonth = 11;
                currentYear--;
            }
            updateScheduleDisplay();
        });

        // 일정표 영역 좌클릭 시 다음 달로 이동 (버튼 제외)
        scheduleContainer.addEventListener('click', function(e) {
            // 화살표 버튼 클릭이 아닌 경우에만 실행
            if (!e.target.classList.contains('month-nav')) {
                currentMonth++;
                if (currentMonth > 11) {
                    currentMonth = 0;
                    currentYear++;
                }
                updateScheduleDisplay();
            }
        });

        // 전체 일정 버튼 기능
        const allScheduleBtn = document.getElementById('allSchedule');
        allScheduleBtn.addEventListener('click', function() {
            // 전체 일정 모달 열기
            const scheduleModal = document.getElementById('schedule-modal');
            const modalList = document.getElementById('modalScheduleList');
            const monthNames = ['1월', '2월', '3월', '4월', '5월', '6월',
                '7월', '8월', '9월', '10월', '11월', '12월'];
            let combinedHTML = '';
            Object.keys(scheduleData).forEach(key => {
                const [year, month] = key.split('-');
                combinedHTML += `<h4>${year}년 ${monthNames[month-1]}</h4>`;
                scheduleData[key].forEach(item => {
                    combinedHTML += `
                    <div class="schedule-item">
                        <div class="schedule-date">${item.date}일</div>
                        <div class="schedule-day">(${item.day})</div>
                        <div class="schedule-content">
                            <i class="fas fa-calendar-day" style="margin-right: 8px; color: var(--primary-light);"></i>
                            ${item.content}
                        </div>
                    </div>`;
                });
            });
            modalList.innerHTML = combinedHTML;
            scheduleModal.classList.remove('hidden');
        });
        // 모달 닫기
        const closeModalBtn = document.querySelector('.close-modal');
        closeModalBtn.addEventListener('click', function() {
            document.getElementById('schedule-modal').classList.add('hidden');
        });

        // 페이지 로드 시 현재 날짜 기준으로 일정 표시
        updateScheduleDisplay();
    }
});
