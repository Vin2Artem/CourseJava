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
    <title>Аккаунт</title>
    <link rel="shortcut icon" href="<c:url value="/pictures/logo.png"/>" type="image/png">
    <link rel="stylesheet" href="<c:url value="/styles/form.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/style.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/general.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/main.css" />"/>
    <link rel="stylesheet" href="<c:url value="/styles/search.css" />"/>
</head>

<style>
    h1 {
        margin: 0;
        text-align: left;
    }

    p {
        margin: 0;
    }

    .logo {
        margin: unset;
        width: 100%;
        border-radius: unset;
    }

    .signup {
        padding: 20px;
    }

    .main {
        padding: 0 30px;
    }

    textarea {
        min-height: 154px;
    }

    .btn_positive {
        text-decoration: none;
        width: min-content;
        padding: 10px;
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

    @media only screen and (max-width: 450px) {
        .main {
            padding: 0;
        }
    }
</style>

<c:if test="${requestScope.success == true}">
    <script>
        alert("Успешно отредактировано!");
    </script>
</c:if>

<c:if test="${requestScope.success == false}">
    <script>
        alert("Что-то пошло не так!");
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
                <form action="" method="post" onsubmit="return validateForm();">
                    <div class="container signup">
                        <h2>Личные данные</h2>

                        <label for="surname" class="bold">Фамилия</label>
                        <input type="text" placeholder="Петров" id="surname" name="surname" value="<c:out value="${sessionScope.user.surname}"/>">

                        <label for="name" class="bold">Имя</label>
                        <input type="text" placeholder="Иван" id="name" name="name" value="<c:out value="${sessionScope.user.name}"/>">

                        <label for="patronymic" class="bold">Отчество</label>
                        <input type="text" placeholder="Сергеевич" id="patronymic" name="patronymic" value="<c:out value="${sessionScope.user.patronymic}"/>">

                        <hr>

                        <h2>Аккаунт</h2>

                        <label for="email" class="bold">Электронная почта</label>
                        <input type="email" placeholder="something@mail.ru" id="email" name="email" class="pop_field" value="<c:out value="${sessionScope.user.email}"/>">
                        <span class="pop_msg">Формат адреса: something@mail.ru</span>

                        <label for="curpwd" class="bold">Текущий пароль *</label>
                        <input class="curpwd" type="password" placeholder="qW1!eR2@tY3#" id="curpwd" name="curpwd" required>

                        <label for="pwd" class="bold">Новый пароль</label>
                        <input type="password" class="pwd pop_field" minlength="6" placeholder="qW1!eR2@tY3#" id="pwd" name="pwd">
                        <span class="pop_msg">Длина пароля должна быть > 6 символов</span>

                        <label for="pwd-repeat" class="bold">Повторите пароль</label>
                        <input type="password" class="pwd_repeat pop_field" placeholder="qW1!eR2@tY3#" id="pwd-repeat" name="pwd-repeat">
                        <span class="pop_msg">Пароли не совпадают</span>

                        <hr>

                        <h2>Контакты и местоположение</h2>

                        <label for="tel" class="bold">Телефон</label>
                        <input type="tel" class="mask pop_field" placeholder="+7 (___) ___-__-__"
                               id="tel" name="tel" value="<c:out value="${sessionScope.user.phone}"/>">
                        <span class="pop_msg">Формат номера: +7 (917) 100-20-30</span>

                        <label for="city" class="bold">Город</label>
                        <input type="text" placeholder="Москва" id="city" name="city" value="<c:out value="${sessionScope.user.city}"/>">

                        <hr>

                        <label for="answer" class="bold"><c:out value="${sessionScope.question}"/></label>
                        <input type="text" maxlength="5" placeholder="123" id="answer" name="answer" required>

                        <button type="submit" class="btn_positive">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="samples/footer.jsp"/>
    </main>
</div>
</body>

<script src="<c:url value="scripts/registration.js" />"></script>
<script>
    function validateForm() {
        if (document.querySelector('#surname').value === "" ||
            document.querySelector('#name').value === "" ||
            document.querySelector('input[type=email]').value === "" ||
            document.querySelector('input[type=tel]').value === "+7 (___) ___-__-__" ||
            document.querySelectorAll('.uncompleted').length !== 0 ||
            document.querySelector('.curpwd').value === "" ||
            (document.querySelector('.pwd').value !== "" && document.querySelector('.pwd_repeat').value === "") ||
            (document.querySelector('.pwd_repeat').value !== "" && document.querySelector('.pwd').value === "")) {
            alert("Не все поля формы корректно заполнены. Заполните все поля в соответствии с форматом.");
            return false;
        } else {
            return true;
        }
    }
</script>
</html>