<%@ page import="java.util.List" %>
<%@ page import="dto.LocationHistory" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/js/delete-history.js"></script>
</head>
<body>
    <h1>위치 히스토리 목록</h1>
    <%@ include file="/top.jsp" %>
    <br>
<table>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    <% List<LocationHistory> dataiList = (List<LocationHistory>) request.getAttribute("historyList");%>
    <% if (dataiList != null && !dataiList.isEmpty()) {%>
    <% for (LocationHistory wifiData : dataiList) {%>
    <tr>
        <td><%=wifiData.getId()%></td>
        <td><%=wifiData.getLAT()%></td>
        <td><%=wifiData.getLNT()%></td>
        <td><%=wifiData.getFIND_DATE()%></td>
        <td style="text-align: center"><input type="button" value="삭제" onclick="deleteHistory(<%=wifiData.getId()%>)"></td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td style="text-align: center; padding: 15px; font-weight: bold;" colspan="5">히스토리가 없습니다.</td>
    </tr>
    <% } %>
</table>
</body>
</html>
