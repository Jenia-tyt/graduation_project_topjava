<%@ page contentType="text/html;charset=UTF-8" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><ftm:setBundle basename="messeges.app"/><html><jsp:include page="fragments/headTag.jsp"/>    <body>    <jsp:include page="fragments/bodyHaeder.jsp"/>    <div class="jumbotron py-0">        <div class="pt-4"><%--            надо написать профль контроллер и сслыать туут на него --%>            <a class="btn btn-lg btn-success" href="Restaurant/register"><spring:message code="app.register"/> &raquo;</a><%--    здесь надо сделать что бы заходил как юзер--%>            <button type="submit" class="btn btn-lg btn-primary" onclick="login('user@mail.ru', 'passwordUser')">                <spring:message code="app.login"/> User            </button><%--    сдесь надо сдлеать что бы заходил как админ--%>            <button type="submit" class="btn btn-lg btn-primary" onclick="login('admin@mail.ru', 'passwordAdmin')">                <spring:message code="app.login"/> Admin            </button>        </div>        <div class="lead py-4"><spring:message code="app.stack"/><br>            <a href="http://projects.spring.io/spring-security/">Spring Security</a>,            <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,            <a href="http://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>,            <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security                Test</a>,            <a href="http://hibernate.org/orm/">Hibernate ORM</a>,            <a href="http://hibernate.org/validator/">Hibernate Validator</a>,            <a href="http://www.slf4j.org/">SLF4J</a>,            <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,            <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,            <a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,            <a href="http://tomcat.apache.org/">Apache Tomcat</a>,            <a href="http://www.webjars.org/">WebJars</a>,            <a href="http://datatables.net/">DataTables plugin</a>,            <a href="http://ehcache.org">EHCACHE</a>,            <a href="http://www.postgresql.org/">PostgreSQL</a>,            <a href="http://junit.org/">JUnit</a>,            <a href="http://jquery.com/">jQuery</a>,            <a href="http://ned.im/noty/">jQuery notification</a>,            <a href="http://getbootstrap.com/">Bootstrap</a>.        </div>    </div><%--    <img src="Restaurant/resources/images/icon.png" width="200" height="200" class="rounded-circle">--%>    <div class="container lead"><spring:message code="app.description"/></div>    <jsp:include page="fragments/footer.jsp"/>    <script type="text/javascript">        <c:if test="${not empty param.username}">        setCredentials("${param.username}", "");        </c:if>        function login(username, password) {            setCredentials(username, password);            $("#login_form").submit();        }        function setCredentials(username, password) {            $('input[name="username"]').val(username);            $('input[name="password"]').val(password);        }    </script>    </body></html>