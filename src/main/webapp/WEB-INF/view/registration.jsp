<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">

<head>
    <noscript>
        <meta http-equiv="refresh" content="0;url=error?type=js&redir=/registration">
    </noscript>
    <meta charset="UTF-8">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content='true' name='HandheldFriendly'/>
    <meta content='width' name='MobileOptimized'/>
    <meta content='yes' name='apple-mobile-web-app-capable'/>
    <title>Регистрация</title>
    <link rel="stylesheet" href="styles/general.css"/>
    <link rel="stylesheet" href="styles/style.css"/>
    <link rel="shortcut icon" href="pictures/logo.png" type="image/png">
    <script src="scripts/registration.js"></script>
</head>

<style>
    .documents {
        line-height: 1.5;
        text-align: center;
    }
</style>

<body>
<div class="content">
    <form action="" method="post" onsubmit="return validateForm();">
        <div class="container signup">
            <div>
                <img class="logo" src="<c:url value="/pictures/logo.png"/>" alt="Logo">
            </div>

            <h1>Регистрация</h1>
            <p class="documents">Регистрируясь на портале и используя любые его материалы, вы принимаете нашу<br>
                <a href="<c:url value="/policy"/>">Политику конфиденциальности</a>
            </p>

            <hr>

            <h2>Личные данные</h2>

            <label for="surname" class="bold">Фамилия *</label>
            <input type="text" placeholder="Петров" id="surname" name="surname" required>

            <label for="name" class="bold">Имя *</label>
            <input type="text" placeholder="Иван" id="name" name="name" required>

            <label for="patronymic" class="bold">Отчество</label>
            <input type="text" placeholder="Сергеевич" id="patronymic" name="patronymic">

            <label for="sexb" class="bold">Пол</label>
            <div class="select" id="sexb" tabindex="1">
                <input type="text" id="sex" name="sex" value="мужской" class="hidden"/>
                <label for="sex"></label>
                <span class="select_value">мужской</span>
                <ul class="select_dropdown">
                    <li class="select_option">мужской</li>
                    <li class="select_option">женский</li>
                </ul>
                <img class="arrow" src="pictures/arrow.png" alt="Max">
            </div>

            <label for="birth" class="bold">Дата рождения</label>
            <input type="date" min="1900-01-01" max="<c:out value="${requestScope.today}"/>" id="birth" name="birth"
                   required>

            <hr>

            <h2>Аккаунт</h2>

            <label for="email" class="bold">Электронная почта *</label>
            <input type="email" placeholder="something@mail.ru" id="email" name="email" class="pop_field" required>
            <span class="pop_msg">Формат адреса: something@mail.ru</span>

            <label for="pwd" class="bold">Пароль *</label>
            <input type="password" class="pwd pop_field" minlength="6" placeholder="qW1!eR2@tY3#" id="pwd" name="pwd"
                   required>
            <span class="pop_msg">Длина пароля должна быть > 6 символов</span>

            <label for="pwd-repeat" class="bold">Повторите пароль *</label>
            <input type="password" class="pwd_repeat pop_field" placeholder="qW1!eR2@tY3#" id="pwd-repeat"
                   name="pwd-repeat" required>
            <span class="pop_msg">Пароли не совпадают</span>

            <hr>

            <h2>Контакты и местоположение</h2>

            <label for="tel" class="bold">Телефон *</label>
            <input type="tel" class="mask pop_field" placeholder="+7 (___) ___-__-__" value="+7 (___) ___-__-__"
                   id="tel" name="tel" required>
            <span class="pop_msg">Формат номера: +7 (917) 100-20-30</span>

            <label for="city" class="bold">Город *</label>
            <input type="text" placeholder="Москва" id="city" name="city" required>

            <hr>

            <label for="answer" class="bold"><c:out value="${sessionScope.question}"/></label>
            <input type="text" maxlength="5" placeholder="123" id="answer" name="answer" required>

            <button type="submit" class="btn_positive">Зарегистрироваться</button>
        </div>

        <div class="container signin">
            <p>Уже есть аккаунт? <a href="login">Войти</a></p>
        </div>
    </form>
</div>
</body>

</html>
