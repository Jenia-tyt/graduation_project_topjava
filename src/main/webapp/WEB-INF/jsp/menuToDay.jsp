<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>
<html>
<body>
<jsp:include page="fragments/bodyHaeder.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="menu.title"/></h3>
        <br>


        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>


        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="menu.nameRest"/></th>
                <th><spring:message code="menu.menu"/></th>
                <th><spring:message code="menu.rating"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

