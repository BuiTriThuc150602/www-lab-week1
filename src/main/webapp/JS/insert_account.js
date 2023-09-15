let us = document.getElementById("us").value;
let pwd = document.getElementById("pwd").value;
let email = document.getElementById("email").value;
let phone = document.getElementById("phone").value;
let btnSubmit = document.getElementById("btnSubmit");
let erUS = document.getElementById("erUS");
let erPWD = document.getElementById("erPwd");
let erEmail = document.getElementById("erEmail");
let erPhone = document.getElementById("erPhone");

console.log(btnSubmit);
btnSubmit.addEventListener("click",()=> {
    // check validate
    let check = () => {
        if(us === ""){
            erUS.innerHTML = "Username is required";
            erUS.style.display = "block";
            return false;
        }
        else{
            erUS.style.display = "none";
        }
        if(pwd === ""){
            erPWD.innerHTML = "Password is required";
            erPWD.style.display = "block";
            return false;
        }
        else{
            erPWD.style.display = "none";
        }
        if(email === ""){
            erEmail.innerHTML = "Email is required";
            erEmail.style.display = "block";
            return false;
        }
        else{
            erEmail.style.display = "none";
        }
        if(phone === ""){
            erPhone.innerHTML = "Phone is required";
            erPhone.style.display = "block";
            return false;
        }
        else{
            erPhone.style.display = "none";
        }
        return true;
    };
    if(check()){
        console.log("ok");
        btnSubmit.type = "submit";
    }
});