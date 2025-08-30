//
// const navHTML = `
//     <nav class="navbar" aria-label="주 메뉴">
//         <div class="nav-container">
//             <a href="/" id="main-menu" >메인 메뉴</a>
//             <a href="/transportation" id="shuttle" >셔틀</a>
//             <a href="/facility" id="facilities" >편의 시설</a>
//             <a href="/restaurant" id="restaurant" >맛집</a>
//             <a href="/login" id="login" style="background: lightseagreen">로그인</a>
//         </div>
//     </nav>
//     `;
//
// const navPlaceholder = document.getElementById('nav-placeholder');
// if (navPlaceholder) {
//     navPlaceholder.innerHTML = navHTML;
// }



const navPlaceholder = document.getElementById('nav-placeholder');

function loadNav() {
    fetch('/api/auth/status')
        .then(response => response.json())
        .then(data => {
            navPlaceholder.innerHTML = `
    <nav class="navbar" aria-label="주 메뉴">
        <div class="nav-container">
            <a href="/" id="main-menu">메인 페이지</a>
            <a href="/transportation" id="shuttle">셔틀</a>
            <a href="/facility" id="facilities">편의 시설</a>
            <a href="/restaurant" id="restaurant">맛집</a>
            ${data.authenticated ?
                '<a href="/logout" id="logout" style="background: lightseagreen">로그아웃</a>' :
                '<a href="/login" id="login" style="background: lightseagreen">로그인</a>'}
        </div>
    </nav>
            `;
        })
        .catch(err => console.error('Failed to load nav:', err));
}

if (navPlaceholder) loadNav();
if (navPlaceholder) loadNav();