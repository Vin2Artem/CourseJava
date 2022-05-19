<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content='true' name='HandheldFriendly'/>
    <meta content='width' name='MobileOptimized'/>
    <meta content='yes' name='apple-mobile-web-app-capable'/>
    <title>Обратная связь</title>
    <link rel="shortcut icon" href="pictures/logo.png" type="image/png">
    <link rel="stylesheet" href="styles/general.css"/>
    <link rel="stylesheet" href="styles/main.css"/>
    <link rel="stylesheet" href="styles/form.css"/>
    <link rel="stylesheet" href="styles/search.css"/>
</head>

<style>
    textarea {
        min-height: 154px;
    }

    @media only screen and (max-width: 450px) {
        .main {
            padding: 0;
        }
    }
</style>

<c:if test="${success == 1}">
    <script>
        alert("Успешно отправлено!");
    </script>
</c:if>

<body>
<div class="content">
    <jsp:include page="samples/aside.jsp" />
    <main>
        <jsp:include page="samples/header.jsp" />
        <div class="main_content">
            <div class="head_desc">
                <div class="welcome_text">
                    <div class="welcome_logo">
                        <a href="welcome">
                            <img class="welcome_img" src="pictures/logo.png" alt="Logo"/>
                        </a>
                    </div>
                    <div>
                        <h1>Upgrader</h1>
                        <p>Онлайн-школа по личностному развитию и финансам</p>
                    </div>
                </div>
                <div class="welcome_social">
                    <a href="https://youtube.com/" target="_blank">
                        <img src="pictures/youtube.png" alt="Social" class="social">
                    </a>
                </div>
            </div>
            <div class="main">
                <form action="" method="post">
                    <div class="container signup">
                        <label for="topic" class="bold">Тема</label>
                        <input type="text" placeholder="Вопрос, предложение или проблема" id="topic" name="topic" required>

                        <label for="desk" class="bold">Описание</label>
                        <textarea id="desk" name="desk" placeholder="Детали вашей ситуации" required></textarea>

                        <hr>

                        <label for="answer" class="bold"><c:out value="${question}"/></label>
                        <input type="text" maxlength="5" placeholder="123" id="answer" name="answer" required>

                        <button type="submit" class="registerbtn">Отправить</button>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="samples/footer.jsp" />
    </main>
</div>
</body>
</html>