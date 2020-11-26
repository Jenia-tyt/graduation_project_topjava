<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<header>
    <spring:message code="app.headName"/><br>
    <spring:message code="app.headLink"/>
    <a href="https://github.com/Jenia-tyt/graduation_project_topjava">github</a>
    <base href="${pageContext.request.contextPath}/"/>
</header>

