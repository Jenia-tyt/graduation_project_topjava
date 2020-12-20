var URL_ALL_MENU_REST = '/Restaurant/admin/menuToDay/';
// надо денамически изменять юрл !!!!!!!!!!!!!!!


var cat = localStorage.getItem('id')

    var ctx = {
        ajaxUrl:URL_ALL_MENU_REST + cat,
        updateTable: function (){
            $.ajax({
                type:"GET",
                url:URL_ALL_MENU_REST + cat,
            }).done(updateTableByData) ;
        }
    }

$(function (){
    makeEditable({
        "columns":[
            {
                "mData" : "menuRest"
            },
            {
                "mData" : "rating",
            },
            {
                "data":"dateMenu",
                "render": function (date, type, row) {
                    if (type === "display") {
                        return date.reverse().toString().replaceAll(',','.');
                    }
                    return date;
                }
            },
            {
                "render": renderEditBtn,
                "orderable": false,
                "defaultContent": "",
            },
            {
                "render": renderDeleteBtn,
                "orderable": false,
                "defaultContent":"",
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
    });
    // добавить нормальный datetimepicker
});
