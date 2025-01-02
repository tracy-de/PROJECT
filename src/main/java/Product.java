
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

@WebServlet("/product")
public class Product extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		String sDriver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://localhost/project";
		//帶入賣方產品資訊
		String sql="select product_name,product_price,product_status,product_photo from product ";
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
				jsonObject.put("name", rs.getString("product_name"));
				jsonObject.put("price", rs.getFloat("product_price"));
				jsonObject.put("status", rs.getString("product_status"));
				jsonObject.put("photo", rs.getString("product_photo"));
				jsonArray.put(jsonObject);
	        }
	        rs.close();
	        stmt.close();
			conn.close();
		}catch(Exception e) {out.print(e.getMessage());}
		
		out.print(jsonArray.toString());
		out.flush();
	}
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
		
	}	
}
	
