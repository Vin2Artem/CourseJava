<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<aside>
    <div class="d-logo">
        <a href="main">
            <img class="logo" src="<c:url value="/pictures/logo.png" />" alt="Logo"/>
        </a>
    </div>
    <nav>
        <div class="menu">
            <label id="lbl_menu" for="chb_menu"></label>
            <input type="checkbox" class="hidden" id="chb_menu">
            <img src="<c:url value="/pictures/menu.png" />" class="menu_img" alt="Menu">
            <ul class="courses">
                <li>
                    <div class="course">
                        <div class="search">
                            <div class="search-fol">
                                <input id="inp_search" type="text" class="search_input"
                                       placeholder="Введите текст для поиска">
                                <label for="inp_search"></label>
                                <div class="search_img">
                                    <img src="<c:url value="/pictures/search.png" />" alt="Search">
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="course">
                        <a href="<c:url value="/courses/1" />" class="course_name">
                            <span>Курс "Первые шаги"</span>
                            <div class="course_fol">
                                <img class="firstimg" src="<c:url value="/pictures/arrow_white.png" />" alt="Go">
                                <img class="secondimg" src="<c:url value="/pictures/arrow.png" />" alt="Go">
                            </div>
                        </a>
                    </div>
                </li>
                <li>
                    <div class="course">
                        <a href="<c:url value="/courses/1" />" class="course_name">
                            <span>Курс "Прорыв"</span>
                            <div class="course_fol">
                                <img class="firstimg" src="<c:url value="/pictures/arrow_white.png" />" alt="Go">
                                <img class="secondimg" src="<c:url value="/pictures/arrow.png" />" alt="Go">
                            </div>
                        </a>
                    </div>
                </li>
                <li>
                    <div class="course">
                        <a href="<c:url value="/courses/1" />" class="course_name">
                            <span>Расписание ZOOM'ов</span>
                            <div class="course_fol">
                                <img class="firstimg" src="<c:url value="/pictures/arrow_white.png" />" alt="Go">
                                <img class="secondimg" src="<c:url value="/pictures/arrow.png" />" alt="Go">
                            </div>
                        </a>
                    </div>
                </li>
                <!--<li>
                    <div class="course">
                        <a href="<c:url value="/courses/1" />" class="course_name">
                            <span>Наши книги</span>
                            <div class="course_fol">
                                <img class="firstimg" src="pictures/arrow_white.png">
                                <img class="secondimg" src="pictures/arrow.png">
                            </div>
                        </a>
                    </div>
                </li>-->
                <li>
                    <div class="course">
                        <a href="<c:url value="/courses/1" />" class="course_name">
                            <span>О школе "Upgrader"</span>
                            <div class="course_fol">
                                <img class="firstimg" src="<c:url value="/pictures/arrow_white.png" />" alt="Go">
                                <img class="secondimg" src="<c:url value="/pictures/arrow.png" />" alt="Go">
                            </div>
                        </a>
                    </div>
                </li>
                <li>
                    <div class="course">
                        <a href="<c:url value="/courses/1" />" class="course_name">
                            <span>Контакты</span>
                            <div class="course_fol">
                                <img class="firstimg" src="<c:url value="/pictures/arrow_white.png" />" alt="Go">
                                <img class="secondimg" src="<c:url value="/pictures/arrow.png" />" alt="Go">
                            </div>
                        </a>
                    </div>
                </li>
                <li>
                    <div class="course">
                        <a href="<c:url value="/courses/1" />" class="course_name">
                            <span>F.A.Q.</span>
                            <div class="course_fol">
                                <img class="firstimg" src="<c:url value="/pictures/arrow_white.png" />" alt="Go">
                                <img class="secondimg" src="<c:url value="/pictures/arrow.png" />" alt="Go">
                            </div>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</aside>