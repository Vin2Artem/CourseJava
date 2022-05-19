<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content='true' name='HandheldFriendly' />
    <meta content='width' name='MobileOptimized' />
    <meta content='yes' name='apple-mobile-web-app-capable' />
    <title><c:out value="${title}" default="Ошибка"/></title>
    <link rel="shortcut icon" href="pictures/logo.png" type="image/png">
</head>
<body>
    <span><c:out value="${message}" default="Непредвиденная ошибка. Повторите позднее"/></span>
    <a href="<c:out value="${redir}" default="javascript:history.back()"/>">Назад</a>
</body>
</html>
