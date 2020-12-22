<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>
<html>
<body>
<script type="text/javascript" src="Restaurant/resources/js/GP.common.js" defer></script>
<script type="text/javascript" src="Restaurant/resources/js/GP.menusOfRest.js" defer></script>
<jsp:include page="fragments/bodyHaeder.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <%--        сюда надо добьавить название ресторана--%>
            <h3 class="text-center"><spring:message code="rest.allMenu"/></h3>
        <br>

        <button class="btn btn-primary" onclick="addNewMenu()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>

        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="menu.menu"/></th>
                <th><spring:message code="rest.rating"/></th>
                <th><spring:message code="rest.data"/></th>
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
                    <input type="hidden" id="id_rest" name="id_rest">

                    <div class="form-group">
                        <label for="dateMenu" class="col-form-label"><spring:message code="menu.data"/></label>
                        <div class="form-group">
                            <input type="date" th:value="*{date}" th:field="*{date}" class="form-control" id="dateMenu" name="dateMenu" autocomplete="off"
                                   placeholder="<spring:message code="menu.data" />">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="menuRest" class="col-form-label"><spring:message code="menu.menu"/></label>
                        <input type="text" class="form-control" id="menuRest" name="menuRest"
                               placeholder="<spring:message code="menu.menu"/>">
                    </div>

                    <div class="form-group">
                        <label for="rating" class="col-form-label"><spring:message code="menu.rating"/></label>
                        <input type="number" class="form-control" id="rating" name="rating"
                               placeholder="<spring:message code="menu.rating"/>">
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
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
