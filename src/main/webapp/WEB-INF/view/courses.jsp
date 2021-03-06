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
    <title><c:out value="${requestScope.course.name}"/></title>
    <link rel="shortcut icon" href="<c:url value="/pictures/logo.png" />" type="image/png">
    <link rel="stylesheet" href="<c:url value="/styles/general.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/main.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/form.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/search.css" />"/>
</head>

<style>
    h2 {
        text-align: center;
    }

    .desc_edit {
        font-size: 16px;
        line-height: 1.5;
        min-height: 250px;
        margin: 15px;
        min-width: calc(100% - 30px);
        max-width: calc(100% - 30px);
    }

    button[type="submit"] {
        margin: 0 auto;
    }

    .course_desc {
        margin: 15px;
        text-indent: 1em;
        line-height: 1.5;
    }

    .course_desc > p {
        margin: 7px 0;
    }

    table {
        width: 100%;
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
    th {
        padding: 15px;
    }

    @media only screen and (max-width: 750px) {
        .main {
            padding: 30px 0;
        }

        table {
            padding: 0 15px;
        }

        thead {
            display: none;
        }

        tr {
            flex-direction: column;
        }

        th, td {
            padding: 3px 0;
        }

        .lesson_name {
            width: 100%;
            text-align: center;
            font-weight: 700;
        }

        .btn_positive {
            margin: 3px auto 8px;
        }
    }
</style>

<c:if test="${requestScope.success == true}">
    <script>
        alert("?????????????? ??????????????????????????????!");
    </script>
</c:if>

<c:if test="${requestScope.success == false}">
    <script>
        alert("??????-???? ?????????? ???? ??????!");
    </script>
</c:if>

<body>
<div class="content">
    <jsp:include page="samples/aside.jsp"/>
    <main>
        <jsp:include page="samples/header.jsp"/>
        <div class="main_content">
            <jsp:include page="samples/head_desk.jsp"/>
            <div class="main">
                <h2>???????? "<c:out value="${requestScope.course.name}"/>"</h2>
                <c:if test="${sessionScope.user.editor == true}">
                    <form action="" method="post">
                    <textarea name="desc" id="area_desk" class="desc_edit"><c:forEach var="string"
                                                                                      items="${requestScope.course.desc}"><c:out
                            value="${string}"/></c:forEach></textarea>
                        <label for="area_desk"></label>
                        <button type="submit" class="btn_positive">??????????????????</button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.user.editor == false}">
                    <div class="course_desc">
                        <c:forEach var="string" items="${requestScope.course.desc}">
                            <p><c:out value="${string}"/></p>
                        </c:forEach>
                    </div>
                </c:if>
                <table>
                    <thead>
                    <tr>
                        <th class="lesson_name">????????</th>
                        <th class="lesson_desc">?????????????? ????????????????</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="lesson" items="${requestScope.lessons}">
                        <tr>
                            <td class="lesson_name"><c:out value="${lesson.name}"/></td>
                            <td class="lesson_desc"><c:out value="${lesson.desc}"/></td>
                            <c:if test="${lesson.isUnlocked(sessionScope.user) == true}">
                                <td class="lesson_go"><a
                                        href="<c:url value="/lessons/" /><c:out value="${lesson.id}" />"
                                        class="btn_positive">??????????????</a></td>
                            </c:if>
                            <c:if test="${lesson.isUnlocked(sessionScope.user) == false}">
                                <td class="lesson_go">
                                    <c:if test="${requestScope.courseLocked == false}">
                                        <div class="btn_positive"><c:out value="${lesson.daysToUnlock}"/> ????.</div>
                                    </c:if>
                                    <c:if test="${requestScope.courseLocked == true}">
                                        <div class="btn_positive">????????????</div>
                                    </c:if>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <jsp:include page="samples/footer.jsp"/>
    </main>
</div>
</body>
</html>