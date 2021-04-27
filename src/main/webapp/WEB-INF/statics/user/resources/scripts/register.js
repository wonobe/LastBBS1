window.addEventListener('DOMContentLoaded', () => {
    const registerForm = window.document.getElementById('registerForm');
    registerForm['AddressPostFindButton'].addEventListener('click', () => {
        new daum.Postcode({
            oncomplete: (data) => {
                registerForm['addressPost'].value = data['zonecode'];
                registerForm['addressPrimary'].value = data['address'];
                registerForm['addressSecondary'].value = '';
                registerForm['addressSecondary'].focus();
            }
        }).open();
    });

    const emailRegex = new RegExp('');
    const passwordRegex = new RegExp('');
    const nameFirstRegex = new RegExp('');
    const nameOptionalRegex = new RegExp('');
    const nameLastRegex = new RegExp('');
    const contactSecondRegex = new RegExp('');
    const contactThirdRegex = new RegExp('');
    const addressSecondaryRegex = new RegExp('');
    registerForm.onsubmit = () => {
        if(registerForm['password'].value !== registerForm['passwordCheck'].value){
            alert('비밀번호가 서로 일치하지 않습니다.');
            registerForm['password'].focus();
            return false;
        }
    };
});
