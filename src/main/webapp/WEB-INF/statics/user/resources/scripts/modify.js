window.addEventListener('DOMContentLoaded', () => {
    const modifyForm = window.document.getElementById('modifyForm');
    modifyForm['AddressPostFindButton'].addEventListener('click', () => {
        new daum.Postcode({
            oncomplete: (data) => {
                modifyForm['addressPost'].value = data['zonecode'];
                modifyForm['addressPrimary'].value = data['address'];
                modifyForm['addressSecondary'].value = '';
                modifyForm['addressSecondary'].focus();
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
    modifyForm.onsubmit = () => {
        if(modifyForm['passwordNew'].value !== modifyForm['passwordNewCheck'].value){
            alert('비밀번호가 서로 일치하지 않습니다.');
            modifyForm['passwordNew'].focus();
            return false;
        }
    };
});
