<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="account">
        <a href="exit" class="btn_account">
            <div class="exit_fol">
                <img class="firstimg" src="pictures/arrow_white.png">
                <img class="secondimg" src="pictures/arrow.png">
            </div>
            <span>Выйти</span>
        </a>
    </div>
    <div class="account right">
        <a href="main/account" class="btn_account">
            <span><c:out value="${user.email}"/></span>
        </a>
    </div>
</header>