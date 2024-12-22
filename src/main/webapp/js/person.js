
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
        setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/login.html'; }, 1000);
		

});
//轉回首頁
backhome.addEventListener("click",()=>{
        setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/login.html'; }, 1000);
});

//重整資料
window.onload = function() { 
    document.getElementById('mailmsg').textContent=sessionStorage.getItem("usermail")|| '未提供';
    document.getElementById('namemsg').textContent=sessionStorage.getItem("name")|| '未提供';
    document.getElementById('gendermsg').textContent = sessionStorage.getItem("gender") || '未提供'; 
    document.getElementById('telmsg').textContent = sessionStorage.getItem("tel") || '未提供'; 
    document.getElementById('addrmsg').textContent = sessionStorage.getItem("addr") || '未提供';

    var dataList = JSON.parse(sessionStorage.getItem("dataList") || '[]');
    console.log(dataList);
    
    if (dataList.length>0) {
        var tableBody = document.getElementById('data');
        dataList.forEach(function(data) {
            var row = document.createElement('tr');
            row.innerHTML = `
                <td>${data.date}</td>
                <td>${data.no}</td>
                <td>${data.name}</td>
                <td>${data.price}</td>
                <td>${data.qua}</td>
                <td>${data.total}</td>
                <td>${data.status}</td>
            `;
            tableBody.appendChild(row);
        });
    }
};