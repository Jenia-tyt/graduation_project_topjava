<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="Restaurant/resources/js/GP.common.js" defer></script>
<jsp:include page="fragments/bodyHaeder.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
<%--        @elvariable id="ToUser" type="ru.restaurants.to.ToUser"--%>
        <div class="row">
            <div class="col-5 offset-3">
                <h3> <spring:message code="common.registered"/></h3>
                <form class="form-group"  method="post" modelAttribute="ToUser" action="Restaurant/register"
                      charset="UTF-8" accept-charset="UTF-8">

                    <input type="text" hidden="hidden" name="role" id="role" value="USER">

<%--                    нужна валидация на 0--%>
                    <label for="name" class="col-form-label"><spring:message code="users.name"/></label>
                    <br>
                    <input type="text" name="name" id="name" class="form-control ${status.error ? 'is-invalid' : '' }" placeholder="name"/>
                    <br>

                    <label for="email" class="col-form-label"><spring:message code="users.email"/></label>
                    <br>
                    <input type="email" name="email" class="form-control ${status.error ? 'is-invalid' : '' }" id="email" placeholder="email"/>
                    <br>

                    <label for="password" class="col-form-label"><spring:message code="users.password"/></label>
                    <br>
                    <input type="password" name="password" class="form-control ${status.error ? 'is-invalid' : '' }" id="password" placeholder="password"/>
                    <br>

                <div class="text-right">
                    <a class="btn btn-secondary" onclick="window.history.back()">
                        <span class="fa fa-close"></span>
                        <spring:message code="common.cancel"/>
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <span class="fa fa-check"></span>
                        <spring:message code="common.save"/>
                    </button>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>