$("#signUpForm").submit(function () {
    $(".alert").each(function () {
        $(this).remove();
    });

    if(!validateItem($("#username"), new RegExp("([a-zA-Z0-9]{6,32}$)"),
            "Username should contain 6 to 32 characters. You can use lower and upper case letters and numbers."))
        return false;
    if(!validateItem($("#email"), new RegExp(".+\\@.+\\..+")
            ,"Wrong email address."))
        return false;
    if(!checkBothPasswords())
        return false;
    if(!validateItem($("#pass"), new RegExp("([a-zA-Z0-9]{6,32}$)"),
            "Password should contain 6 to 32 characters. You can use lower and upper case letters and numbers."))
        return false;


    if(!validateItem($("#firstname"), new RegExp("([a-zA-Z\\-]{0,255}$)"),
            "You can use lower and upper case letters and '-' sign."))
        return false;
    if(!validateItem($("#lastname"), new RegExp("([a-zA-Z\\-]{0,255}$)"),
            "You can use lower and upper case letters and '-' sign."))
        return false;
    if(!validateItem($("#street"), new RegExp("([a-zA-Z0-9\\.\\-]{0,255}$)"),
            "You can use lower and upper case letters, numbers and ['-', '.'] signs."))
        return false;
    if(!validateItem($("#city"), new RegExp("([a-zA-Z\\.\\-]{0,255}$)"),
            "You can use lower and upper case letters and ['-', '.'] signs."))
        return false;
    if(!$("#zipCode").val().trim() === "" && !validateItem($("#zipCode"), new RegExp("([0-9]{2}-[0-9]{3}$)"),
            "U need to provide numbers with '-' in format xx-xxx"))
        return false;
    if(!$("#phone").val().trim() === "" && !validateItem($("#phone"), new RegExp("([0-9]{9,9}$)"),
            "U need to provide 9 numbers"))
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
function checkBothPasswords() {
    var password = $("#pass").val();
    var confirmPassword = $("#repass").val();
    var correct = password === confirmPassword;
    if (!correct)
        $("#pass").parent().append('<div class="alert alert-danger" role="alert">\n' +
            'Passwords dont match!' +
            '</div>');
    return correct;
}
