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
            <c:forEach items="${menu}" var="menu">
                <jsp:useBean id="menu" scope="page" type="ru.restaurants.model.Menu"/>
                    <tr>
                        <td>${menu.rest.name}</td>
                        <td>${menu.menuRest}</td>
                        <td>${menu.rating}</td>
                    </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%--форма редактирования меню ее нужно привести в поорядок и повесить на кнопку--%>
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

                    <div class="form-group">
                        <label for="dateTime" class="col-form-label"><spring:message code="app.title"/></label>
                        <input class="form-control" id="dateTime" name="dateTime" autocomplete="off"
                               placeholder="<spring:message code="app.title"/>">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message
                                code="app.title"/></label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="app.title"/>">
                    </div>

                    <div class="form-group">
                        <label for="calories" class="col-form-label"><spring:message code="app.title"/></label>
                        <input type="number" class="form-control" id="calories" name="calories" placeholder="1000">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="app.title"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="app.title"/>
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>

<script type="text/javascript">
    var UI_URl = '/profile/menuToDay/';
    var oTable_dateTable;
    var oTable_dateTable_parms;

    $(function (){
        oTable_dateTable = $('#datatable');
        oTable_dateTable_parms = {
            "bPaginate":false,
            "bInfo":false,
            "aoColums":[
                {
                    "mData" : "name"
                },
                {
                    "mData" : "menuRest"
                },
                {
                    "mData" : "rating"
                },
                {
                    "orderable": false,
                    "defaultContent": ""
                },
                {
                    "orderable": false,
                    "defaultContent": ""
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        };
        oTable_dateTable.dataTable(oTable_dateTable_parms);
        // makeEditable;
    })
</script>

</html>

