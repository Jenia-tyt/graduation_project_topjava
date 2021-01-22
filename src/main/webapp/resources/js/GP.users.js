var URL_ALL_USERS = '/admin/users/';

var ctx = {
    ajaxUrl:URL_ALL_USERS,
    updateTable: function (){
        $.ajax({
            type:"GET",
            url:URL_ALL_USERS,
        }).done(updateTableByData) ;
    }
}

$(function (){
    makeEditable({
        "columns":[
            {
                "mData" : "name"
            },
            {
                "mData" : "email",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a href='mailto:" + data + "'>" + data + "</a>";
                    }
                    return data;
                }
            },
            {
                "data":"role",
            },
            {
                "render": renderEditBtnForUser,
                "orderable": false,
                "defaultContent": "",
            },
            {
                "render": renderDeleteBtnForUsers,
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
});
