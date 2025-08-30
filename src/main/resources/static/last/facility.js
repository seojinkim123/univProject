// // 필터링 기능
// document.addEventListener('DOMContentLoaded', function() {
//   const filterButtons = document.querySelectorAll('.filter-btn');
//   const facilityCards = document.querySelectorAll('.facility-card');
//
//   filterButtons.forEach(button => {
//     button.addEventListener('click', function() {
//       filterButtons.forEach(btn => btn.classList.remove('active'));
//       this.classList.add('active');
//
//       const filterValue = this.getAttribute('data-filter');
//
//       facilityCards.forEach(card => {
//         if (filterValue === 'all' || card.getAttribute('data-category').includes(filterValue)) {
//           card.style.display = 'flex';
//         } else {
//           card.style.display = 'none';
//         }
//       });
//     });
//   });
//
//
// });


// 필터링 및 검색 기능
document.addEventListener('DOMContentLoaded', function() {
  const filterButtons = document.querySelectorAll('.filter-btn');
  const cards         = document.querySelectorAll('.facility-card');
  const searchInput   = document.getElementById('facility-search');
  const searchBtn     = document.getElementById('search-btn');
  const noResults     = document.querySelector('.no-results-message');
  const container     = document.querySelector('.facility-container');

  let selectedCategory = 'all';
  let searchQuery      = '';

  function updateDisplay() {
    let visibleCount = 0;

    cards.forEach(card => {
      const name     = card.querySelector('h3').textContent.toLowerCase();
      const category = card.dataset.category;

      const matchCategory = (selectedCategory === 'all' || category.includes(selectedCategory));
      const matchSearch   = (!searchQuery || name.includes(searchQuery));

      if (matchCategory && matchSearch) {
        card.style.display = 'flex';
        visibleCount++;
      } else {
        card.style.display = 'none';
      }
    });

    if (visibleCount === 0) {
      container.style.display = 'none';
      noResults.style.display = 'flex';
    } else {
      noResults.style.display = 'none';
      container.style.display = 'grid';
    }
  }

  //카테고리 필터
  filterButtons.forEach(btn => {
    btn.addEventListener('click', function() {
      filterButtons.forEach(b => b.classList.remove('active'));
      this.classList.add('active');
      selectedCategory = this.dataset.filter;
      updateDisplay();
    });
  });

  //검색 기능
  searchBtn.addEventListener('click', () => {
    searchQuery = searchInput.value.trim().toLowerCase();
    updateDisplay();
  });
});