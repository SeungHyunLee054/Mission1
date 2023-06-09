<%@ page import="dto.BookMark" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>북마크 목록</h1>
<%@ include file="top.jsp"%>
<br>
<table>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    <% List<BookMark> bookMarkList = (List<BookMark>) request.getAttribute("bookMarkList");%>
    <% if (bookMarkList != null && !bookMarkList.isEmpty()) {%>
    <% for (BookMark bookMark : bookMarkList) {%>
    <tr>
        <td>
            <%=bookMark.getId()%>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/bookmark-detail?id=<%=bookMark.getId()%>&distance=${distance}">
            <%=bookMark.getBookmark_group_name()%>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/detail?mgrNo=<%=bookMark.getMgr_no()%>&distance=${distance}">
            <%=bookMark.getWifi_name()%>
        </td>
        <td>
            <%=bookMark.getRegister_date()%>
        </td>
        <td style="text-align: center">
            <a href="${pageContext.request.contextPath}/bookmark-delete-detail?id=<%=bookMark.getId()%>">삭제</a>
        </td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td style="text-align: center; padding: 15px; font-weight: bold;" colspan="5">북마크가 없습니다.</td>
    </tr>
    <% } %>
</table>
</body>
</html>
