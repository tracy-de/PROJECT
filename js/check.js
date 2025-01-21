var xmlHTTP;
var str;


function check() {
    
    if (window.ActiveXObject) {
        xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        xmlHTTP = new XMLHttpRequest();
    }


    //判斷登入身分
    var identityValue = document.querySelector('input[name="identity"]:checked').value; 
    if (!identityValue) { alert("請選擇身份"); return; } 
    var url; 
    if (identityValue == "1") { 
		url = "http://localhost:8080/PROJECT/login"; } 
	else if (identityValue == "2") { 
		url = "http://localhost:8080/PROJECT/login2"; } 
	else if (identityValue == "3") { 
		url ="http://localhost:8080/PROJECT/login3";}
    
    xmlHTTP.open("POST", url, true);   
    xmlHTTP.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // 傳遞表單資料
    var formData = "email=" + encodeURIComponent(document.querySelector("input[name=email]").value) +
                   "&password=" + encodeURIComponent(document.querySelector("input[name=password]").value)
                   +"&identity="+ identityValue
                   ;
    
    // 監聽請求狀態變化
    xmlHTTP.onreadystatechange = function check_status() {
        if (xmlHTTP.readyState == 4) { // 請求完成
            if (xmlHTTP.status == 200) { // 請求成功
                str = xmlHTTP.responseText;
                // 根據回傳的訊息，顯示錯誤或成功訊息
               
                alert(str);
                document.getElementById("message").innerHTML=str; 
                if(str=="登入成功!!!"){
					var usermail =document.querySelector("input[name=email]").value;
					sessionStorage.setItem("usermail",usermail);
					var identity =identityValue;
					sessionStorage.setItem("identity",identity);
			
					}
		
      		  	window.location.reload();
      		  	}
				
				
                
            }
        
    };

    // 送出請求
	xmlHTTP.send(formData);
}