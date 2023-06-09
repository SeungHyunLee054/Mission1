<%@ page import="dto.Wifi" %>
<%@ page import="dto.BookMarkGroup" %>
<%@ page import="dto.BookMark" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>와이파이 상세정보</h1>
<%@ include file="/top.jsp" %>
<br>
<table>
    <% Wifi wifi = (Wifi) request.getAttribute("wifiData");%>
    <% BookMarkGroup bookMarkGroup = (BookMarkGroup) request.getAttribute("bookMarkGroupData");%>
    <% BookMark bookMark = (BookMark) request.getAttribute("bookMarkData");%>
    <tr>
        <th>북마크 id</th>
        <td><%=bookMark.getId()%>
        </td>
    </tr>
    <tr>
        <th>북마크 등록일자</th>
        <td><%=bookMark.getRegister_date()%>
        </td>
    </tr>
    <tr>
        <th>북마크 그룹 id</th>
        <td><%=bookMarkGroup.getId()%>
        </td>
    </tr>
    <tr>
        <th>북마크 그룹 이름</th>
        <td><%=bookMarkGroup.getBookmark_group_name()%>
        </td>
    </tr>
    <tr>
        <th>북마크 그룹 순서</th>
        <td><%=bookMarkGroup.getOrder()%>
        </td>
    </tr>
    <tr>
        <th>북마크 그룹 등록일자</th>
        <td><%=bookMarkGroup.getRegister_date()%>
        </td>
    </tr>
    <tr>
        <th>북마크 그룹 수정일자</th>
        <td><%=bookMarkGroup.getEdit_date() != null ? bookMarkGroup.getEdit_date() : ""%>
        </td>
    </tr>
    <tr>
        <th>거리(Km)</th>
        <td><%=String.format("%.4f", wifi.getDistance())%>
        </td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td><%=wifi.getX_SWIFI_MGR_NO()%>
        </td>
    </tr>
    <tr>
        <th>자치구</th>
        <td><%=wifi.getX_SWIFI_WRDOFC()%>
        </td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%=wifi.getX_SWIFI_MAIN_NM()%>
        </td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%=wifi.getX_SWIFI_ADRES1()%>
        </td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%=wifi.getX_SWIFI_ADRES2()%>
        </td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%=wifi.getX_SWIFI_INSTL_FLOOR() != null ? wifi.getX_SWIFI_INSTL_FLOOR() : ""%>
        </td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%=wifi.getX_SWIFI_INSTL_TY()%>
        </td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%=wifi.getX_SWIFI_INSTL_MBY()%>
        </td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%=wifi.getX_SWIFI_SVC_SE()%>
        </td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%=wifi.getX_SWIFI_CMCWR()%>
        </td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%=wifi.getX_SWIFI_CNSTC_YEAR()%>
        </td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%=wifi.getX_SWIFI_INOUT_DOOR()%>
        </td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><%=wifi.getX_SWIFI_REMARS3()%>
        </td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td><%=wifi.getLAT()%>
        </td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td><%=wifi.getLNT()%>
        </td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td><%=wifi.getWORK_DTTM()%>
        </td>
    </tr>
</table>
</body>
</html>
