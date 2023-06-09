<%@ page import="dto.BookMarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>북마크 그룹</h1>
<%@ include file="top.jsp" %>
<br>
<form action="bookmark-group-add.jsp">
    <input type="submit" value="북마크 그룹 이름 추가">
</form>
<table>
    <tr>
        <th>ID</th>
        <th>북마크 그룹 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    <% List<BookMarkGroup> bookMarkGroupList = (List<BookMarkGroup>) request.getAttribute("bookMarkGroupList");%>
    <% if (bookMarkGroupList != null && !bookMarkGroupList.isEmpty()) {%>
    <% for (BookMarkGroup bookMarkGroup : bookMarkGroupList) {%>
    <tr>
        <td>
            <%=bookMarkGroup.getId()%>
        </td>
        <td>
            <%=bookMarkGroup.getBookmark_group_name()%>
        </td>
        <td>
            <%=bookMarkGroup.getOrder()%>
        </td>
        <td>
            <%=bookMarkGroup.getRegister_date()%>
        </td>
        <td>
            <%=bookMarkGroup.getEdit_date() != null ? bookMarkGroup.getEdit_date() : ""%>
        </td>
        <td style="text-align: center">
            <a href="bookmark-group-edit-detail?id=<%=bookMarkGroup.getId()%>">수정</a>
            <a href="${pageContext.request.contextPath}/bookmark-group-delete-detail?id=<%=bookMarkGroup.getId()%>">삭제</a>
        </td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td style="text-align: center; padding: 15px; font-weight: bold;" colspan="6">북마크 그룹이 없습니다.</td>
    </tr>
    <% } %>
</table>
</body>
</html>
