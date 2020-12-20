var URL_USER_MENU = '/Restaurant/profile/menuToDay/';

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
                    "render": renderVoteBtn,
                    "orderable": false,
                    "defaultContent": "",
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

        // $('#dateMenu').datetimepicker({
        //     format: 'Y-m-d H:i'
        // });
        // добавить нормальный datetimepicker
    });

    // function renderRestaurant(data, type, row) {
    //     if (type === "display") {
    //         // return "<a href='/Restaurant/menusOfRestaurant'><span class='btn btn-success'>"+ row.rest.name +"</span></a>";
    //         return "<a onclick='upUrl("+ row.id +")' href='/Restaurant/menusOfRestaurant'><span class='btn btn-success'>"+ row.rest.name +"</span></a>"
    //     }
    // }

    // function upUrl(id){
    //     debugger;
    //     ulr_menus = '/Restaurant/admin/menuToDay/3';
    // }
