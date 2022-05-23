<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">

<head>
    <noscript>
        <meta http-equiv="refresh" content="0;url=error?type=js&redir=/feedback">
    </noscript>
    <meta charset="UTF-8">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content='true' name='HandheldFriendly'/>
    <meta content='width' name='MobileOptimized'/>
    <meta content='yes' name='apple-mobile-web-app-capable'/>
    <title>Поиск</title>
    <link rel="shortcut icon" href="pictures/logo.png" type="image/png">
    <link rel="stylesheet" href="styles/general.css"/>
    <link rel="stylesheet" href="styles/main.css"/>
    <link rel="stylesheet" href="styles/form.css"/>
    <link rel="stylesheet" href="styles/search.css"/>
</head>

<style>
    .search_params {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

    .not_found {
        text-align: center;
        margin: 10px 0 20px;
    }

    .search_param {
        margin: 5px 10px;
    }

    .btn_positive {
        text-decoration: none;
        width: min-content;
        padding: 10px;
    }

    .course_title {
        text-align: center;
    }

    table p {
        text-indent: 1em;
    }

    table {
        width: 100%;
        padding: 0 15px;
    }

    tr {
        display: flex;
        line-height: 1.5;
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

    .btn_positive {
        width: 100px;
        text-align: center;
    }

    div.btn_positive {
        background: gray;
    }

    @media only screen and (max-width: 450px) {
        .main {
            padding: 0;
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
                <form class="search_form" action="<c:url value="/search" />" method="get">
                    <div class="search-fol">
                        <input id="inp_search1" type="text" class="search_input" placeholder="Введите текст для поиска"
                               name="search" value="<c:out value="${requestScope.search}"/>">
                        <label for="inp_search1"></label>
                        <button class="search_img" type="submit">
                            <img src="<c:url value="/pictures/search.png" />" alt="Search">
                        </button>
                    </div>
                    <div class="search_params">
                        <div class="search_param">
                            <c:if test="${requestScope.courseDesc == true}">
                                <input type="checkbox" name="courseDesc" id="courseDesc" checked>
                            </c:if>
                            <c:if test="${requestScope.courseDesc == false}">
                                <input type="checkbox" name="courseDesc" id="courseDesc">
                            </c:if>
                            <label for="courseDesc">По описанию курсов</label>
                        </div>
                        <div class="search_param">
                            <c:if test="${requestScope.lessonDesc == true}">
                                <input type="checkbox" name="lessonDesc" id="lessonDesc" checked>
                            </c:if>
                            <c:if test="${requestScope.lessonDesc == false}">
                                <input type="checkbox" name="lessonDesc" id="lessonDesc">
                            </c:if>
                            <label for="lessonDesc">По описанию уроков</label>
                        </div>
                    </div>
                </form>
                <c:if test="${requestScope.notFound == true}">
                    <div class="not_found bold">Ничего не найдено</div>
                </c:if>
                <table>
                    <tbody>
                    <c:forEach var="course" items="${requestScope.courses}">
                        <tr>
                            <td class="course_title bold">Курс "<c:out value="${course.name}"/>"</td>
                            <td>
                                <c:forEach var="string" items="${course.desc}">
                                    <p><c:out value="${string}"/></p>
                                </c:forEach>
                            </td>
                            <td class="course_go"><a
                                    href="<c:url value="/courses/" /><c:out value="${course.id}" />"
                                    class="btn_positive">Перейти</a></td>
                        </tr>
                    </c:forEach>
                    <c:forEach var="lesson" items="${requestScope.lessons}">
                        <tr>
                            <td class="course_title bold"><c:out value="${lesson.name}"/></td>
                            <td>
                                <p><c:out value="${lesson.desc}"/></p>
                            </td>
                            <c:if test="${lesson.isUnlocked(sessionScope.user) == true}">
                                <td class="lesson_go"><a
                                        href="<c:url value="/lessons/" /><c:out value="${lesson.id}" />"
                                        class="btn_positive">Открыть</a></td>
                            </c:if>
                            <c:if test="${lesson.isUnlocked(sessionScope.user) == false}">
                                <td class="lesson_go">
                                    <c:if test="${lesson.isLocked(sessionScope.user) == false}">
                                        <div class="btn_positive"><c:out value="${lesson.daysToUnlock}"/> дн.</div>
                                    </c:if>
                                    <c:if test="${lesson.isLocked(sessionScope.user) == true}">
                                        <div class="btn_positive">Закрыт</div>
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

<script>
    function validateForm() {
        if (document.querySelector('#inp_search1').value.trim() === "") {
            alert("Введите поисковый запрос.");
            return false;
        } else {
            return true;
        }
    }
</script>
</html>