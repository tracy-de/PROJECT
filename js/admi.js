const items = document.querySelectorAll('.btn');
const details = document.querySelectorAll('.details');
const perstrg=document.querySelector('.perstrg')
const comptrg=document.querySelector('.comptrg')
const personres=document.querySelector('.personres')
const companyres=document.querySelector('.companyres')
const logoutbtn = document.querySelector(".logoutbtn");
const backhome = document.querySelector(".backhome");

//選單切換
items.forEach((item, index) => {
    item.addEventListener('click', () => {
        details.forEach(detail => detail.classList.remove('active'));
        details[index].classList.add('active');
    });
});

// 顯示買家資料 隱藏賣家資料
perstrg.addEventListener('change', function() {
    if (perstrg.checked) {
        personres.style.display = 'block'; 
        companyres.style.display = 'none'; 
    }
});

// 顯示賣家資料 隱藏買家資料
comptrg.addEventListener('change', function() {
    if (comptrg.checked) {
        personres.style.display = 'none'; 
        companyres.style.display = 'block'; 
    }});
    
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


