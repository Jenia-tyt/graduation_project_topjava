<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/headTag.jsp"/>
<html>
<script type="text/javascript" src="resources/js/GP.common.js" defer></script>
<script type="text/javascript" src="resources/js/GP.restaurant.js" defer></script>
<body>
<jsp:include page="fragments/bodyHaeder.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="rest.title"/></h3>
        <br>

        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>

        <h3></h3>

        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="rest.name"/></th>
                <th><spring:message code="rest.rating"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<%--добавить datetimepicer--%>
<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="rest.add"/></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>

            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="rest.name"/></label>
                        <div class="form-group">
                            <input type="text" class="form-control" id="name" name="name" autocomplete="off"
                                   placeholder="<spring:message code="rest.name" />">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rating" class="col-form-label"><spring:message code="rest.rating"/></label>
                        <input type="number" class="form-control" id="rating" name="rating"
                               placeholder="<spring:message code="rest.rating"/>" readonly >
                    </div>

                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="saveForRest()">
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
