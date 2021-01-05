<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark py-0">
    <div class="container">
        <a class="navbar-brand" href="Restaurant/login">
            <img src="Restaurant/resources/images/icon.png" width="32" height="32" class="rounded-circle">
            <spring:message code="app.title"/>
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">

                    <sec:authorize access="isAuthenticated()">
                        <form:form class="form-inline my-2" action="Restaurant/logout" method="post">
                            <sec:authorize access="hasRole('ADMIN')">
                                <a class="btn btn-info mr-1" href="/Restaurant/restaurant"><spring:message code="rest.title"/></a>

                                <a class="btn btn-info mr-1" href="/Restaurant/users"><spring:message code="users.title"/></a>

                            </sec:authorize>

<%--                            ссылка на профиль--%>
                            <a class="btn btn-info mr-1" href="Restaurant/profile"><spring:message code="app.user"/><sec:authentication property="principal.userTo.name"/></a>

                            <button class="btn btn-primary my-1" type="submit">
                                <span class="fa fa-sign-out"></span>
                            </button>
                        </form:form>
                    </sec:authorize>

                    <sec:authorize access="isAnonymous()">
                            <form id="login_form" class="form-inline my-2" action="${pageContext.request.contextPath}/spring_security_check" method="post">
                                <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                                <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                                <button class="btn btn-success" type="submit">
                                    <span class="fa fa-sign-in"></span>
                                </button>
                            </form>
                    </sec:authorize>

                </li>

                <li class="nav-item dropdown">
                    <a class="dropdown-toggle nav-link my-1 ml-2" data-toggle="dropdown">ru</a>
<%--                    локализация, надо сделать что бы работала локализация--%>
                    <div class="dropdown-menu">
<%--                        <a class="dropdown-item" href="Restaurant/login?lang=en">English</a>--%>
<%--                        <a class="dropdown-item" href="Restaurant/login?lang=ru">Русский</a>--%>
                            <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a>
                            <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    </div>

</nav>


