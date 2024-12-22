<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--帶入賣方公司資料-->     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>company jsp</title>
</head>
<body>

	<script>
	var usermail = "<%= session.getAttribute("usermail") != null ? session.getAttribute("usermail") : "" %>";
	var name = "<%= session.getAttribute("name") != null ? session.getAttribute("name") : "" %>";
	var id = "<%= session.getAttribute("id") != null ? session.getAttribute("id") : "" %>";
	var number = "<%= session.getAttribute("number") != null ? session.getAttribute("number") : "" %>";
	var addr = "<%= session.getAttribute("addr") != null ? session.getAttribute("addr") : "" %>";
	var type = "<%= session.getAttribute("type") != null ? session.getAttribute("type") : "" %>";
	
	//交易資料
	<% org.json.JSONArray dataList = null; 
	if (session.getAttribute("dataList") != null) { 
		java.util.ArrayList dataListArray = (java.util.ArrayList) session.getAttribute("dataList"); 
		dataList = new org.json.JSONArray(dataListArray); } 
	else { 
		dataList = new org.json.JSONArray(); } %> 

		var dataList = <%= dataList.toString() %>;

	//產品資料
	<% org.json.JSONArray dataList2 = null; 
	if (session.getAttribute("dataList2") != null) { 
		java.util.ArrayList dataListArray2 = (java.util.ArrayList) session.getAttribute("dataList2"); 
		dataList2 = new org.json.JSONArray(dataListArray2); } 
	else { 
		dataList2 = new org.json.JSONArray(); } %> 

		var dataList2 = <%= dataList2.toString() %>;
		
	
		sessionStorage.setItem("usermail",usermail);
		sessionStorage.setItem("name", name); 
		sessionStorage.setItem("id", id); 
		sessionStorage.setItem("number", number); 
		sessionStorage.setItem("addr",addr);
		sessionStorage.setItem("type",type);
		
		sessionStorage.setItem("dataList", JSON.stringify(dataList));
		sessionStorage.setItem("dataList2", JSON.stringify(dataList2));

		window.location.href="../html/company.html";
	
	</script>

</body>
</html>