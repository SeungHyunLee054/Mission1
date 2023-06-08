package servlets;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.Wifi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

@WebServlet("/load-wifi")
public class WifiLoadServlet extends HttpServlet {
    final String KEY = "744f764e7779656c353771516e676f";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int start = 1;
        int end = 1;
        String apiUrl = "http://openapi.seoul.go.kr:8088/" + KEY + "/json/TbPublicWifiInfo/" + start + "/" + end;
        String dbURL = "jdbc:SQLite:C:\\Users\\LSH\\IdeaProjects\\mission1\\src\\main\\webapp\\WEB-INF\\db\\wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String insertQuery = "insert or ignore into wifi " +
                "(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, " +
                " X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, " +
                " X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int totalCount = totalCnt(apiUrl);
        URL url = null;
        Connection connection = null;
        HttpURLConnection httpURLConnection = null;
        PreparedStatement pstmt = null;
        JsonObject result = null;

        int pageEnd = totalCount / 1000;
        int pageEndRemain = totalCount % 1000;
        for (int k = 0; k <= pageEnd; k++) {
            start = 1000 * k + 1;

            if (k == pageEnd) {
                end = start + pageEndRemain - 1;
            } else {
                end = 1000 * (k + 1);
            }

            apiUrl = "http://openapi.seoul.go.kr:8088/" + KEY + "/json/TbPublicWifiInfo/";
            apiUrl += start + "/" + end;

            try {
                url = new URL(apiUrl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Content-type", "application/json");
                int responseCode = httpURLConnection.getResponseCode();

                if (responseCode >= 200 && responseCode <= 300) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    in.close();

                    connection = DriverManager.getConnection(dbURL);
                    result = (JsonObject) new JsonParser().parse(response.toString());
                    JsonObject data = (JsonObject) result.get("TbPublicWifiInfo");
                    JsonArray jsonArray = (JsonArray) data.get("row");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        String jsonString = jsonArray.get(i).toString();
                        Wifi wifi = new Gson().fromJson(jsonString, Wifi.class);

                        pstmt = connection.prepareStatement(insertQuery);
                        pstmt.setString(1, wifi.getX_SWIFI_MGR_NO());
                        pstmt.setString(2, wifi.getX_SWIFI_WRDOFC());
                        pstmt.setString(3, wifi.getX_SWIFI_MAIN_NM());
                        pstmt.setString(4, wifi.getX_SWIFI_ADRES1());
                        pstmt.setString(5, wifi.getX_SWIFI_ADRES2());
                        pstmt.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
                        pstmt.setString(7, wifi.getX_SWIFI_INSTL_TY());
                        pstmt.setString(8, wifi.getX_SWIFI_INSTL_MBY());
                        pstmt.setString(9, wifi.getX_SWIFI_SVC_SE());
                        pstmt.setString(10, wifi.getX_SWIFI_CMCWR());
                        pstmt.setInt(11, wifi.getX_SWIFI_CNSTC_YEAR());
                        pstmt.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
                        pstmt.setString(13, wifi.getX_SWIFI_REMARS3());
                        pstmt.setDouble(14, wifi.getLNT());
                        pstmt.setDouble(15, wifi.getLAT());
                        pstmt.setString(16, wifi.getWORK_DTTM());
                        pstmt.executeUpdate();
                    }

                    httpURLConnection.disconnect();

                    req.setAttribute("totalCount", totalCount);
                } else {
                    BufferedReader error = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = error.readLine()) != null) {
                        response.append(inputLine);
                    }

                    error.close();
                    httpURLConnection.disconnect();
                }
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
        }

        req.getRequestDispatcher("/load-wifi.jsp").forward(req, resp);
    }

    public int totalCnt(String apiUrl) {
        JsonObject result = null;
        int totalCount = -1;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode >= 200 && responseCode <= 300) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                result = (JsonObject) new JsonParser().parse(response.toString());
                JsonObject data = (JsonObject) result.get("TbPublicWifiInfo");
                totalCount = data.get("list_total_count").getAsInt();

                httpURLConnection.disconnect();
            } else {
                BufferedReader error = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = error.readLine()) != null) {
                    response.append(inputLine);
                }

                error.close();
                httpURLConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalCount;
    }
}
