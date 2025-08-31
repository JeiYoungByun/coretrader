/*
 * =============================================
 * JavaScript - js/mainPage.js
 * - 사이트의 동적 기능을 담당합니다.
 * =============================================
*/

// 1. 데이터 정의 (서버에서 받아올 데이터를 담을 빈 배열로 변경)
let products = [];

// 2. DOM 요소 가져오기 (기존과 동일)
const productListContainer = document.getElementById('product-list');
const searchBox = document.getElementById('search-box');
const sortLatestBtn = document.getElementById('sort-latest');
const sortPriceAscBtn = document.getElementById('sort-price-asc');
const sortPriceDescBtn = document.getElementById('sort-price-desc');
const filterButtons = document.querySelectorAll('.filter-buttons button');

// 정렬 함수 정의 (기존과 동일)
let currentSortFunction = (a, b) => new Date(b.createdAt) - new Date(a.createdAt); // date -> createdAt

/**
 * 상품 데이터를 받아 화면에 렌더링하는 함수 (데이터 필드명 수정)
 * @param {Array} data - 화면에 표시할 상품 데이터 배열
 */
function renderProducts(data) {
    productListContainer.innerHTML = '';
    if (data.length === 0) {
        productListContainer.innerHTML = '<p class="text-center">일치하는 상품이 없습니다.</p>';
        return;
    }
    data.forEach((product, index) => {
        const card = document.createElement('div');
        card.className = 'product-card';
        card.style.animationDelay = `${index * 50}ms`;
        // DTO 필드명에 맞게 수정: image -> thumbnailUrl, seller -> sellerName, date -> createdAt
        card.innerHTML = `
            <img src="${product.thumbnailUrl || 'https://via.placeholder.com/300x200.png?text=No+Image'}" alt="${product.name}">
            <div class="product-info">
                <h3>${product.name}</h3>
                <p class="product-price">${product.price.toLocaleString()}원</p>
                <div class="product-meta">
                    <span>판매자: ${product.sellerName}</span>
                    <span>등록일: ${new Date(product.createdAt).toLocaleDateString()}</span>
                </div>
            </div>
        `;
        productListContainer.appendChild(card);
    });
}

/**
 * 현재 활성화된 버튼 스타일을 업데이트하는 함수 (기존과 동일)
 */
function updateActiveButton(activeButton) {
    filterButtons.forEach(button => button.classList.remove('active'));
    activeButton.classList.add('active');
}

/**
 * 검색과 정렬을 모두 적용하여 최종 결과를 렌더링하는 함수 (기존과 동일)
 */
function filterAndSortAndRender() {
    const searchTerm = searchBox.value.toLowerCase();
    const filteredProducts = products.filter(product => product.name.toLowerCase().includes(searchTerm));
    const sortedProducts = [...filteredProducts].sort(currentSortFunction);
    renderProducts(sortedProducts);
}

// --- 3. 핵심 변경 사항: 서버에서 상품 데이터를 가져오는 함수 ---
/**
 * 서버 API를 호출하여 모든 상품 데이터를 가져오는 비동기 함수
 */
async function fetchProducts() {
    try {
        const response = await fetch('/api/products'); // 백엔드 API 호출
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json(); // 응답을 JSON으로 파싱
        products = data; // 전역 products 배열에 서버 데이터 저장
        filterAndSortAndRender(); // 데이터를 받은 후 화면 렌더링
    } catch (error) {
        console.error("상품 데이터를 불러오는 데 실패했습니다:", error);
        productListContainer.innerHTML = '<p class="text-center text-danger">상품을 불러올 수 없습니다.</p>';
    }
}


// 4. 이벤트 리스너 설정 (기존과 동일)
searchBox.addEventListener('input', filterAndSortAndRender);

sortLatestBtn.addEventListener('click', () => {
    currentSortFunction = (a, b) => new Date(b.createdAt) - new Date(a.createdAt);
    updateActiveButton(sortLatestBtn);
    filterAndSortAndRender();
});

sortPriceAscBtn.addEventListener('click', () => {
    currentSortFunction = (a, b) => a.price - b.price;
    updateActiveButton(sortPriceAscBtn);
    filterAndSortAndRender();
});

sortPriceDescBtn.addEventListener('click', () => {
    currentSortFunction = (a, b) => b.price - a.price;
    updateActiveButton(sortPriceDescBtn);
    filterAndSortAndRender();
});

// 5. 초기 페이지 로드 시 실행 (데이터 fetch로 변경)
document.addEventListener('DOMContentLoaded', () => {
    updateActiveButton(sortLatestBtn);
    fetchProducts(); // 하드코딩된 데이터 대신 서버에서 데이터를 가져오도록 변경
});