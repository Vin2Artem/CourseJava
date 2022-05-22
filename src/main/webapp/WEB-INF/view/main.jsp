<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">

<head>
    <noscript>
        <meta http-equiv="refresh" content="0;url=error?type=js&redir=/main">
    </noscript>
    <meta charset="UTF-8">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content='true' name='HandheldFriendly'/>
    <meta content='width' name='MobileOptimized'/>
    <meta content='yes' name='apple-mobile-web-app-capable'/>
    <title>Главная</title>
    <link rel="shortcut icon" href="<c:url value="/pictures/logo.png"/>" type="image/png">
    <link rel="stylesheet" href="<c:url value="/styles/general.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/main.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/form.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/search.css" />"/>
</head>

<style>
    .btn_positive {
        margin: 10px auto 0;
        padding: 10px;
        text-decoration: none;
        text-align: center;
        line-height: 1.5;
        max-width: 250px;
        white-space: break-spaces;
    }
</style>

<body>
<div class="content">
    <jsp:include page="samples/aside.jsp"/>
    <main>
        <jsp:include page="samples/header.jsp"/>
        <div class="main_content">
            <jsp:include page="samples/head_desk.jsp"/>
            <div class="main">
                <a href="https://upgrader.com/" target="_blank">
                    <img src="<c:url value="/pictures/welcome.png" />" alt="Welcome">
                </a>
                <div class="desc">
                    <p><strong>Контакты:</strong></p>
                    <p><strong><a href="tel:+74951234567">+7 (495) 123-45-67</a></strong></p>
                    <p><strong><a href="mailto:mail@upgr.com">mail@upgr.com<br></a></strong></p>
                </div>

                <c:if test="${requestScope.last_lesson != null}">
                    <a href="<c:url value="/lessons/" /><c:out value="${requestScope.last_lesson}" />"
                            class="btn_positive">К последнему просмотренному уроку</a>
                </c:if>
            </div>
        </div>
        <jsp:include page="samples/footer.jsp"/>
    </main>
</div>
</body>
</html>