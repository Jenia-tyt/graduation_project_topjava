var URL_ALL_USERS = '/Restaurant/admin/users/';

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
