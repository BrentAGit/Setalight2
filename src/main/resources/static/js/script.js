function checkPassword(form) {
    password1 = form.password.value;
    password2 = form.rePassword.value;
    username = form.username.value;
    let noPassword = document.getElementById("noPassword");
    let noConfirmPassword = document.getElementById("noConfirmPassword");
    let noMatch = document.getElementById("noMatch");

    if (password1 === '') {
        noPassword.style.display = "inline-block";
        noConfirmPassword.style.display = "none";
        noMatch.style.display = "none";
        return false;
    } else if (password2 === '') {
        noPassword.style.display = "none";
        noConfirmPassword.style.display = "inline-block";
        noMatch.style.display = "none";
        return false;
    } else if (password1 !== password2) {
        noPassword.style.display = "none";
        noConfirmPassword.style.display = "none";
        noMatch.style.display = "inline-block";
        return false;
    } else if (username === "" && password1 === "" && password2 === "") {
        return false;
    } else {
        noPassword.style.display = "none";
        noConfirmPassword.style.display = "none";
        noMatch.style.display = "none";
        return true;
    }
}