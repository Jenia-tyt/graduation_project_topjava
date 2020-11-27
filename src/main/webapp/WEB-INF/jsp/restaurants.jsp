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
                        </tr>
                    </thead>
                    <c:forEach var="restaurant" items="${rest}">
                        <jsp:useBean id="restaurant" scope="page" type="ru.restaurants.model.Restaurant"/>
                            <tr>
                                <td><%=restaurant.getName()%></td>
                            </tr>
                    </c:forEach>
                </table>
            </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
