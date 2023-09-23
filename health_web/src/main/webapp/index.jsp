<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}
</body>
</html>
