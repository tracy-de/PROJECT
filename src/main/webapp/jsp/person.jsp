<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--帶入買方個人資料-->   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>person JSP</title>
</head>
<body>
	<script>
	var usermail = "<%= session.getAttribute("usermail") != null ? session.getAttribute("usermail") : "" %>";
	var name = "<%= session.getAttribute("name") != null ? session.getAttribute("name") : "" %>";
	var gender = "<%= session.getAttribute("gender") != null ? session.getAttribute("gender") : "" %>";
	var tel = "<%= session.getAttribute("tel") != null ? session.getAttribute("tel") : "" %>";
	var addr = "<%= session.getAttribute("addr") != null ? session.getAttribute("addr") : "" %>";
		
	
	<% org.json.JSONArray dataList = null; 
	if (session.getAttribute("dataList") != null) { 
		java.util.ArrayList dataListArray = (java.util.ArrayList) session.getAttribute("dataList"); 
		dataList = new org.json.JSONArray(dataListArray); } 
	else { 
		dataList = new org.json.JSONArray(); } %> 
		var dataList = <%= dataList.toString() %>;
		
	
		sessionStorage.setItem("usermail",usermail);
		sessionStorage.setItem("name", name); 
		sessionStorage.setItem("gender", gender); 
		sessionStorage.setItem("tel", tel); 
		sessionStorage.setItem("addr",addr);

		sessionStorage.setItem("dataList", JSON.stringify(dataList));
		window.location.href="../html/person.html";
	
	</script>

</body>
</html>