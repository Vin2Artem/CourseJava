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
    <title><c:out value="${requestScope.lesson.name}"/></title>
    <link rel="shortcut icon" href="<c:url value="/pictures/logo.png" />" type="image/png">
    <link rel="stylesheet" href="<c:url value="/styles/general.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/main.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/form.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/search.css" />"/>
</head>

<style>
    .main {
        padding-top: 0;
    }

    h2 {
        padding-top: 25px;
        text-align: center;
    }

    .lesson_desc {
        margin: 15px;
        text-indent: 1em;
        line-height: 1.5;
    }

    .lesson_desc > p {
        margin: 7px 0;
    }

    .btn_positive {
        margin: 0;
        padding: 10px;
        text-decoration: none;
        width: 100px;
        text-align: center;
    }

    div.btn_positive {
        background: gray;
    }

    td,
    th {
        padding: 15px;
    }

    @media only screen and (max-width: 450px) {
        .main {
            padding: 0;
        }
    }

    @media only screen and (max-width: 750px) {
        .main {
            padding: 0;
        }

        .btn_positive {
            margin: 3px auto 8px;
        }
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
                <h2><c:out value="${requestScope.lesson.name}"/></h2>
                <div class="lesson_desc">
                    <c:out value="${requestScope.lesson.desc}"/>
                </div>

            </div>
        </div>
        <jsp:include page="samples/footer.jsp"/>
    </main>
</div>
</body>
</html>