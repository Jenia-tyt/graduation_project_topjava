<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>
<html>
<body>
<jsp:include page="fragments/bodyHaeder.jsp"/>
<q><spring:message code="app.user"/><%=request.getParameter("login")%></q>
<div>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="rest.name"/></th>
            <th><spring:message code="rest.data"/></th>
            <th><spring:message code="rest.menu"/></th>
            <th><spring:message code="rest.rating"/></th>
        </tr>
        </thead>
        <c:forEach var="menu" items="${menu}">
            <jsp:useBean id="menu" scope="page" type="ru.restaurants.model.Menu"/>
            <tr>
                <td>${menu.rest.name}</td>
                <td>${menu.dateMenu}</td>
                <td>${menu.menuRest}</td>
                <td>${menu.rest.rating}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
