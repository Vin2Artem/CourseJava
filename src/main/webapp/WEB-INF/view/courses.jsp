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
    <title>Обратная связь</title>
    <link rel="shortcut icon" href="<c:url value="/pictures/logo.png" />" type="image/png">
    <link rel="stylesheet" href="<c:url value="/styles/general.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/main.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/form.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/search.css" />"/>
</head>

<style>
    @media only screen and (max-width: 450px) {
        .main {
            padding: 0;
        }
    }

    thead {
        display: block;
        width: auto;
        text-transform: uppercase;
        text-align: left;
    }

    tr {
        display: flex;
        line-height: 1.5;
    }

    .lesson_name {
        width: 150px;
        flex: 0 0 auto;
    }

    .lesson_desk {
        width: 100%;
    }

    .btn_positive {
        margin: 0;
        padding: 10px;
        text-decoration: none;
    }

    td,
    th{
        padding: 15px 15px 15px 10px;
    }
</style>

<body>
<div class="content">
    <jsp:include page="samples/aside.jsp" />
    <main>
        <jsp:include page="samples/header.jsp" />
        <div class="main_content">
            <jsp:include page="samples/head_desk.jsp"/>
            <div class="main">
                <table>
                    <thead>
                    <tr>
                        <th class="lesson_name">Название</th>
                        <th class="lesson_desk">Краткое описание</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="lesson_name">Введение</td>
                        <td class="lesson_desk">Структура курса, формат его проведения и основные правила.</td>
                        <td class="lesson_go"><a href="barsik" class="btn_positive">Открыть</a></td>
                    </tr>
                    <tr>
                        <td class="lesson_name">Ставим цель</td>
                        <td class="lesson_desk">Правила постановки цели. Как получить максимальный результат от курса?</td>
                        <td class="lesson_go"><a href="barsik" class="btn_positive">Открыть</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <jsp:include page="samples/footer.jsp" />
    </main>
</div>
</body>
</html>