<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="Restaurant/resources/js/GP.common.js" defer></script>
<jsp:include page="fragments/bodyHaeder.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <%--@elvariable id=toUser" type="ru.restaurants.to.ToUser"--%>
        <div class="row">
            <div class="col-5 offset-3">
                <h3><spring:message code="profile.title"/> ${toUser.name}</h3>
<%--                надо изменить адрес обрабокти страницы--%>
                <form:form class="form-group"  method="post" modelAttribute="toUser" action="Restaurant/register"
                      charset="UTF-8" accept-charset="UTF-8">

                    <input type="text" hidden="hidden" name="role" id="role" value="USER">

                    <%--                    нужна валидация на 0--%>
                    <label for="name" class="col-form-label"><spring:message code="users.name"/></label>
                    <br>
                    <input name="name" id="name" class="form-control" value="${toUser.name}"/>
                    <br>

                    <label for="email" class="col-form-label"><spring:message code="users.email"/></label>
                    <br>
                    <input type="email" name="email" class="form-control ${status.error ? 'is-invalid' : '' }" id="email" placeholder="email" value="${toUser.email}"/>
                    <br>

                    <label for="password" class="col-form-label"><spring:message code="users.password"/></label>
                    <br>
                    <input type="password" name="password" class="form-control ${status.error ? 'is-invalid' : '' }" id="password" placeholder="password"/>
                    <br>

                    <label for="role" class="col-form-label"><spring:message code="users.role"/></label>
                    <input type="text" name="role" class="form-control" id="role" placeholder="role" value="${toUser.role.substring(1, toUser.role.length()-1)}" readonly/>
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
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>