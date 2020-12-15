<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

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
                    <form id="login_form" class="form-inline my-2" method="post">
                        <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                        <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                        <button class="btn btn-success" type="submit">
                            <span class="fa fa-sign-in"></span>
                        </button>
                    </form>
                </li>

                <li class="nav-item dropdown">
                    <a class="dropdown-toggle nav-link my-1 ml-2" data-toggle="dropdown">ru</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/login?lang=en">English</a>
                        <a class="dropdown-item" href="/login?lang=ru">Русский</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    </div>

</nav>


