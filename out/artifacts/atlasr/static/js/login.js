var login;
var password;

$(document).ready(function(){
    login = $("#login");
    password = $("#password");
    $("#logButton").click(function () {
        submit();
    });
});

$(document).keypress(function (event) {
    if(event.keyCode == 13) {
        event.preventDefault();
        submit();
    }
});

function submit(){
    if(login.val()=="" || password.val()==""){
        alert("Proszę wypełnić wszystkie pola.")
    }
    else {
        $("form").submit();
    }
}
