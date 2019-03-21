login = document.querySelector("#login");
password = document.querySelector("#password");
submit = document.querySelector("#submit");
logButton = document.querySelector("#logButton")

$("logButton").click(function (e) {
    if(login.value =! "" && password.value != ""){
        submit.click();
    }
});