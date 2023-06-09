package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/bookmark-add")
public class BookMarkAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String insertSql = "insert into bookmark " +
                "    (bookmark_group_name, wifi_name, register_date, bookmark_group_id, mgr_no) " +
                "select ?, X_SWIFI_MAIN_NM, datetime('now'), (select id from bookmark_group where bookmark_group_name = ?), ? " +
                "from wifi " +
                "where X_SWIFI_MGR_NO = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DriverManager.getConnection(dbURL);
            pstmt = connection.prepareStatement(insertSql);
            pstmt.setString(1, req.getParameter("bookmarkGroupName"));
            pstmt.setString(2, req.getParameter("bookmarkGroupName"));
            pstmt.setString(3, req.getParameter("mgrNo"));
            pstmt.setString(4, req.getParameter("mgrNo"));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("distance",req.getParameter("distance"));
        req.getRequestDispatcher("bookmark-add-submit.jsp").forward(req, resp);
    }
}
