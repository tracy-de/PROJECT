
import java.beans.Statement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/event")
public class Event extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		String sDriver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://localhost/project";
		//帶入活動資訊
		String sql="select event_date,event_name,event_info,created_at from event order by event_date";
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		JSONArray jsonArray = new JSONArray();
		
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
		}    catch(Exception e) {       
            System.out.println("與資料來源連結錯誤: ");
            return;
        }
		
		try {
			
			rs=stmt.executeQuery();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("date", rs.getString("event_date"));
				jsonObject.put("name", rs.getString("event_name"));
				jsonObject.put("info", rs.getString("event_info"));
				jsonObject.put("time", rs.getString("created_at"));
				jsonArray.put(jsonObject);
	        }
	        rs.close();
	        stmt.close();
			conn.close();
		}catch(Exception e) {out.print(e.getMessage());}
		
		out.print(jsonArray.toString());
		
	}
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
		
	}	
}
	
