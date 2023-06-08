<%@ page import="dto.BookMarkGroup" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>북마크 그룹 삭제</h1>
<%@ include file="top.jsp" %>
<br>
북마크 그룹을 삭제하시겠습니까?
<% BookMarkGroup bookMarkGroup = (BookMarkGroup) request.getAttribute("bookMarkGroupData");%>
<form action="${pageContext.request.contextPath}/bookmark-group-delete" method="get">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td><input type="text" name="bookmarkGroupName" value="<%=bookMarkGroup.getBookmark_group_name()%>"></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><input type="text" name="order" value="<%=bookMarkGroup.getOrder()%>"></td>
        </tr>
        <tr>
            <td style="text-align: center" colspan="2">
                <a href="${pageContext.request.contextPath}/bookmark-group">돌아가기</a> |
                <input type="submit" value="삭제">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
