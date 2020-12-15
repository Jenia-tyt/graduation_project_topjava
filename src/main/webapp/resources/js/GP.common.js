function makeEditable(){
    $('#add').click(function (){
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function (){
        deleteRow($(this).attr('id'));
    });

    $('detailsForm').submit(function (){
        save();
        return false;
    })
}

function deleteRaw(id){
    $.ajax({
        url: UI_URl + id, //сюда приходит юрл юзера который не моджет удалять обрати внимание
        type: 'DELETE',
        success: function (){
            updateTable();
        }
    })
}

function updateTable(){
    $.get(UI_URl, function (data){
        oTable_dateTable.fnClearTable();
        $.each(data, function (key, item){
            oTable_dateTable.fnAddData(item);
        });
        oTable_dateTable.fnDraw();
    });
}

function save(){
    var from = $('detailsForm');
    debugger;
    $.ajax({
        type: "POST",
        url: UI_URl,
        data: from.serialize(),
        success: function (data){
            $('#editRow').modal('hide');
            updateTable();
        }
    })
}