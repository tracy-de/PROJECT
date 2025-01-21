import java.sql.PreparedStatement;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/editproduct")
@MultipartConfig
public class Editproduct extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

		Connection conn = null;
		PreparedStatement stmt = null, stmt2 = null, stmt3 = null, stmt4 = null;
		ResultSet rs3 = null, rs4 = null;
		String fileName = null;
		String no = null;

		String sDriver = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost/project";
		//新增賣方產品資訊
		String sql1 = "insert into product SET  product_photo= ?,product_no = ?,product_name = ? , product_price = ? ,product_status=? , seller_mail=?";
		//更新賣方產品資訊
		String sql2 = "update product SET  product_photo= ?,product_name = ? , product_price = ? ,product_status=? where product_no = ? and seller_mail=?  ";
		//查詢產品編號是否重複
		String sql3 = "select * from product where product_no=?";
		//帶入產品資訊
		String sql4 = "select * from  product where seller_mail=?";
		List<Map<String, String>> dataList2 = new ArrayList<>();

		HttpSession session = request.getSession(false);
		String usermail = (String) session.getAttribute("usermail");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String pro_no = request.getParameter("pro_no");
		String pro_name = request.getParameter("pro_name");
		String pro_price = request.getParameter("pro_price");
		String pro_status = request.getParameter("pro_status");
		
		// 上傳圖片
		try {
			
			Part filePart = request.getPart("pro_photo"); 
			fileName = filePart.getSubmittedFileName();
	
			String savePath = "workspace/PROJECT/src/main/webapp/upload/" + fileName;
			File uploadDir = new File("workspace/PROJECT/src/main/webapp/upload/");
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
	
			InputStream fileContent = filePart.getInputStream();
			Files.copy(fileContent, Paths.get(savePath), StandardCopyOption.REPLACE_EXISTING);
		
		}catch(Exception e) {
			out.print("<html><body>");
			out.print("<h1 style='color:orange;'>上傳圖片失敗</h1>");
			out.print("<script>");
			out.print("setTimeout(function(){ window.location.href = './jsp/company.jsp'; }, 2000);");
			out.print("</script>");
			out.print("</body></html>");
		}

		//判斷輸入資料格式是否正確
		try {  
			if (Integer.parseInt(pro_no) > 0 & Float.parseFloat(pro_price) > 0) {

			} else {
				throw new Exception("資料輸入錯誤請確認");
			}

		} catch (Exception e) {
			out.print("<html><body>");
			out.print("<h1 style='color:orange;'>資料輸入錯誤請確認</h1>");
			out.print("<script>");
			out.print("setTimeout(function(){ window.location.href = './jsp/company.jsp'; }, 2000);");
			out.print("</script>");
			out.print("</body></html>");
		}
		//帶入資料庫帳密
		String loginuser, loginpwd; 
		String[] login = new String[2];
		BufferedReader file = new BufferedReader(new FileReader("login.txt"));
		String res = file.readLine();
		login = res.split(",");
		loginuser = login[0];
		loginpwd = login[1];

		try {
			Class.forName(sDriver);
		} catch (Exception e) {
			System.out.print("驅動程式錯誤");
			return;
		}
		// 查詢商品編號是否存在或是為自家公司的商品編號
		try { 

			conn = DriverManager.getConnection(url, loginuser, loginpwd);
			stmt3 = conn.prepareStatement(sql3);
			stmt3.setString(1, pro_no);

			rs3 = stmt3.executeQuery();
			
			if (rs3.next()) {
				if (rs3.getString("seller_mail").equals(usermail)) {
					no = pro_no;
				} else {
					throw new SQLIntegrityConstraintViolationException();
				}
			}
			// 商品編號其他賣方已使用
		} catch (SQLIntegrityConstraintViolationException e) {
			out.print("<html><body>");
			out.print("<h1 style='color:orange;'>商品編號請更改</h1>");
			out.print("<script>");
			out.print("setTimeout(function(){ window.location.href = './jsp/company.jsp'; }, 2000);");
			out.print("</script>");
			out.print("</body></html>");
		} catch (Exception e) {}

		try { 
			//新增資料
			if (no != pro_no) { 

				stmt = conn.prepareStatement(sql1);
				stmt.setString(1, fileName);
				stmt.setString(2, pro_no);
				stmt.setString(3, pro_name);
				stmt.setString(4, pro_price);
				stmt.setString(5, pro_status);
				stmt.setString(6, usermail);
				stmt.executeUpdate();
			} 
			//修改資料
			else { 
				 
					stmt2 = conn.prepareStatement(sql2);
					stmt2.setString(1, fileName);
					stmt2.setString(2, pro_name);
					stmt2.setString(3, pro_price);
					stmt2.setString(4, pro_status);
					stmt2.setString(5, pro_no);
					stmt2.setString(6, usermail);
					stmt2.executeUpdate();
			}

			stmt4 = conn.prepareStatement(sql4);
			stmt4.setString(1, usermail);
			rs4 = stmt4.executeQuery();
			//Session存入產品資料
			while (rs4.next()) { 
				Map<String, String> data = new HashMap<>();

				data.put("pro_photo", "<img src='"+"../upload/" + rs4.getString("product_photo") + "'>");
				data.put("pro_no", rs4.getString("product_no"));
				data.put("pro_name", rs4.getString("product_name"));
				data.put("pro_price", rs4.getString("product_price"));
				data.put("pro_date", rs4.getString("sale_create"));
				data.put("pro_status", rs4.getString("product_status"));
				dataList2.add(data);
			}
			request.getSession().setAttribute("dataList2", dataList2);

			out.print("<html><body>");
			out.print("<h1 style='color:orange;'>資料新增成功</h1>");
			out.print("<script>");
			out.print("setTimeout(function(){ window.location.href = './jsp/company.jsp'; }, 2000);");
			out.print("</script>");
			out.print("</body></html>");

		} catch (Exception e) {
			System.out.println("與資料來源連結錯誤: " + e.getMessage());
			return;
		}

		try {
			rs3.close();
			rs4.close();
			stmt.close();
			stmt2.close();
			stmt3.close();
			stmt4.close();
			conn.close();
		} catch (Exception e) {
			return;
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
}
