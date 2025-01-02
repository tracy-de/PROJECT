
import java.sql.PreparedStatement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Editperson")
public class Editperson extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		Connection conn=null;
		PreparedStatement stmt=null;
		String sDriver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://localhost/project";
		//更改買方個人資料
		String sql1 = "UPDATE client SET client_name = ?,client_gender = ?, client_tel = ?, client_addr=? , client_credit_card=? WHERE client_mail = ?";

		HttpSession session=request.getSession(false);
		String usermail = (String) session.getAttribute("usermail");
		request.setCharacterEncoding("utf-8");

		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		String number=request.getParameter("number");
		String addr=request.getParameter("addr");
		String credit=request.getParameter("credit");

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

		}    catch(Exception e) {       
			System.out.println("與資料來源連結錯誤: ");
			return;
		}

		//修改買方個人資料
		try {

				stmt.setString(1,name);
				stmt.setString(2,gender);
				stmt.setString(3,number);
				stmt.setString(4,addr);
				stmt.setString(5,credit);
				stmt.setString(6,usermail);
				int updatedata=stmt.executeUpdate();

				if (updatedata>0) {
					session.setAttribute("name",name);
					session.setAttribute("gender",gender);
					session.setAttribute("tel",number);
					session.setAttribute("addr",addr);
					session.setAttribute("credit",credit);
					
					out.print("<html><body>");
					out.print("<h1 style='color:orange;'>修改資料成功</h1>");
					out.print("<script>");
					out.print("setTimeout(function(){ window.location.href = './jsp/person.jsp'; }, 2000);");
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
