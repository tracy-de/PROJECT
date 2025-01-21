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
    <title>Whisk & Whimsy 買方資料</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/person.css">
</head>
<body>
 <% 
 	String usermail=(String)session.getAttribute("usermail");
 	String name=(String)session.getAttribute("name");
 	String gender=(String)session.getAttribute("gender");
 	String tel=(String)session.getAttribute("tel");
 	String addr=(String)session.getAttribute("addr");
 	List<Map<String, String>> dataList=(List<Map<String, String>>) session.getAttribute("dataList");
 %>
 <span class="main_bg"></span>
    <div class="container">

        <section class="front">
            <img src="../upload/cake.png" alt="image">
            <div>
                <p>買方信箱帳號</p>
                <div id="mailmsg"><% out.print(usermail); %></div>
            </div>
        </section>

        <section class="main">
            <div>
                <ul class="btns">
                    <li class="btn">個人資訊</li>
                    <li class="btn">購買查詢</li>
                    <li class="btn backhome" >返回首頁</li>
                    <li class="btn logoutbtn" >登出</li>
                </ul>

                <div>
                    <div class="details active"> <!--顯示頁面-->
                        <div class="present">
                            <p>個人資訊</p>
                            <button class="edit-btn">編輯個人資訊</button>
                            <ul class="info">
                                <li>姓名:<span id="namemsg"><% out.print(name); %></span></li>
                                <li>性別:<span id="gendermsg"><% out.print(gender); %></span></li>
                                <li>電話:<span id="telmsg"><% out.print(tel); %></span></li>
                                <li>地址:<span id="addrmsg"><% out.print(addr); %></span></li>     
                            </ul>                     
                        </div>

                        <div class="edit"> <!--編輯個人資訊頁面-->
                        <form action="../Editperson" method="post" name="editform">
                            <p>請填寫個人資訊</p>
                            <label for="name">姓名
                            <input type="text" name="name" id="name" required>
                            </label>

                            <p>性別</p>
                            <label for="man">
                                <input type="radio" name="gender" id="man" value="M" required>男生
                            </label>
                            <label for="woman">
                                <input type="radio" name="gender" id="woman" value="F">女生
                            </label><br>

                            <label for="number">電話
                                <input type="tel" name="number" id="number" required placeholder="0912345678">
                            </label><br>

                            <label for="addr">地址
                                <input type="text" name="addr" id="addr" required>
                            </label><br>

                            <label for="credit">信用卡卡號
                                <input type="text" name="credit" id="credit" required placeholder="xxxx-xxxx-xxxx-xxxx">
                            </label>
                            
                            <button class="submit-btn" onclick="checkData(event)">填寫完成</button>
                        </form>
                        </div>
                    </div>

                    <div class="details"> <!--顯示交易資訊頁面-->
                       <p>購買查詢</p>
                            <table>                                   
                              <tr>
                                  <th>日期</th>
                                  <th>單號</th>
                                  <th>商品名稱</th>
                                  <th>金額</th>
                                  <th>數量</th>
                                  <th>小計</th>
                                  <th>狀態</th>
                              </tr>
							  <%
							    if (dataList != null) {
							        for (Map<String, String> map : dataList) {
							            out.print("<tr>");								           
							            out.print("<td>" + map.get("date") + "</td>");     
							            out.print("<td>" + map.get("no") + "</td>");       
							            out.print("<td>" + map.get("name") + "</td>");     
							            out.print("<td>" + map.get("price") + "</td>");    
							            out.print("<td>" + map.get("qua") + "</td>");      
							            out.print("<td>" + map.get("total") + "</td>");    
							            out.print("<td>" + map.get("status") + "</td>");   
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
    <script src="../js/person.js" defer></script>

</body>
</html>