package servlets;

import dto.BookMark;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bookmark-list")
public class BookMarkListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectSql="select * " +
                "from bookmark ";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<BookMark> bookMarkList =new ArrayList<>();
        BookMark bookMark;
        try {
            connection = DriverManager.getConnection(dbURL);
            pstmt = connection.prepareStatement(selectSql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                bookMark = new BookMark();
                bookMark.setId(rs.getInt("id"));
                bookMark.setBookmark_group_name(rs.getString("bookmark_group_name"));
                bookMark.setWifi_name(rs.getString("wifi_name"));
                bookMark.setRegister_date(rs.getString("register_date"));
                bookMark.setBookmark_group_id(rs.getInt("bookmark_group_id"));
                bookMark.setMgr_no(rs.getString("mgr_no"));

                bookMarkList.add(bookMark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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

        req.setAttribute("distance",req.getParameter("distance"));
        req.setAttribute("bookMarkList", bookMarkList);
        req.getRequestDispatcher("bookmark-list.jsp").forward(req, resp);
    }
}
