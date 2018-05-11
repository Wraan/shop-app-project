$("#signUpForm").submit(function(){
    var password = $("#pass").val();
    var confirmPassword = $("#repass").val();
    if(password === confirmPassword){
        return true;
    }
    else{
        alert("Passwords don't match");
        return false;
    }
});