<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>
<html>
<body>
<script type="text/javascript" src="Restaurant/resources/js/GP.common.js" defer></script>
<script type="text/javascript" src="Restaurant/resources/js/GP.users.js" defer></script>
<jsp:include page="fragments/bodyHaeder.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <%--        сюда надо добьавить название ресторана--%>
        <h3 class="text-center"><spring:message code="users.title"/></h3>
        <br>


        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="users.login"/></th>
                <th><spring:message code="users.email"/></th>
                <th><spring:message code="users.role"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>

            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <input type="date" hidden="hidden" th:value="*{date}" th:field="*{date}" id="voteLast" name="voteLast">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="users.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="users.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="email" class="col-form-label"><spring:message code="users.email"/></label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="<spring:message code="users.email"/>">
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-form-label"><spring:message code="users.password"/></label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="<spring:message code="users.password"/>">
                    </div>

                    <div class="form-group">
                        <label for="role" class="col-form-label"><spring:message code="users.role"/></label>
                        <select type="text" class="form-control" id="role"  name="role">
                            <option value="USER">USER</option>
                            <option value="ADMIN">ADMIN</option>
                            <option value="ADMIN.USER">USER,ADMIN</option>
                        </select>
                    </div>

                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="saveUser()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>

        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="menu"/>
</jsp:include>
</html>
