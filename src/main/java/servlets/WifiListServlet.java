package servlets;

import dto.Wifi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/wifi-list")
public class WifiListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String getLat = req.getParameter("lat");
        String getLnt = req.getParameter("lnt");

        String selectSql = "select floor(6371 * acos(cos(radians(" + getLat + ")) * cos(radians(LAT)) * cos(radians(LNT) - radians(" + getLnt + ")) + " +
                "                         sin(radians(" + getLat + ")) * sin(radians(LAT))) * 10000) / 10000.0000 as dis,  " +
                "* " +
                "from wifi " +
                "order by dis " +
                "limit 0,20";

        String historySql = "insert into location_history " +
                "    (LAT, LNT, FIND_DATE) " +
                "VALUES (?, ?, datetime('now'))";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Wifi> dataList = new ArrayList<>();
        Wifi wifiData;
        try {
            connection = DriverManager.getConnection(dbURL);

            pstmt = connection.prepareStatement(historySql);
            pstmt.setDouble(1, Double.parseDouble(getLat));
            pstmt.setDouble(2, Double.parseDouble(getLnt));
            pstmt.executeUpdate();

            pstmt = connection.prepareStatement(selectSql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                wifiData = new Wifi();
                wifiData.setDistance(rs.getDouble("dis"));
                wifiData.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifiData.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifiData.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifiData.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifiData.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifiData.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifiData.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifiData.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifiData.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifiData.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifiData.setX_SWIFI_CNSTC_YEAR(rs.getInt("X_SWIFI_CNSTC_YEAR"));
                wifiData.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifiData.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifiData.setLAT(rs.getDouble("LAT"));
                wifiData.setLNT(rs.getDouble("LNT"));
                wifiData.setWORK_DTTM(rs.getString("WORK_DTTM"));

                dataList.add(wifiData);
            }
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

        req.setAttribute("dataList", dataList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
