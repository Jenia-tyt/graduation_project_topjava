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
            <th><spring:message code="menu.nameRest"/></th>
            <th><spring:message code="menu.data"/></th>
            <th><spring:message code="menu.menu"/></th>
            <th><spring:message code="menu.rating"/></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach var="menu" items="${menu}">
            <jsp:useBean id="menu" scope="page" type="ru.restaurants.model.Menu"/>
            <tr>
                <td>
                    <a href="Restaurant/rest/profile/restaurant?id=${menu.rest.id()}&login=<%=request.getParameter("login")%>">
                            ${menu.rest.name}
                    </a>
                </td>
                <td>${menu.dateMenu}</td>
                <td>${menu.menuRest}</td>
                <td>${menu.rating}</td>
                <td>
                    <form method="get" action="Restaurant/rest/profile/vote/${menu.id()}">
                        <button type="submit" value="${menu.rest.id()}">
                            <spring:message code="common.vote"/>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

