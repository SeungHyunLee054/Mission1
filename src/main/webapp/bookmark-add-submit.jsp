<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<script>
    alert("북마크 정보를 추가하였습니다.");
    location.href="${pageContext.request.contextPath}/bookmark-list?distance="+${distance};
</script>
</body>
</html>
