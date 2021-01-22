var URL_USER_MENU = '/profile/menuToDay/';

    var ctx = {
        ajaxUrl:URL_USER_MENU,
        updateTable: function () {
            $.ajax({
                type:"GET",
                url:URL_USER_MENU,
            }).done(updateTableByData);
        }
    }

    $(function (){
        makeEditable({
            "columns":[
                {
                    "render": renderRestaurant,
                    "orderable": false,
                    "defaultContent": "",
                },
                {
                    "render": renderRestaurantRating,
                    "orderable": false,
                    "defaultContent": "",
                },
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
                            return new Date(date).toLocaleString().substring(0,10).split('.').join(".");
                        }
                        return date;
                    }
                },
                {
                    "render": renderVoteBtn,
                    "orderable": false,
                    "defaultContent": "",
                },
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
