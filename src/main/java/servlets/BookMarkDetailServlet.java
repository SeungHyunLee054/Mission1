package servlets;

import dto.BookMark;
import dto.BookMarkGroup;
import dto.Wifi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/bookmark-detail")
public class BookMarkDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectBookMarkSql = "select b.id, b.register_date, bg.*, w.* " +
                "from bookmark b " +
                "         join wifi w on b.mgr_no = w.X_SWIFI_MGR_NO " +
                "         join bookmark_group bg on b.bookmark_group_id = bg.id " +
                "where b.id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Wifi wifiData = new Wifi();
        BookMarkGroup bookMarkGroupData =new BookMarkGroup();
        BookMark bookMarkData =new BookMark();
        try {
            connection = DriverManager.getConnection(dbURL);
            pstmt = connection.prepareStatement(selectBookMarkSql);
            pstmt.setString(1,req.getParameter("id"));
            rs = pstmt.executeQuery();

            bookMarkData.setId(rs.getInt(1));
            bookMarkData.setRegister_date(rs.getString("register_date"));
            bookMarkGroupData.setId(rs.getInt(3));
            bookMarkGroupData.setBookmark_group_name(rs.getString("bookmark_group_name"));
            bookMarkGroupData.setOrder(rs.getInt("order"));
            bookMarkGroupData.setRegister_date(rs.getString(6));
            bookMarkGroupData.setEdit_date(rs.getString("edit_date"));
            if (req.getParameter("distance").equals("")) {
                wifiData.setDistance(rs.getDouble("distance"));
            } else {
                wifiData.setDistance(Double.parseDouble(req.getParameter("distance")));
            }
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

        req.setAttribute("wifiData", wifiData);
        req.setAttribute("bookMarkData", bookMarkData);
        req.setAttribute("bookMarkGroupData", bookMarkGroupData);
        req.getRequestDispatcher("bookmark-detail.jsp").forward(req, resp);
    }
}
