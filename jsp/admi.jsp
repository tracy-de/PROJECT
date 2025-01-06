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
    <title>Whisk & Whimsy 管理員資料</title>
    <link rel="stylesheet" href="../css/admi.css">

</head>
<body>
	
	<%
		String usermail = (String) session.getAttribute("usermail");
		String target = (String) session.getAttribute("target");				
		String name = (String) session.getAttribute("name");
		String id = (String) session.getAttribute("id");
		String number = (String) session.getAttribute("number");
		String addr = (String) session.getAttribute("addr");
		String targetmail = (String) session.getAttribute("targetmail");		
		List<Map<String, String>> dataList = (List<Map<String, String>>) session.getAttribute("dataList");
	%>
	
	  <span class="main_bg"></span>
    <div class="container">
 
        <section class="front">
            <img src="../upload/admi.png" alt="image">
            <div>
                <p>管理者信箱帳號</p>
                <div id="mailmsg"><% out.print(usermail); %></div>
                
            </div>
        </section>

        <section class="main">
            <div>
                <ul class="btns">
                    <li class="btn">會員管理</li>
                    <li class="btn">公布新資訊</li>
                    <li class="backhome">返回首頁</li>
                    <li class="logoutbtn">登出</li>
                </ul>

                <div>
                    <div class="details active">  <!--搜尋買賣方資料頁面-->
                        <div class="present">
                            <h3>會員管理</h3>
                            <p>請選擇對象</p>
                            <form action="http://localhost:8080/PROJECT/query" method="get">
                                <label for="client">
                                    <input type="radio" name="target" class="perstrg" value="0" id="client">買家
                                </label>
                                <label for="company">
                                    <input type="radio" name="target" class="comptrg" value="1" id="company">賣家
                                </label><br>
                                <label for="targetmail">
                                    信箱號碼<input type="email" name="targetmail" class="selectnum" id="targetmail">
                                </label>
                                <button class="querybtn">查詢</button>
                            </form>
                            <br>

                            <fieldset>
    
                                <legend>搜尋結果</legend>
                                    <div>
                                        <ul>
                                            <li>買/賣家信箱:<br><span id="c_targetmsg"><% out.print(targetmail); %></span></li>
                                            <li>姓名:<span id="c_namemsg"><% out.print(name); %></span></li>
                                            <li>性別:<span id="c_idmsg"><% out.print(id); %></span></li>
                                            <li>電話:<span id="c_telmsg"><% out.print(number); %></span></li>
                                            <li>地址:<span id="c_addrmsg"><% out.print(addr); %></span></li>       
                                        </ul>
                                    </div>    
                                
                            </fieldset>                        
                        </div>
                    </div>

                    <div class="details"> <!--活動資料頁面-->
                        <div class=""></div>
                            <h3>公布新資訊</h3>
                            <form action="../editadmi" method="post">

                            	<label for="date">日期</label>
                      			<input type="date" name="date">
                                  <button class="addbtn">新增</button><br>
                                  
                      			<label for="name">活動名稱</label>
                      			<input type="text" name="name"><br>
                      			<label for="info">活動內容</label>
                      			<textarea name="info" rows="4" cols="50"></textarea>
         
                            </form>
                          <table>
                              <tr>
                                  <th name="date">日期</th>
                                  <th name="name">活動名稱</th>
                                  <th name="info">活動內容</th>
                              </tr>
                        		<%
                        			if(dataList!=null){
                        				out.print("<tr>");
                        				for(Map<String,String> map : dataList){
                        					for(Map.Entry<String,String> enter : map.entrySet()){
                        						String value = enter.getValue();
                        						out.print("<td>"+value+"</td>");
                        					}
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

    <script src="../js/admi.js" defer></script>

</body>
</html>