<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--帶入管理員資料--> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admi jsp</title>
</head>
<body>

	<script>
	var usermail = "<%= session.getAttribute("usermail") != null ? session.getAttribute("usermail") : "" %>";
	var name = "<%= session.getAttribute("name") != null ? session.getAttribute("name") : "" %>";
	var id = "<%= session.getAttribute("id") != null ? session.getAttribute("id") : "" %>";
	var number = "<%= session.getAttribute("number") != null ? session.getAttribute("number") : "" %>";
	var addr = "<%= session.getAttribute("addr") != null ? session.getAttribute("addr") : "" %>";
	var target = "<%= session.getAttribute("target") != null ? session.getAttribute("target") : "" %>";
	var targetmail = "<%= session.getAttribute("targetmail") != null ? session.getAttribute("targetmail") : "" %>";
	
	<% org.json.JSONArray dataList = null; 
	if (session.getAttribute("dataList") != null) { 
		java.util.ArrayList dataListArray = (java.util.ArrayList) session.getAttribute("dataList"); 
		dataList = new org.json.JSONArray(dataListArray); } 
	else { 
		dataList = new org.json.JSONArray(); } %> 
	
	var dataList = <%= dataList.toString() %>;
		
	sessionStorage.setItem("usermail",usermail);
	sessionStorage.setItem("name", name); 
	sessionStorage.setItem("id", id); 
	sessionStorage.setItem("number", number); 
	sessionStorage.setItem("addr",addr);
	sessionStorage.setItem("targetmail",targetmail);
	sessionStorage.setItem("target",target);

	sessionStorage.setItem("dataList", JSON.stringify(dataList));
	window.location.href="../html/admi.html";
	
	</script>

</body>
</html>