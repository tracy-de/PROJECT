const showPopupBtn = document.querySelector(".login-btn");
const formPopup = document.querySelector(".form-pop");
const hidePopupBtn = formPopup.querySelector(".close-btn");
const signupLoginLink = formPopup.querySelectorAll(".bottom-link a");

//選單切換
showPopupBtn.addEventListener("click", () => {
	const usermail = sessionStorage.getItem("usermail");
	
	if(usermail){
		
		document.body.classList.remove("show-pop");
		showPopupBtn.textContent="登入"
		alert(usermail+"登出成功");
		sessionStorage.removeItem("usermail");
		location.reload();
	}
	else{document.body.classList.toggle("show-pop");}

	
});
		
		
// 隱藏登入註冊畫面
hidePopupBtn.addEventListener("click", () => showPopupBtn.click());

// 切換登入註冊畫面
signupLoginLink.forEach(link => {
    link.addEventListener("click", () => {
  
        formPopup.classList[link.id === 'signup-link' ? 'add' : 'remove']("show-signup");
    });
});


window.onload = function() {
	//最新消息資訊
	const xhr= new XMLHttpRequest();
	xhr.open("get","http://localhost:8080/PROJECT/event",true);
	xhr.onreadystatechange=function(){
		if(xhr.readyState===4){
			if(xhr.status===200){
				const data=JSON.parse(xhr.responseText);
				const container1=document.getElementById("event_data");
				
				data.forEach(item=>{
					const itemTr = document.createElement("tr");
					itemTr.classList.add("item");
					itemTr.innerHTML=`
						<td style="width:20%;border:0.3vw groove orange">${item.date}</td>
						<td style="width:20%;border:0.3vw outset orange">${item.name}</td>
						<td style="width:50%;border:0.3vw groove orange">${item.info}</td>
						<td style="border:0.3vw outset orange">${item.time}</td>`
						
					container1.appendChild(itemTr);
					
				});		
			}else{console.error("存取錯誤",xhr.statusText);}
		}
	}
	xhr.send();

    //更新產品資訊
	const xhr2= new XMLHttpRequest();
	xhr2.open("get","http://localhost:8080/PROJECT/product",true);
	xhr2.onreadystatechange=function(){
		if(xhr2.readyState===4){
			if(xhr2.status===200){
				const data=JSON.parse(xhr2.responseText);
				
				const container2=document.getElementById("product_data");
				data.forEach(item2=>{
					const itemTr2 = document.createElement("tr");
					itemTr2.classList.add("item2");
					
					itemTr2.innerHTML=`
						<td style="width:40%;border:0.3vw ridge chocolate">${item2.name}</td>
						<td style="width:20%;border:0.3vw inset chocolate">${item2.price}</td>
						<td style="width:20%;border:0.3vw ridge chocolate">${item2.status}</td>
						<td style="border:0.3vw inset chocolate"><img style="width:20%"; src='upload/${item2.photo}'></td>
					`		
					container2.appendChild(itemTr2);
				});		
			}else{console.error("存取錯誤",xhr2.statusText);}
		}
	}
	xhr2.send();

	//登出登入判斷
	const btncontainer = document.querySelector(".btncontainer");
	let profilebtn = document.createElement("button");
	const usermail = sessionStorage.getItem("usermail");
	const identity = sessionStorage.getItem("identity");

	if (usermail != null) {
		document.getElementById("loginbtn").textContent = "登出";
		document.getElementById("usermail").textContent = "用戶: " + usermail
				+ " 您好";
		if (identity === "1") {
			profilebtn.innerText = "買家會員資料";
			profilebtn
					.addEventListener(
							'click',
							function() {
								setTimeout(
										function() {
											window.location.href = 'http://localhost:8080/PROJECT/jsp/person.jsp';
										}, 2000);
							});

		} else if (identity === "2") {
			profilebtn.innerText = "賣家會員資料";
			profilebtn
					.addEventListener(
							'click',
							function() {
								setTimeout(
										function() {
											window.location.href = 'http://localhost:8080/PROJECT/jsp/company.jsp';
										}, 2000);
							});
		}

		else if (identity === "3") {
			profilebtn.innerText = "管理員資料";
			profilebtn
					.addEventListener(
							'click',
							function() {
								setTimeout(
										function() {
											window.location.href = 'http://localhost:8080/PROJECT/jsp/admi.jsp';
										}, 2000);
							});

		}

		profilebtn.className = "profilebtn";
		btncontainer.appendChild(profilebtn);

	} else {
		document.getElementById("loginbtn").textContent = "登入";
		document.getElementById("usermail").textContent = "您好";
	}
}

//驗證輸入格式
function checkMsg(event){
	var emailMsg = document.editMsg.msg_mail.value
	
	const emailRegex=/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	if(!(emailRegex.test(emailMsg))){
		alert("聯絡信箱格式錯誤");
		event.preventDefault();
	}
}

function checkRegister(event){
	var emailRegister = document.editRegister.email.value;

	const emailRegex=/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	if(!(emailRegex.test(emailRegister))&&emailRegister!=null){
		alert("註冊信箱格式錯誤");
		event.preventDefault();
	}

}




