import java.sql.PreparedStatement;
import java.io.*;
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


@WebServlet("/login3")
public class Checklogin3 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		Connection conn=null;
		PreparedStatement stmt=null,stmt2=null;
		ResultSet rs=null,rs2=null;
		String sDriver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://localhost/project";
		
		//比對管理員帳密
		String sql="select * from admin where admi_mail=? and admi_pwd=SHA2(?,256) "; 
		//抓取活動資訊
		String sql2="select * from event where admin_mail=?"; 
		

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
			
			rs=stmt.executeQuery();
				
			if(rs.next()) {
				//Session存入管理員資訊
				HttpSession session = request.getSession();  
				session.setAttribute("usermail", user);
				session.setAttribute("userpwd", pwd);
				session.setAttribute("identity", identity);
				
				//Session存入活動資訊
				List<Map<String, String>> dataList = new ArrayList<>();

				rs2=stmt2.executeQuery();  
				while(rs2.next()) {
					Map<String, String> data = new HashMap<>();
					data.put("date", rs2.getString("event_date"));
					data.put("name", rs2.getString("event_name"));
					data.put("info", rs2.getString("event_info"));

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
