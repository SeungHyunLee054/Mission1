<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>북마크 그룹 추가</h1>
<%@ include file="top.jsp" %>
<br>
<form action="${pageContext.request.contextPath}/bookmark-group-add" method="get">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td><input type="text" name="bookmarkGroupName"></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><input type="number" name="order" min="0" step="1"></td>
        </tr>
        <tr>
            <td style="text-align: center" colspan="2">
                <input type="submit" value="추가">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
