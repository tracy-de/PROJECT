
const items = document.querySelectorAll('.btn');
const details = document.querySelectorAll('.details');
const editbtn = document.querySelector('.edit-btn');
const present = document.querySelector('.present');
const edit = document.querySelector('.edit');
const logoutbtn = document.querySelector(".logoutbtn");
const backhome = document.querySelector(".backhome");

//選單切換
items.forEach((item, index) => {
    item.addEventListener('click', () => {
    
        details.forEach(detail => detail.classList.remove('active'));

    
        details[index].classList.add('active');
    });
});

//轉向編輯選單
editbtn.addEventListener("click", () => {
    present.classList.add('details');
    edit.classList.add('active'); 
});

//登出後轉向首頁
logoutbtn.addEventListener("click",()=>{
    const usermail =sessionStorage.getItem("usermail");
        if (usermail){
        alert(usermail+"登出成功");
        sessionStorage.removeItem("usermail");}
        else{alert(usermail+"登出成功");}
        setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/index.html'; }, 1000);
		

});
//轉回首頁
backhome.addEventListener("click",()=>{
        setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/index.html'; }, 1000);
});
//驗證輸入格式
function checkData(event){
	var number=document.editform.number.value;
	var credit=document.editform.credit.value;
	const mobileRegex=/^09\d{8}$/;
	const creditRegex=/^\d{4}-\d{4}-\d{4}-\d{4}$/;
	if(!(mobileRegex.test(number))){
		alert("錯誤手機格式");
		event.preventDefault();
	}
	if(!(creditRegex.test(credit))){
		alert("錯誤信用卡格式");
		event.preventDefault();
	}
}
