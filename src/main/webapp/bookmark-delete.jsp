<%@ page import="dto.BookMark" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>북마크 삭제</h1>
<%@ include file="top.jsp" %>
<br>
북마크를 삭제하시겠습니까?
<br>
<br>
<% BookMark bookMark = (BookMark) request.getAttribute("bookMarkData"); %>
<form action="${pageContext.request.contextPath}/bookmark-delete" method="get">
    <input type="hidden" name="id" value="<%=bookMark.getId()%>">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td>
                <%=bookMark.getBookmark_group_name()%>
            </td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td>
                <%=bookMark.getWifi_name()%>
            </td>
        </tr>
        <tr>
            <th>등록일자</th>
            <td>
                <%=bookMark.getRegister_date()%>
            </td>
        </tr>
        <tr>
            <td style="text-align: center" colspan="2">
                <a href="${pageContext.request.contextPath}/bookmark-list">돌아가기</a> |
                <input type="submit" value="삭제">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
