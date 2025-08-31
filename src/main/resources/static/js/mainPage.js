/*
 * =============================================
 * JavaScript - js/mainPage.js
 * - 사이트의 동적 기능을 담당합니다.
 * - 외부 라이브러리 없이 순수 바닐라 JavaScript로 작성되었습니다.
 * =============================================
*/

// 1. 데이터 정의 (더미 데이터)
const products = [
    { id: 1, name: 'Intel Core i9-13900K', price: 750000, image: 'https://via.placeholder.com/300x200.png?text=i9-13900K', seller: 'TechMaster', date: '2025-08-12', specs: { type: 'CPU', cpuSocket: 'LGA 1700' } },
    { id: 2, name: 'NVIDIA GeForce RTX 4080', price: 1550000, image: 'https://via.placeholder.com/300x200.png?text=RTX+4080', seller: 'GPU_Seller', date: '2025-08-11', specs: { type: 'GPU', gpuChipset: 'Ada Lovelace' } },
    { id: 3, name: 'G.SKILL Trident Z5 RGB 32GB', price: 280000, image: 'https://via.placeholder.com/300x200.png?text=DDR5+RAM', seller: 'MemoryMan', date: '2025-08-10', specs: { type: 'RAM', ramType: 'DDR5' } },
    { id: 4, name: 'AMD Ryzen 7 7800X3D', price: 580000, image: 'https://via.placeholder.com/300x200.png?text=Ryzen+7', seller: 'AMDFan', date: '2025-08-09', specs: { type: 'CPU', cpuSocket: 'AM5' } },
    { id: 5, name: 'Samsung 980 Pro 2TB NVMe SSD', price: 220000, image: 'https://via.placeholder.com/300x200.png?text=NVMe+SSD', seller: 'StorageKing', date: '2025-08-12', specs: { type: 'Storage', storageInterface: 'PCIe 4.0' } },
    { id: 6, name: 'AMD Radeon RX 7900 XTX', price: 1350000, image: 'https://via.placeholder.com/300x200.png?text=RX+7900+XTX', seller: 'RedTeam', date: '2025-08-08', specs: { type: 'GPU', gpuChipset: 'RDNA 3' } },
    { id: 7, name: 'Corsair Vengeance LPX 16GB', price: 95000, image: 'https://via.placeholder.com/300x200.png?text=DDR4+RAM', seller: 'BudgetBuilds', date: '2025-08-05', specs: { type: 'RAM', ramType: 'DDR4' } },
    { id: 8, name: 'Intel Core i5-13600K', price: 420000, image: 'https://via.placeholder.com/300x200.png?text=i5-13600K', seller: 'CPU_Seller', date: '2025-08-11', specs: { type: 'CPU', cpuSocket: 'LGA 1700' } }
];

// 2. DOM 요소 가져오기
const productListContainer = document.getElementById('product-list');
const searchBox = document.getElementById('search-box');
const sortLatestBtn = document.getElementById('sort-latest');
const sortPriceAscBtn = document.getElementById('sort-price-asc');
const sortPriceDescBtn = document.getElementById('sort-price-desc');
const filterButtons = document.querySelectorAll('.filter-buttons button');

let currentSortFunction = (a, b) => new Date(b.date) - new Date(a.date); // 기본 정렬: 최신순

/**
 * 상품 스펙 정보를 HTML 문자열로 생성하는 함수
 * @param {object} specs - 상품의 스펙 객체
 * @returns {string} - 스펙 정보를 담은 HTML 문자열
 */
function createSpecsHTML(specs) {
    let specsHTML = '';
    if (specs.cpuSocket) specsHTML += `<p><strong>CPU 소켓:</strong> ${specs.cpuSocket}</p>`;
    if (specs.gpuChipset) specsHTML += `<p><strong>GPU 칩셋:</strong> ${specs.gpuChipset}</p>`;
    if (specs.ramType) specsHTML += `<p><strong>RAM 타입:</strong> ${specs.ramType}</p>`;
    if (specs.storageInterface) specsHTML += `<p><strong>인터페이스:</strong> ${specs.storageInterface}</p>`;
    return specsHTML;
}

/**
 * 상품 데이터를 받아 화면에 렌더링하는 함수
 * @param {Array} data - 화면에 표시할 상품 데이터 배열
 */
function renderProducts(data) {
    productListContainer.innerHTML = '';
    if (data.length === 0) {
        productListContainer.innerHTML = '<p>일치하는 상품이 없습니다.</p>';
        return;
    }
    data.forEach((product, index) => {
        const card = document.createElement('div');
        card.className = 'product-card';
        card.style.animationDelay = `${index * 50}ms`;
        card.innerHTML = `
            <img src="${product.image}" alt="${product.name}">
            <div class="product-info">
                <h3>${product.name}</h3>
                <p class="product-price">${product.price.toLocaleString()}원</p>
                <div class="product-specs">${createSpecsHTML(product.specs)}</div>
                <div class="product-meta">
                    <span>판매자: ${product.seller}</span>
                    <span>등록일: ${product.date}</span>
                </div>
            </div>
        `;
        productListContainer.appendChild(card);
    });
}

/**
 * 현재 활성화된 버튼 스타일을 업데이트하는 함수
 * @param {HTMLElement} activeButton - 활성화할 버튼 요소
 */
function updateActiveButton(activeButton) {
    filterButtons.forEach(button => button.classList.remove('active'));
    activeButton.classList.add('active');
}

/**
 * 검색과 정렬을 모두 적용하여 최종 결과를 렌더링하는 함수
 */
function filterAndSortAndRender() {
    const searchTerm = searchBox.value.toLowerCase();
    const filteredProducts = products.filter(product => product.name.toLowerCase().includes(searchTerm));
    const sortedProducts = [...filteredProducts].sort(currentSortFunction);
    renderProducts(sortedProducts);
}

// 3. 이벤트 리스너 설정
searchBox.addEventListener('input', filterAndSortAndRender);

sortLatestBtn.addEventListener('click', () => {
    currentSortFunction = (a, b) => new Date(b.date) - new Date(a.date);
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

// 4. 초기 페이지 로드 시 실행
document.addEventListener('DOMContentLoaded', () => {
    updateActiveButton(sortLatestBtn);
    filterAndSortAndRender();
});