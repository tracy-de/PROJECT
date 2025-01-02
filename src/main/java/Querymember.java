import java.sql.PreparedStatement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/query")
public class Querymember extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null, stmt2 = null;
		ResultSet rs = null, rs2 = null;
		String sDriver = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost/project";

		//查詢買方個人資料
		String sql = "select * from client where client_mail=?";
		//查詢賣方公司資料
		String sql2 = "select * from company where company_mail=? ";

		request.setCharacterEncoding("utf-8");
		String targetmail = request.getParameter("targetmail");
		String target = request.getParameter("target");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

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

		try {
			conn = DriverManager.getConnection(url, loginuser, loginpwd);
			//查詢買方資料
			if (target.equals("0")) {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, targetmail);
			//查詢賣方資料
			} else { 
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setString(1, targetmail);
			}
		} catch (Exception e) {
			System.out.println("與資料來源連結錯誤: ");
			return;
		}

		try {

			if (target.equals("1")) {
				rs2 = stmt2.executeQuery();
				if (rs2.next()) {
					HttpSession session = request.getSession();
					session.setAttribute("targetmail", targetmail);
					session.setAttribute("target", target);
					session.setAttribute("name", rs2.getString("company_name"));
					session.setAttribute("id", rs2.getString("company_id"));
					session.setAttribute("number", rs2.getString("company_tel"));
					session.setAttribute("addr", rs2.getString("company_addr"));

					out.println("<script>");
					out.print(
							"setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/jsp/admi.jsp'; }, 100);");
					out.println("</script>");
					
				} else {
					throw new SQLIntegrityConstraintViolationException();
				}

			}

			else if (target.equals("0")) {
				rs = stmt.executeQuery();
				if (rs.next()) {
					HttpSession session = request.getSession();
					session.setAttribute("targetmail", targetmail);
					session.setAttribute("target", target);
					session.setAttribute("name", rs.getString("client_name"));
					session.setAttribute("id", rs.getString("client_gender"));
					session.setAttribute("number", rs.getString("client_tel"));
					session.setAttribute("addr", rs.getString("client_addr"));

					out.println("<script>");
					out.print(
							"setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/jsp/admi.jsp'; }, 100);");
					out.println("</script>");

				} else {
					throw new SQLIntegrityConstraintViolationException();
				}
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			out.print("<html><body>");
			out.print("<h1 style='color:orange;'>信箱資訊錯誤請確認</h1>");
			out.print("<script>");
			out.print("setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/jsp/admi.jsp'; }, 2000);");
			out.print("</script>");
			out.print("</body></html>");
		} catch (Exception e) {
			e.printStackTrace();
			out.print(e.getMessage());
			out.println("<script>");
			out.print(
					"setTimeout(function(){ window.location.href = 'http://localhost:8080/PROJECT/jsp/admi.jsp'; }, 2000);");
			out.println("</script>");
		}

		try {
			rs.close();
			rs2.close();
			stmt.close();
			stmt2.close();
			conn.close();
		} catch (Exception e) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
}