<%@ page import="java.util.List" %>
<%@ page import="dto.Wifi" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    double lat = 0.0;
    double lnt = 0.0;

    if (request.getParameter("lat") != null && request.getParameter("lnt") != null) {
        lat = Double.parseDouble(request.getParameter("lat"));
        lnt = Double.parseDouble(request.getParameter("lnt"));
    }
%>
<!doctype html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/location.js"></script>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<%@ include file="/top.jsp" %>
<br>
<form action="${pageContext.request.contextPath}/wifi-list" method="get">
    LAT: <input type="text" name="lat" id="lat" value="<%=lat%>">, LNT:
    <input type="text" name="lnt" id="lnt" value="<%=lnt%>">
    <input type="button" onclick="getLocation()" value="내 위치 가져오기">
    <input type="submit" value="근처 WIFI 정보 보기">
</form>
<br>
<table>
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <% List<Wifi> wifiList = (List<Wifi>) request.getAttribute("dataList");%>
    <% if (wifiList != null && !wifiList.isEmpty()) {%>
    <% for (Wifi wifiData : wifiList) {%>
    <tr>
        <td>
            <%=String.format("%.4f", wifiData.getDistance())%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_MGR_NO()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_WRDOFC()%>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/detail?mgrNo=<%=wifiData.getX_SWIFI_MGR_NO()%>&distance=<%=wifiData.getDistance()%>">
                <%=wifiData.getX_SWIFI_MAIN_NM()%>
            </a>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_ADRES1()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_ADRES2()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_INSTL_FLOOR() != null ? wifiData.getX_SWIFI_INSTL_FLOOR() : ""%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_INSTL_TY()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_INSTL_MBY()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_SVC_SE()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_CMCWR()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_CNSTC_YEAR()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_INOUT_DOOR()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_REMARS3()%>
        </td>
        <td>
            <%=wifiData.getLAT()%>
        </td>
        <td>
            <%=wifiData.getLNT()%>
        </td>
        <td>
            <%=wifiData.getWORK_DTTM()%>
        </td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td style="text-align: center; padding: 15px; font-weight: bold;" colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>