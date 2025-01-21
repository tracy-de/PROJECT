
import java.sql.PreparedStatement;
import java.io.*;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class Register extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		Connection conn=null;
		PreparedStatement stmt=null,stmt2=null;
		ResultSet rs=null;
		String sDriver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://localhost/project";
		//查詢買方是否重複信箱 無則新增
		String sql1="INSERT INTO client (client_mail,client_pwd) VALUES (?, SHA2(?,256))";
		String sql2="SELECT * FROM client WHERE client_mail=?";
		//查詢賣方是否重複信箱 無則新增
		String sql3="INSERT INTO company (company_mail,company_pwd) VALUES (?, SHA2(?,256))";
		String sql4="SELECT * FROM company WHERE company_mail=?";

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
			
			if(identity.equals("0")) {
				stmt=conn.prepareStatement(sql1);
				stmt2=conn.prepareStatement(sql2);
				stmt2.setString(1,user);
			}
			else if(identity.equals("1")){
				stmt=conn.prepareStatement(sql3);
				stmt2=conn.prepareStatement(sql4);
				stmt2.setString(1,user);
	
			}			
		}catch(Exception e) {       
            System.out.println("與資料來源連結錯誤: ");
            return;
        }
		
		try {
			rs=stmt2.executeQuery();
			
			if (rs.next()) {
		
				out.print("<html><body>");
				out.print("<h1 style='color:red;'>重複註冊信箱:"+user+"</h1>");
				out.print("<script>");
				out.print("setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/index.html'; }, 2000);");
				out.print("</script>");
				out.print("</body></html>");}
			else {
		
				stmt.setString(1,user);
				stmt.setString(2,pwd);
	
				int updatedata=stmt.executeUpdate();
				if (updatedata>0) {
					out.print("<html><body>");
					out.print("<h1 style='color:orange;'>註冊成功</h1>");
					out.print("<script>");
					out.print("setTimeout(function(){ window.location.href = 'index.html'; }, 2000);");
					out.print("</script>");
					out.print("</body></html>");
				}
			}
		}catch(Exception e) {       
            System.out.println("與資料來源連結錯誤: "+e.getMessage());
            return;
        }
		

		try {
			rs.close();
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
