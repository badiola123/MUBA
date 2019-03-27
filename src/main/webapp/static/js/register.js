var login;
var password;
var password2;

$(document).ready(function(){
    login = $("#login");
    password = $("#password");
    password2 = $("#password2");

    $("#registerButton").click(function () {
        submit();
    });
});

$(document).keypress(function (event) {
    if(event.keyCode == 13) {
        event.preventDefault();
        submit();
    }
});

function passwordCheck() {
    if(password.val() != password2.val()){
        alert("Hasła nie są takie same.")
    }
    else {
        $("form").submit();
    }
}

function submit(){
    if(login.val()=="" || password.val()=="" || password2.val()==""){
        alert("Proszę wypełnić wszystkie pola.")
    }
    else {
        passwordCheck();
    }
}

