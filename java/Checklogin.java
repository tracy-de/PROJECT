
import java.sql.PreparedStatement;
import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/login")
public class Checklogin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		Connection conn=null;
		PreparedStatement stmt=null,stmt2=null;
		ResultSet rs=null,rs2=null;
		String sDriver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://localhost/project";

		//比對帳密是否正確
		String sql="select * from client where client_mail=? and client_pwd=SHA2(?,256)"; 
		//抓取客戶資料及交易資料
		String sql2="select * , trade_qua*product_price total from trade t join product p on t.product_no=p.product_no where client_mail=? ";
		

		request.setCharacterEncoding("utf-8");
		String user=request.getParameter("email");
		String pwd=request.getParameter("password");
		String identity=request.getParameter("identity");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//帶入資料庫帳密
		String loginuser,loginpwd; 
		String [] login=new String [2];
		BufferedReader file=new BufferedReader(new FileReader("login.txt"));
		String res=	file.readLine();			
		login=res.split(",");
		loginuser=login[0];
		loginpwd=login[1];
		
		try {
			Class.forName(sDriver);
		}catch(Exception e) {System.out.print("驅動程式錯誤");return;
		}
		
		try {
			
			conn=DriverManager.getConnection(url,loginuser,loginpwd);
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,user);
			stmt.setString(2,pwd);
			stmt2=conn.prepareStatement(sql2);
			stmt2.setString(1,user);
			
		}    catch(Exception e) {       
            System.out.println("與資料來源連結錯誤: ");
            return;
        }
		
		try {
			//session存入客戶資料
			rs=stmt.executeQuery();
			if(rs.next()) { 

				HttpSession session = request.getSession(); 
				session.setAttribute("usermail", user);
				session.setAttribute("userpwd", pwd);
				session.setAttribute("identity", identity);
				session.setAttribute("name",rs.getString("client_name"));
				session.setAttribute("gender",rs.getString("client_gender"));
				session.setAttribute("tel",rs.getString("client_tel"));
				session.setAttribute("addr",rs.getString("client_addr"));

				//session存入交易資料
				rs2=stmt2.executeQuery();
				List<Map<String, String>> dataList = new ArrayList<>();
				while(rs2.next()) { 
				
					Map<String, String> data = new HashMap<>(); 
					data.put("date", rs2.getString("trade_date"));
					data.put("no", rs2.getString("trade_no"));
					data.put("name", rs2.getString("product_name"));
					data.put("price", rs2.getString("product_price"));
					data.put("qua", rs2.getString("trade_qua"));
					data.put("total", rs2.getString("total"));
					data.put("status", rs2.getString("trade_status"));
					dataList.add(data);
	
				}
				request.getSession().setAttribute("dataList",dataList);

				out.print("登入成功!!!");

			}
			else {		
		
				out.print("登入失敗");	
			}
			}catch(Exception e) {
				e.printStackTrace();
				out.print(e.getMessage());
			}

			
		try {
			rs.close();
			rs2.close();
			stmt.close();
			stmt2.close();
			conn.close();
			
		}catch(Exception e) {}
	}	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
		
	}	
}
