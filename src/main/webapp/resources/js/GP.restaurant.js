var URL_ALL_REST = '/Restaurant/admin/restaurant';

var ctx = {
    ajaxUrl:URL_ALL_REST,
    updateTableRest: function () {
        $.ajax({
            type:"GET",
            url:URL_ALL_REST,
        }).done(updateTableByData);
    }
}

$(function (){
    makeEditable({
        // добавить выбор листов
        "columns":[
            {
                "render": renderRestaurantForPageRest,
                "orderable": false,
                "defaultContent": "",
            },
            {
                "mData" : "rating"
            },
            {
                "render": renderEditBtnForRest,
                "orderable": false,
                "defaultContent": "",
            },
            {
                "render": renderDeleteBtnForRest,
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
