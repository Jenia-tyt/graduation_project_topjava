<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rest" tagdir="/WEB-INF/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/GP.common.js" defer></script>
<jsp:include page="fragments/bodyHaeder.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <%--@elvariable id="toUser" type="ru.restaurants.to.ToUser"--%>
        <div class="row">
            <div class="col-5 offset-3">
                <h3><spring:message code="profile.title"/> ${toUser.name}</h3>

                <form:form class="form-group"  method="post" modelAttribute="toUser" action="register/update"
                      charset="UTF-8" accept-charset="UTF-8">

                    <input type="number" name="id" id="id" hidden="hidden" value="${toUser.id()}">

                    <input type="date" hidden="hidden" id="voteLast" name="voteLast" value="${toUser.voteLast}">

                    <rest:inputField name="name" labelCode="users.name"/>
                    <rest:inputField name="email" labelCode="users.email" inputType="email"/>
                    <rest:inputField name="password" labelCode="users.password" inputType="password"/>

                    <label for="role" class="col-form-label"><spring:message code="users.role"/></label>
                    <input type="text" name="role" class="form-control" id="role" placeholder="role" value="${toUser.role.replaceAll('\\[|\\]','')}" readonly/>
                    <br>

                    <div class="text-right">
                        <a class="btn btn-secondary" onclick="location.href = 'users'">
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