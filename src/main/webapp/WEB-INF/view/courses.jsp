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

    h2 {
        text-align: center;
    }

    .course_desc {
        margin: 15px;
        text-indent: 1em;
        line-height: 1.5;
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
        width: 160px;
        flex: 0 0 auto;
        word-break: break-word;
    }

    .lesson_desc {
        width: 100%;
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
    th{
        padding: 15px;
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
                <h2>Название курса</h2>
                <div class="course_desc">
                    <p>Описание курса</p>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th class="lesson_name">Урок</th>
                        <th class="lesson_desc">Краткое описание</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="lesson" items="${requestScope.lessons}">
                        <tr>
                            <td class="lesson_name"><c:out value="${lesson.name}" /></td>
                            <td class="lesson_desc"><c:out value="${lesson.desc}" /></td>
                            <c:if test="${lesson.daysToUnlock <= 0}">
                                <td class="lesson_go"><a href="<c:url value="/lessons/" /><c:out value="${lesson.id}" />" class="btn_positive">Открыть</a></td>
                            </c:if>
                            <c:if test="${lesson.daysToUnlock > 0}">
                                <td class="lesson_go"><div class="btn_positive"><c:out value="${lesson.daysToUnlock}" /> дн.</div></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <jsp:include page="samples/footer.jsp" />
    </main>
</div>
</body>
</html>