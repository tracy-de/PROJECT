var xmlHTTP;
var str;


function check() {
    
    if (window.ActiveXObject) {
        xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        xmlHTTP = new XMLHttpRequest();
    }

    var url; 

    //判斷身分對應servlet
    var identityValue = document.querySelector('input[name="identity"]:checked').value; 
    console.log(identityValue);
    
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
                   +"&identity="+ encodeURIComponent(document.querySelector("input[name='identity']:checked").value)
                   ;
    
    xmlHTTP.onreadystatechange = function check_status() {
        if (xmlHTTP.readyState == 4) { 
            if (xmlHTTP.status == 200) { 
                str = xmlHTTP.responseText;
            
                alert(str);
                document.getElementById("message").innerHTML=str; 
                if(str=="登入成功!!!"){
					var usermail =document.querySelector("input[name=email]").value;
					sessionStorage.setItem("usermail",usermail);
					var password =document.querySelector("input[name=password]").value;
					sessionStorage.setItem("password",password);
					var identity =document.querySelector("input[name='identity']:checked").value;
					sessionStorage.setItem("identity",identity);
					console.log(identity);
					}
      		  	window.location.reload();
      		  	}
				
				
                
            }
        
    };

    // 送出請求
	xmlHTTP.send(formData);
}
