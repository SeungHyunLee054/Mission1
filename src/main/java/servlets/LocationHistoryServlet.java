package servlets;

import dto.LocationHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/history")
public class LocationHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectSql="select * " +
                "from location_history";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<LocationHistory> historyList =new ArrayList<>();
        LocationHistory locationHistory;
        try {
            connection = DriverManager.getConnection(dbURL);
            pstmt = connection.prepareStatement(selectSql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                locationHistory = new LocationHistory();
                locationHistory.setId(rs.getInt("id"));
                locationHistory.setLAT(rs.getDouble("LAT"));
                locationHistory.setLNT(rs.getDouble("LNT"));
                locationHistory.setFIND_DATE(rs.getString("FIND_DATE"));

                historyList.add(locationHistory);
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

        req.setAttribute("historyList", historyList);
        req.getRequestDispatcher("/location-history.jsp").forward(req, resp);
    }
}
