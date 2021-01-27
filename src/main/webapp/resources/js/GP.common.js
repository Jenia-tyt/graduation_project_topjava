    var form;
    var URL_ADMIN = '/admin/menuToDay';

    $.ajaxSetup({cache: false});

    function makeEditable(datatableOpts) {
        ctx.datatableApi = $("#datatable").DataTable(
            $.extend(true, datatableOpts,
                {
                    "ajax": {
                        "url": ctx.ajaxUrl,
                        "dataSrc": ""
                    },
                    "paging": true,
                    "info": true,
                    "language": {
                        "info":             i18n["common.info"],
                        "search":           i18n["common.search"],
                        "lengthMenu":       i18n["common.lengthMenu"],
                        "loadingRecords":   i18n["common.loadingRecords"],
                        "processing":       i18n["common.processing"],
                        "sZeroRecords":     i18n["common.sZeroRecords"],
                        "paginate": {
                            "next":         i18n["common.paginate.next"],
                            "previous":     i18n["common.paginate.previous"]},
                    }
                }
            ));

        form = $('#detailsForm');
        $(document).ajaxError(function (event, jqXHR, options, jsExc) {
            failNoty(jqXHR);
        });

        $.ajaxSetup({cache: false});

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }

    function failNoty(jqXHR) {
        closeNoty();
        var errorInfo = jqXHR.responseJSON;
        failedNote = new Noty({
            text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + errorInfo.typeMessage + "<br>" + errorInfo.details.join("<br>"),
            type: "error",
            layout: "bottomRight"
        }).show();
    }

    function updateTableByData(data) {
        ctx.datatableApi.clear().rows.add(data).draw();
    }

    function save(){
        $.ajax({
            type: "POST",
            url: URL_ADMIN,
            data: form.serialize(),
            success: function (data){
                $('#editRow').modal('hide');
                ctx.updateTable();
                successNoty("common.saved")
            }
        })
    }

    function add(){
        $("#modalTitle").html(i18n["addTitle"]);
        form.find(":input").val("");
        $("#editRow").modal();
    }

    function addNewMenu(){
        $("#modalTitle").html(i18n["addTitle"]);
        form.find(":input").val("");
        form.find("input[name='" + "id_rest" + "']").val(localStorage.getItem('idRest'));
        $("#editRow").modal();
    }

    var failedNote;

    function closeNoty() {
        if (failedNote) {
            failedNote.close();
            failedNote = undefined;
        }
    }

    function successNoty(key) {
        closeNoty();
        new Noty({
            text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + i18n[key],
            type: 'success',
            layout: "bottomRight",
            timeout: 1000
        }).show();
    }

    function renderEditBtn(data, type, row) {
        if (type === "display") {
            return "<a onclick='updateRow(" + row.id + ","+ row.rest.id +");'><span class='fa fa-pencil'></span></a>";
        }
    }

    function updateRow(id, id_rest) {
        form.find(":input").val("");
        $("#modalTitle").html(i18n["editTitle"]);
        $.get('/profile/menuToDay/' + id, function (data) {
            $.each(data, function (key, value) {
                if (key === 'dateMenu'){
                    let date = new Date(value).toLocaleString().substring(0,10).split('.').reverse().join("-");
                    form.find("input[name='" + "dateMenu" + "']").val(date);
                }else {
                    form.find("input[name='" + key + "']").val(value);
                }
            });
            form.find("input[name='" + "id_rest" + "']").val(id_rest);
            $('#editRow').modal();
        });
    }

    function renderDeleteBtn(data, type, row) {
        if (type === "display") {
            return "<a onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></a>";
        }
    }

    function deleteRow(id) {
        if (confirm(i18n['common.confirm'])) {
            $.ajax({
                url: URL_ADMIN + "/" + id,
                type: "DELETE"
            }).done(function () {
                ctx.updateTable();
                successNoty("common.deleted");
            });
        }
    }

    function renderVoteBtn(date, type, row){
        if (type === "display") {
            return "<a onclick='vote(" + row.id + ")'><span class='btn btn-primary'><img src='resources/images/icon2.png' width=30 height=30></span></a>";
        }
    }

    function vote(id) {
        $.ajax({
            type:"PUT",
            url:URL_USER_MENU + "vote/"+id
        }).done(function () {
            ctx.updateTable();
        })
    }

    var idRest;
    function renderRestaurant(data, type, row) {
        if (type === "display") {
            return row.rest.name
        }
    }



    function renderRestaurantRating(data, type, row) {
        if (type === "display") {
            return row.rest.rating
        }
    }


    function set(idRest) {
        localStorage.setItem("idRest", idRest);
    }

    // ==================== USERS+++++++++++++++++++++ наверно надо вынести в отдельный файл так как получается много дублируеющего кода


    function renderEditBtnForUser(data, type, row) {
        if (type === "display") {
            return "<a onclick='updateRowForUser(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
        }
    }

    function renderDeleteBtnForUsers(data, type, row) {
        if (type === "display") {
            return "<a onclick='deleteRowForUser(" + row.id + ");'><span class='fa fa-remove'></span></a>";
        }
    }

    function deleteRowForUser(id) {
        if (confirm(i18n['common.confirm'])) {
            $.ajax({
                url: '/admin/users/' + id,
                type: "DELETE"
            }).done(function () {
                ctx.updateTable();
                successNoty("common.deleted");
            });
        }
    }

    function updateRowForUser(id) {
        form.find(":input").val("");
        $("#modalTitle").html(i18n["editUser"]);
        $.get('/admin/users/' + id, function (data) {
            $.each(data, function (key, value) {
                if (key === "role") {
                    let str = value.sort().join('.');
                    form.find("select[name='" + key + "']").val(str);
                } else if (key === 'voteLast') {
                    let date = new Date(value).toLocaleString().substring(0, 10).split('.').reverse().join("-");
                    form.find("input[name='" + key + "']").val(date);
                } else if (key === 'password'){
                    form.find("input[name='" + key + "']").val('');
                } else {
                    form.find("input[name='" + key + "']").val(value);
                }
            });
            form.find("input[name='" + "id" + "']").val(id);
            $('#editRow').modal();
        });
    }

    function updateUser(){
        $.ajax({
            type: "POST",
            url: "/admin/users",
            data: form.serialize(),
            success: function (date){
                $('#editRow').modal('hide');
                ctx.updateTable();
                successNoty("common.saved");
            }
        })
    }

    // =============================Restaurants++++++++++++++++++++

    function renderEditBtnForRest(data, type, row) {
        if (type === "display") {
            return "<a onclick='updateRowForRest(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
        }
    }

    function updateRowForRest(id) { //переписать через условие
        form.find(":input").val("");
        $("#modalTitle").html(i18n["editTitle"]);
        $.get("/admin/restaurant/" + id, function (data) {
            $.each(data, function (key, value) {
                form.find("input[name='" + key + "']").val(value);
            });
            $('#editRow').modal();
        });
    }

    function renderDeleteBtnForRest(data, type, row) {
        if (type === "display") {
            return "<a onclick='deleteRowForRest(" + row.id + ");'><span class='fa fa-remove'></span></a>";
        }
    }

    function renderRestaurantForPageRest(data, type, row) {
        if (type === "display") {
            return "<a href='/menusOfRestaurant'><span class='btn btn-success' onclick='set(" + row.id + ")'>"+ row.name +"</span></a>"

        }
    }

    function deleteRowForRest(id) {
        if (confirm(i18n['common.confirm'])) {
            $.ajax({
                url: "/admin/restaurant/" + id,
                type: "DELETE"
            }).done(function () {
                ctx.updateTableRest();
                successNoty("common.deleted");
            });
        }
    }

    function saveForRest(){
        $.ajax({
            type: "POST",
            url: "/admin/restaurant",
            data: form.serialize(),
            success: function (data){
                $('#editRow').modal('hide');
                ctx.updateTableRest();
                successNoty("common.saved");
            }
        })
    }


