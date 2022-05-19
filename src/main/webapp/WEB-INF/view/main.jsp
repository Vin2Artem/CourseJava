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
    <title>Главная</title>
    <link rel="shortcut icon" href="pictures/logo.png" type="image/png">
    <link rel="stylesheet" href="styles/general.css"/>
    <link rel="stylesheet" href="styles/main.css"/>
    <link rel="stylesheet" href="styles/form.css"/>
    <link rel="stylesheet" href="styles/search.css"/>
</head>

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
                <a href="https://upgrader.com/" target="_blank">
                    <img src="pictures/welcome.png" alt="Welcome">
                </a>
                <div class="desc">
                    <strong>
                        <p>Контакты:</p>
                        <p><a href="tel:+74951234567">+7 (495) 123-45-67</a></p>
                        <p><a href="mailto:mail@upgr.com">mail@upgr.com<br></a></p>
                    </strong>
                </div>
            </div>
        </div>
        <jsp:include page="samples/footer.jsp" />
    </main>
</div>
</body>
</html>