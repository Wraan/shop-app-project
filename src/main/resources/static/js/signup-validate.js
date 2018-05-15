$("#signUpForm").submit(function () {
    var password = $("#pass").val();
    var confirmPassword = $("#repass").val();
    if (password !== confirmPassword) {
        alert("Passwords don't match");
        return false;
    }
    return true;
});
