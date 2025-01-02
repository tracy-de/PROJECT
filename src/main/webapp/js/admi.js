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
        setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/login.html'; }, 1000);
		

});

//轉回首頁
backhome.addEventListener("click",()=>{
        setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/login.html'; }, 1000);
});



//重整資料 	
window.onload = function() { 
    const target =sessionStorage.getItem("target");
    document.getElementById('mailmsg').textContent=sessionStorage.getItem("usermail")|| '未提供';
    
    if(target==="0"){	
        document.getElementById('c_targetmsg').textContent=sessionStorage.getItem("targetmail");
        document.getElementById('c_namemsg').textContent=sessionStorage.getItem("name");
        document.getElementById('c_idmsg').textContent = sessionStorage.getItem("id"); 
        document.getElementById('c_telmsg').textContent = sessionStorage.getItem("number"); 
        document.getElementById('c_addrmsg').textContent = sessionStorage.getItem("addr");
    }
    else if(target==="1"){
        
        document.getElementById('s_targetmsg').textContent=sessionStorage.getItem("targetmail");
        document.getElementById('s_namemsg').textContent=sessionStorage.getItem("name");
        document.getElementById('s_idmsg').textContent = sessionStorage.getItem("id"); 
        document.getElementById('s_telmsg').textContent = sessionStorage.getItem("number"); 
        document.getElementById('s_addrmsg').textContent = sessionStorage.getItem("addr");	
    }

    var dataList = JSON.parse(sessionStorage.getItem("dataList") || '[]');

    if (dataList.length>0) {
        var tableBody = document.getElementById('data');
        dataList.forEach(function(data) {
            var row = document.createElement('tr');
            row.innerHTML = `
                <td>${data.date}</td>
                <td>${data.name}</td>
                <td>${data.info}</td>
            `;
            tableBody.appendChild(row);
        });
    }
};