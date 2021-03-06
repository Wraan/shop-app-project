$("#add").click(function () {
    var textboxgroup = '                <div class="input-group mb-1 rec">' +
        '                    <input type="text" class="form-control col-6 spec" placeholder="Name" aria-label="Name" aria-describedby="basic-addon1">\n' +
        '                    <input type="text" class="form-control col-6 val" placeholder="Value" aria-label="Value" aria-describedby="basic-addon1">\n' +
        '                </div>';
    $("#groups").append(textboxgroup);
});

$("#productForm").submit(function () {
    $(".alert").each(function () {
        $(this).remove();
    });

    var arr = [];
    var num = 0;
    $("div.rec").each(function () {
        num++;
        var name = $(this).find(".spec").val();
        var value = $(this).find(".val").val();
        if(name !== "" && value !== "")
            arr.push({number:num, name:name, value:value});
    });
    $("#jsonSpec").val(JSON.stringify(arr));

    // Validation
    if(!validateItem($("#name"), new RegExp("^(.{1,250})$"),
            "The maximum length for a product name is 250 characters."))
        return false;
    if(!validateItem($("#price"), new RegExp("^\\d{1,6}(?:[.,]\\d{3})*(?:[.,]\\d{2})$")
            ,"Wrong price syntax."))
        return false;
    if(!validateItem($("#amount"), new RegExp("^([1-9][0-9]{0,2})$"),
            "Amount can only be in range of 1 - 1 000."))
        return false;
    if(!validateItem($("#producer"), new RegExp("^(.{1,100})$"),
            "The maximum length for a producer is 100 characters."))
        return false;
    if(!validateItem($("#description"), new RegExp("^(.{1,255})$"),
            "The maximum length for a description is 255 characters."))
        return false;
    return true;
});

function validateItem(item, regex, alert)
{
    var correct = regex.test(item.val());
    if(!correct)
        item.parent().append('<div class="alert alert-danger" role="alert">\n' +
            alert +
            '</div>');
    return correct;
}
