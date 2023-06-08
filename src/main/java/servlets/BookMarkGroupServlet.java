package servlets;

import dto.BookMarkGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bookmark-group")
public class BookMarkGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectSql="select * " +
                "from bookmark_group " +
                "order by \"order\"";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<BookMarkGroup> bookMarkGroupList =new ArrayList<>();
        BookMarkGroup bookMarkGroup;
        try {
            connection = DriverManager.getConnection(dbURL);
            pstmt = connection.prepareStatement(selectSql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                bookMarkGroup = new BookMarkGroup();
                bookMarkGroup.setId(rs.getInt("id"));
                bookMarkGroup.setBookmark_group_name(rs.getString("bookmark_group_name"));
                bookMarkGroup.setOrder(rs.getInt("order"));
                bookMarkGroup.setRegister_date(rs.getString("register_date"));
                bookMarkGroup.setEdit_date(rs.getString("edit_date"));

                bookMarkGroupList.add(bookMarkGroup);
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

        req.setAttribute("bookMarkGroupList", bookMarkGroupList);
        req.getRequestDispatcher("bookmark-group.jsp").forward(req, resp);
    }
}
