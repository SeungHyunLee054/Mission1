package servlets;

import dto.BookMark;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/bookmark-delete-detail")
public class BookMarkDeleteDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectSql = "select * " +
                "from bookmark " +
                "where id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        BookMark bookMarkData = new BookMark();
        try {
            connection = DriverManager.getConnection(dbURL);
            pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, Integer.parseInt(req.getParameter("id")));
            rs = pstmt.executeQuery();

            bookMarkData.setId(rs.getInt("id"));
            bookMarkData.setBookmark_group_name(rs.getString("bookmark_group_name"));
            bookMarkData.setWifi_name(rs.getString("wifi_name"));
            bookMarkData.setRegister_date(rs.getString("register_date"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

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

        req.setAttribute("bookMarkData", bookMarkData);
        req.getRequestDispatcher("/bookmark-delete.jsp").forward(req, resp);
    }
}
