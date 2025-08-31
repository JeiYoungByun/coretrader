document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');

    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            event.preventDefault(); // 기본 폼 제출 방지

            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const data = { email, password };

            fetch('/api/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) {
                        // 서버에서 받은 텍스트 에러 메시지를 그대로 사용
                        return response.text().then(text => { throw new Error(text || '서버 오류가 발생했습니다.') });
                    }
                    return response.text();
                })
                .then(message => {
                    alert(message); // "로그인 성공!" 메시지
                    window.location.href = '/main/mainPage'; // 로그인 성공 후 메인 페이지로 이동
                })
                .catch(error => {
                    // 에러 메시지를 더 명확하게 표시
                    alert('아이디 혹은 비밀번호를 다시 입력해주세요');
                });
        });
    }
});