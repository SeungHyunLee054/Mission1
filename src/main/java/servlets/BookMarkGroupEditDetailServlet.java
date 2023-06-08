package servlets;

import dto.BookMarkGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/bookmark-group-edit-detail")
public class BookMarkGroupEditDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectSql = "select * " +
                "from bookmark_group " +
                "where id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        BookMarkGroup bookMarkGroup = new BookMarkGroup();
        try {
            connection = DriverManager.getConnection(dbURL);
            pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, Integer.parseInt(req.getParameter("id")));
            rs = pstmt.executeQuery();

            bookMarkGroup.setId(rs.getInt("id"));
            bookMarkGroup.setBookmark_group_name(rs.getString("bookmark_group_name"));
            bookMarkGroup.setOrder(rs.getInt("order"));
            bookMarkGroup.setRegister_date(rs.getString("register_date"));
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

        req.setAttribute("bookMarkGroupData", bookMarkGroup);
        req.getRequestDispatcher("/bookmark-group-edit.jsp").forward(req, resp);
    }
}

