var login;
var password;
var invisibleButton;

$(document).ready(function(){
    login = $("#login");
    password = $("#password");
    invisibleButton = $("#invisibleButton");
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
        invisibleButton.click();
    }
}
