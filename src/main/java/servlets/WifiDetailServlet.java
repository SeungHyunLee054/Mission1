package servlets;

import dto.BookMarkGroup;
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

@WebServlet("/detail")
public class WifiDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String getMgrNo = req.getParameter("mgrNo");
        String getDistance=req.getParameter("distance");

        String selectSql = "select * " +
                "from wifi " +
                "where X_SWIFI_MGR_NO = ?";

        String selectSql2="select bookmark_group_name " +
                "from bookmark_group";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Wifi wifiData = new Wifi();
        List<BookMarkGroup> bookMarkGroupList=new ArrayList<>();
        BookMarkGroup bookMarkGroup;
        try {
            connection = DriverManager.getConnection(dbURL);
            pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1,getMgrNo);
            rs = pstmt.executeQuery();

            wifiData.setDistance(Double.parseDouble(getDistance));
            wifiData.setX_SWIFI_MGR_NO(getMgrNo);
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

            pstmt=connection.prepareStatement(selectSql2);
            rs=pstmt.executeQuery();
            while (rs.next()){
                bookMarkGroup=new BookMarkGroup();
                bookMarkGroup.setBookmark_group_name(rs.getString("bookmark_group_name"));

                bookMarkGroupList.add(bookMarkGroup);
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

        req.setAttribute("bookMarkGroupList",bookMarkGroupList);
        req.setAttribute("wifiData", wifiData);
        req.getRequestDispatcher("wifi-detail.jsp").forward(req, resp);
    }
}
