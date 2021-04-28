window.addEventListener('DOMContentLoaded', () => {
    const loginForm = window.document.getElementById('loginForm');
    loginForm.onsubmit = () => {
        if (loginForm['email'].value === ''){
            alert('이메일을 입력해주세요');
            return false;
        }else if(loginForm['password'].value === ''){
            alert('비밀번호를 입력해주세요');
            return false;
        }
    }
    
});
