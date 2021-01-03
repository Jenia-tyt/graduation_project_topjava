    var form;
    var URL_ADMIN = '/Restaurant/admin/menuToDay';
    var URL_ALL_MENU_REST;

    $.ajaxSetup({cache: false});

    function makeEditable(datatableOpts) { //разобарать какк выводить ошибки
        ctx.datatableApi = $("#datatable").DataTable(
            // https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
            $.extend(true, datatableOpts,
                {
                    "ajax": {
                        "url": ctx.ajaxUrl,
                        "dataSrc": ""
                    },
                    "paging": true,
                    "info": true,
                }
            ));

        form = $('#detailsForm');
        $(document).ajaxError(function (event, jqXHR, options, jsExc) { //вот здесь выводятся ошибки надо разобраться с этим
            failNoty(jqXHR);
        });

        // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
        $.ajaxSetup({cache: false});
    }

    function failNoty(jqXHR) {
        closeNoty();
        var errorInfo = jqXHR.responseText;//тут не правильно надо обрабатывать страницу ошибки а она не json
        failedNote = new Noty({
            text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + errorInfo,//.statusText + "<br>" + errorInfo.statusText,//.details.join("<br>"),// + "<br>",// + errorInfo.details.join("<br>"),
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

    function successNoty(key) { //высплывающие уведомления
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

    function updateRow(id, id_rest) { //переписать через кусловие
        form.find(":input").val("");
        $("#modalTitle").html(i18n["editTitle"]);
        $.get('/Restaurant/profile/menuToDay/' + id, function (data) {
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
            return "<a onclick='vote(" + row.id + ")'><span class='btn btn-primary'><img src='Restaurant/resources/images/icon2.png' width=30 height=30></span></a>"; //сюда надо повеситть функцию голосовать и сделать подсвечивающуюся кнопку
        }
    }

    function vote(id) {  //сюда надо накинуть ошибку что ты уже голосовал сегодня или это в java?
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
            return "<a href='/Restaurant/menusOfRestaurant'><span class='btn btn-success' onclick='set("+row.rest.id +")'>"+ row.rest.name +"</span></a>"
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
                url: '/Restaurant/admin/users/' + id,
                type: "DELETE"
            }).done(function () {
                ctx.updateTable();
                successNoty("common.deleted");
            });
        }
    }

 // тут надо отредоктировать всплывающее окно что бы заполнялдось и сохранялось для юзера
    function addEditUser(){
        $("#modalTitle").html(i18n["addTitle"]);
        form.find(":input").val("");
        form.find("input[name='" + "id_rest" + "']").val(localStorage.getItem('idRest'));
        $("#editRow").modal();
    }

    function updateRowForUser(id) {
        form.find(":input").val("");
        $("#modalTitle").html(i18n["editUser"]);
        $.get('/Restaurant/admin/users/' + id, function (data) {
            $.each(data, function (key, value) {
                if (key === "role") {
                    let str = value.sort().join('.');
                    form.find("select[name='" + key + "']").val(str);
                } else if (key === 'voteLast') {
                    let date = new Date(value).toLocaleString().substring(0, 10).split('.').reverse().join("-");
                    form.find("input[name='" + key + "']").val(date);
                } else {
                    form.find("input[name='" + key + "']").val(value);
                }
            });
            form.find("input[name='" + "id" + "']").val(id);
            $('#editRow').modal();
        });
    }

    function saveUser(){
        $.ajax({
            type: "POST",
            url: "/Restaurant/admin/users",
            data: form.serialize(),
            success: function (data){
                $('#editRow').modal('hide');
                ctx.updateTable();
                successNoty("common.saved")
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
        $.get("/Restaurant/admin/restaurant/" + id, function (data) {
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
            return "<a href='/Restaurant/menusOfRestaurant'><span class='btn btn-success' onclick='set("+row.id +")'>"+ row.name +"</span></a>"
        }
    }

    function deleteRowForRest(id) {
        if (confirm(i18n['common.confirm'])) {
            $.ajax({
                url: "/Restaurant/admin/restaurant/" + id,
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
            url: "/Restaurant/admin/restaurant",
            data: form.serialize(),
            success: function (data){
                $('#editRow').modal('hide');
                ctx.updateTableRest();
                successNoty("common.saved");
            }
        })
    }


