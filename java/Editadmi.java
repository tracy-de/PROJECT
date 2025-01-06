import java.sql.PreparedStatement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/editadmi")
public class Editadmi extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		Connection conn=null;
		PreparedStatement stmt=null;
		String sDriver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://localhost/project";
		//新增管理員活動資訊
		String sql1 = "insert into event SET event_date = ?,event_name = ?,event_info = ? , admin_mail = ?";

		HttpSession session=request.getSession(false);
		String usermail = (String) session.getAttribute("usermail");
		
		request.setCharacterEncoding("utf-8");
		String date=request.getParameter("date");
		String name=request.getParameter("name");
		String info=request.getParameter("info");
		//新增活動資訊在後方
		List<Map<String, String>> dataList = (List<Map<String, String>>) session.getAttribute("dataList");
		if(dataList==null) {dataList=new ArrayList<>();}
		
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
		}catch(Exception e) {
			System.out.print("驅動程式錯誤");
			return;
		}
		
		try {
			conn=DriverManager.getConnection(url,loginuser,loginpwd);
			stmt=conn.prepareStatement(sql1);	
		}catch(Exception e) {       
            System.out.println("與資料來源連結錯誤: ");
            return;
        }
		
		//新增活動資訊
		try { 

			stmt.setString(1,date);
			stmt.setString(2,name);
			stmt.setString(3, info);
			stmt.setString(4, usermail);
			int updatedata=stmt.executeUpdate();
			if (updatedata>0) {
				Map<String, String> data = new HashMap<>();
				data.put("date", date);
				data.put("name", name);
				data.put("info", info);
				dataList.add(data);
				}
				
			request.getSession().setAttribute("dataList",dataList);
						
				out.print("<html><body>");
				out.print("<h1 style='color:orange;'>資料新增成功</h1>");
				out.print("<script>");
				out.print("setTimeout(function(){ window.location.href = './jsp/admi.jsp'; }, 2000);");
				out.print("</script>");
				out.print("</body></html>");
				
		
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
