<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<header>
    <div class="account">
        <a href="exit" class="btn_account">
            <div class="exit_fol">
                <img class="firstimg" src="<c:url value="/pictures/arrow_white.png"/>" alt="Out">
                <img class="secondimg" src="<c:url value="/pictures/arrow.png"/>" alt="Out">
            </div>
            <span>Выйти</span>
        </a>
    </div>
    <div class="account right">
        <a href="<c:url value="/main/account"/>" class="btn_account">
            <span><c:out value="${sessionScope.user.email}"/></span>
        </a>
    </div>
</header>