<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
   
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="../upload/cake0.png" type="image/x-icon">
    <link rel="shortcut icon" href="../upload/cake0.png" type="image/x-icon">
    <title>Whisk & Whimsy 賣方資料</title>
    <link rel="stylesheet" href="../css/company.css">
</head>
<body>
	<%
		String usermail = (String)session.getAttribute("usermail");
		String name = (String)session.getAttribute("name");
		String id = (String)session.getAttribute("id");
		String number = (String)session.getAttribute("number");
		String addr = (String)session.getAttribute("addr");
		String type = (String)session.getAttribute("type");
		List<Map<String, String>> dataList=(List<Map<String, String>>) session.getAttribute("dataList");
		List<Map<String, String>> dataList2=(List<Map<String, String>>) session.getAttribute("dataList2");
	%>

  <span class="main_bg"></span>
    <div class="container">
 
        <section class="front">
            <img src="../upload/company.png" alt="image">
            <div>
                <p>賣方信箱帳號</p>
                <div id="mailmsg"><% out.print(usermail); %></div>
            </div>
        </section>

        <section class="main">
            <div>
                <ul class="btns">
                    <li class="btn">公司資訊</li>
                    <li class="btn">訂單管理</li>
                    <li class="btn">商品管理</li>
                    <li class="backhome">返回首頁</li>
                    <li class="logoutbtn">登出</li>
                </ul>

                <div> <!--顯示頁面-->
                    <div class="details active">
                        <div class="present">
                            <h3>公司資訊</h3>
                            <button class="edit-btn">編輯公司資訊</button>
                            <ul>
                                <li>公司名稱:<span id="namemsg"><% out.print(name); %></span></li>
                                <li>統編:<span id="idmsg"><% out.print(id); %></span></li>
                                <li>電話:<span id="telmsg"><% out.print(number); %></span></li>
                                <li>地址:<span id="addrmsg"><% out.print(addr); %></span></li> 
                                <li>分類:<span id="typemsg"><% out.print(type); %></span></li>  
                            </ul>                                               
                        </div>

                        <div class="edit"><!--編輯公司資訊頁面-->
                        <form action="../Editcompany" method="post" name="editform">
                            <p>請填寫個人資訊</p>
                            <label for="name">公司名稱
                            <input type="text" name="name" id="name" required>
                            </label><br>
                            <label for="id">統編
                                <input type="text" name="id" id="id" required>
                                </label><br>
                            <label for="number">電話
                                <input type="tel" name="number" id="number" required placeholder="031234567">
                            </label><br>
                            <label for="addr">地址
                                <input type="text" name="addr" id="addr" required>
                            </label><br>
                              <label for="type">類別
                                <input type="text" name="type" id="type" required>
                            </label>
                            <button class="submit-btn" onclick="checkData(event)">填寫完成</button>
                        </form>
                        </div>
                    </div>

                    <div class="details"><!--顯示訂單資訊頁面-->
                       <h3>訂單管理</h3>							
                         <table>
                            <tr>
                             <th>日期</th>                                        
                             <th>訂單編號</th>
                             <th>商品名稱</th>		                                    
                             <th>金額</th>
                             <th>數量</th>
                             <th>小計</th>
                             <th>狀態</th>
                            </tr>
                 			<%
                 				if(dataList!=null){
                 					for(Map<String,String> map : dataList){
                 						out.print("<tr>");
                 						out.print("<td>"+map.get("date")+"</td>");
                 						out.print("<td>"+map.get("no")+"</td>");
                 						out.print("<td>"+map.get("name")+"</td>");
                 						out.print("<td>"+map.get("price")+"</td>");
                 						out.print("<td>"+map.get("qua")+"</td>");
                 						out.print("<td>"+map.get("total")+"</td>");
                 						out.print("<td>"+map.get("status")+"</td>");
                 						out.print("</tr>");
                 					}
                 				}
                 			%>
                         </table>
                    </div>

                    
                    <div class="details"><!--編輯商品資訊頁面-->                    
                       <h3>商品管理</h3>

                       <form action="../editproduct" method="post" enctype="multipart/form-data">

                           <label for="pro_photo">照片</label>
                           <input type="file" name="pro_photo">
                           <button class="addbtn">新增</button><br>
                           <label for="pro_no">商品編號</label>
                 			<input type="text" name="pro_no"><br>
                 			<label for="pro_name">商品名稱</label>
                 			<input type="text" name="pro_name"><br>
                           <label for="pro_price">金額</label>
                 			<input type="text" name="pro_price"><br>
                 			<label for="pro_status">狀態</label>
                 			<input type="text" name="pro_status"><br>

                       </form>
                       <!--顯示商品資訊頁面-->
                        <table>
                             <tr>
                              <th>照片</th>	
                              <th>商品編號</th>        
                              <th>商品名稱</th>		                                    
                              <th>金額</th>
                              <th>上架日</th>
                              <th>狀態</th>
                             </tr>
       							<%
                       				if(dataList2!=null){
                       					for(Map<String,String> map : dataList2){
                       						out.print("<tr>");
											out.print("<td>"+map.get("pro_photo")+"</td>");
											out.print("<td>"+map.get("pro_no")+"</td>");
											out.print("<td>"+map.get("pro_name")+"</td>");
											out.print("<td>"+map.get("pro_price")+"</td>");
											out.print("<td>"+map.get("pro_date")+"</td>");
											out.print("<td>"+map.get("pro_status")+"</td>");
											out.print("</tr>");
                       					}
                       				}
                       			%>
                        </table> 
                    </div>
                </div>
            </div>
        </section>     
    </div>
    <script src="../js/company.js" defer></script>
</body>
</html>