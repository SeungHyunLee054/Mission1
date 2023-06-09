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

@WebServlet("/bookmark-group-delete")
public class BookMarkGroupDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String deleteBookMarkGroupSql = "delete " +
                "from bookmark_group " +
                "where id = ?";

        String deleteBookMarkSql = "delete " +
                "from bookmark " +
                "where bookmark_group_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DriverManager.getConnection(dbURL);
            pstmt = connection.prepareStatement(deleteBookMarkGroupSql);
            pstmt.setInt(1, Integer.parseInt(req.getParameter("id")));
            pstmt.executeUpdate();

            pstmt = connection.prepareStatement(deleteBookMarkSql);
            pstmt.setInt(1, Integer.parseInt(req.getParameter("id")));
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

        req.getRequestDispatcher("bookmark-group-delete-submit.jsp").forward(req, resp);
    }
}
