$("#add").click(function () {
    var textboxgroup = '                <div class="input-group mb-1 rec">' +
        '                    <input type="text" name="name" class="form-control col-6 spec" placeholder="Name" aria-label="Name" aria-describedby="basic-addon1">\n' +
        '                    <input type="text" name="value" class="form-control col-6 val" placeholder="Value" aria-label="Value" aria-describedby="basic-addon1">\n' +
        '                </div>';
    $("#groups").append(textboxgroup);
});

$("#form").submit(function () {
    var arr = [];
    var num = 0;
    $("div.rec").each(function () {
        num++;
        var name = $(this).find(".spec").val();
        var value = $(this).find(".val").val();
        arr.push({number:num, name:name, value:value})
    });
    $("#json").val(JSON.stringify(arr));
});
