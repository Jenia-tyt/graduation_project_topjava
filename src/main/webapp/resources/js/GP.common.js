    var form;
    var URL_ADMIN = '/Restaurant/admin/menuToDay';
    var URL_ALL_MENU_REST;

    $.ajaxSetup({cache: false});

    function makeEditable(datatableOpts) { //разобарать что как работает
        ctx.datatableApi = $("#datatable").DataTable(
            // https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
            $.extend(true, datatableOpts,
                {
                    "ajax": {
                        "url": ctx.ajaxUrl,
                        "dataSrc": ""
                    },
                    "paging": false,
                    "info": true
                }
            ));

        form = $('#detailsForm');
        // $(document).ajaxError(function (event, jqXHR, options, jsExc) { //вот здесь выводятся ошибки надо разобраться с этим
        //     failNoty(jqXHR);
        // });

        // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
        $.ajaxSetup({cache: false});

    }

    function updateTableByData(data) {
        ctx.datatableApi.clear().rows.add(data).draw();
    }

    function save(){            //приходят не правильные параметры для сохранения меню, если этот метод использовать для сохранения и меню и ресторана то надо править URL
        $.ajax({
            type: "POST",
            url: URL_ADMIN,
            data: form.serialize(),
            success: function (data){
                debugger;
                $('#editRow').modal('hide');
                debugger;
                ctx.updateTable();
            }
        })
    }

    function add(){
        $("#modalTitle").html(i18n["addTitle"]);
        form.find(":input").val("");
        $("#editRow").modal();
    }

    var failedNote;

    function closeNoty() { //закрытие всплывающих окон
        if (failedNote) {
            failedNote.close();
            failedNote = undefined;
        }
    }

    function renderEditBtn(data, type, row) {
        if (type === "display") {
            return "<a onclick='updateRow(" + row.id + ","+ row.rest.id +");'><span class='fa fa-pencil'></span></a>";
        }
    }

    function updateRow(id, id_rest) {
        form.find(":input").val("");
        $("#modalTitle").html(i18n["editTitle"]);
        $.get('/Restaurant/profile/menuToDay/' + id, function (data) {
            $.each(data, function (key, value) {
                console.log(value)
                if (key === 'dateMenu'){
                    let valueDate = value.join("-").toString();
                    form.find("input[name='" + "dateMenu" + "']").val(valueDate);
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
                // successNoty("common.deleted");
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


    function set(id) {
        localStorage.setItem("id", id);
    }
// ====================USERS+++++++++++++++++++++ наверно надо вынести в отдельный файл так как получается много дублируеющего кода
    function renderEditBtnForUser(data, type, row) {
        if (type === "display") {
            return "<a onclick='updateRowForUser(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
        }
    }

    function renderDeleteBtnFromUsers(data, type, row) {
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
                // successNoty("common.deleted");
            });
        }
    }
 // тут надо отредоктировать всплывающее окно что бы заполнялдось и сохранялось
    function updateRowForUser(id) {
        debugger;
        form.find(":input").val("");
        $("#modalTitle").html(i18n["editTitle"]);
        $.get('/Restaurant/admin/users/' + id, function (data) {
            $.each(data, function (key, value) {
                form.find("input[name='" + key + "']").val(value);
            });
            form.find("input[name='" + "id" + "']").val(id);
            $('#editRow').modal();
        });
    }
