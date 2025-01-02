
import java.sql.PreparedStatement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/message")
public class Message extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		Connection conn=null;
		PreparedStatement stmt=null;
		String sDriver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://localhost/project";
		//新增客戶發送訊息資料
		String sql1="INSERT INTO message (msg_name,msg_mail,msg_text) VALUES (?, ?, ?)";


		request.setCharacterEncoding("utf-8");
		String msg_name=request.getParameter("msg_name");
		String msg_mail=request.getParameter("msg_mail");
		String msg_text=request.getParameter("msg_text");
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
			stmt=conn.prepareStatement(sql1);			
		}catch(Exception e) {       
            System.out.println("與資料來源連結錯誤: ");
            return;
        }
		
		try {

			stmt.setString(1,msg_name);
			stmt.setString(2,msg_mail);
			stmt.setString(3,msg_text);
			int updatedata=stmt.executeUpdate();
			if (updatedata>0) {
				out.print("<html><body>");
				out.print("<h1 style='color:orange;'>傳送成功，專人盡快回覆給您</h1>");
				out.print("<script>");
				out.print("setTimeout(function(){ window.location.href = 'index.html'; }, 2000);");
				out.print("</script>");
				out.print("</body></html>");
			}
			
		}catch(Exception e) {       
            System.out.println("與資料來源連結錯誤: "+e.getMessage());
            return;
        }
		

		try {
			stmt.close();
			conn.close();
				
		}catch(Exception e) {}
	}	
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
		
	}	
}
