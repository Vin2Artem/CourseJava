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
    .nav_buttons {
        display: flex;
        padding-bottom: 20px;
    }

    h2 {
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
        margin: auto;
        padding: 10px;
        text-decoration: none;
        width: min-content;
        white-space: nowrap;
        text-align: center;
    }

    div.btn_positive {
        background: gray;
    }

    iframe {
        margin: auto;
        display: block;
        max-width: 560px;
        max-height: 315px;
        width: 90vw;
        height: 50vw;
    }

    @media only screen and (max-width: 750px) {
        .main {
            padding: 30px 0;
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
            <div class="nav_buttons">
                <a href="<c:url value="/lessons/" /><c:out value="${requestScope.prevId}"/>" class="btn_positive">< Пред.</a>
                <a href="<c:url value="/courses/" /><c:out value="${requestScope.courseId}"/>" class="btn_positive">Список уроков</a>
                <a href="<c:url value="/lessons/" /><c:out value="${requestScope.nextId}"/>" class="btn_positive">След. ></a>
            </div>
            <div class="main">
                <h2><c:out value="${requestScope.lesson.name}"/></h2>
                <div class="lesson_desc">
                    <c:out value="${requestScope.lesson.desc}"/>
                </div>
                <div>
                    <iframe src="<c:out value="${requestScope.lesson.url}"/>"
                            title="YouTube video player"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen></iframe>
                </div>
            </div>
        </div>
        <jsp:include page="samples/footer.jsp"/>
    </main>
</div>
</body>
</html>