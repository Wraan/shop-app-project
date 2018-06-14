$("#emailChangeForm").submit(function () {
    $(".alert").each(function () {
        $(this).remove();
    });

    if(!validateItem($("#newEmail"), new RegExp(".+\\@.+\\..+")
            ,"Wrong email address."))
        return false;
    if(!checkBothEmails())
        return false;

    return true;
});

$("#addNewAddressForm").submit(function () {
    $(".alert").each(function () {
        $(this).remove();
    });

    if(!validateItem($("#firstName"), new RegExp("([a-zA-Z\\-]{1,255}$)"),
            "You can use lower and upper case letters and '-' sign."))
        return false;
    if(!validateItem($("#lastName"), new RegExp("([a-zA-Z\\-]{0,255}$)"),
            "You can use lower and upper case letters and '-' sign."))
        return false;
    if(!validateItem($("#street"), new RegExp("([a-zA-Z0-9\\.\\-]{0,255}$)"),
            "You can use lower and upper case letters, numbers and ['-', '.'] signs."))
        return false;
    if(!validateItem($("#city"), new RegExp("([a-zA-Z\\.\\-]{0,255}$)"),
            "You can use lower and upper case letters and ['-', '.'] signs."))
        return false;
    if(!$("#zipCode").val().trim() === "" || !validateItem($("#zipCode"), new RegExp("([0-9]{2}-[0-9]{3}$)"),
            "You need to provide numbers with '-' in format xx-xxx"))
        return false;
    if(!$("#phoneNumber").val().trim() === "" || !validateItem($("#phoneNumber"), new RegExp("([0-9]{9,9}$)"),
            "You need to provide 9 numbers"))
        return false;

    return true;
});


function checkBothEmails() {
    var newEmailAddress = $("#newEmail").val();
    var confirmNewEmailAddress = $("#newEmailConfirm").val();
    var correct = newEmailAddress === confirmNewEmailAddress;
    if (!correct)
        $("#newEmail").parent().append('<div class="alert alert-danger" role="alert">\n' +
            'E-mails dont match!' +
            '</div>');
    return correct;
}

function validateItem(item, regex, alert)
{
    var correct = regex.test(item.val());
    if(!correct)
        item.parent().append('<div class="alert alert-danger" role="alert">\n' +
            alert +
            '</div>');
    return correct;
}





