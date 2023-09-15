var us = document.getElementById("us").value;
var pwd = document.getElementById("pwd").value;
var email = document.getElementById("email").value;
var phone = document.getElementById("phone").value;
var btnSubmit = document.getElementById("btnSubmit");
var erUS = document.getElementById("erUS");
var erPWD = document.getElementById("erPwd");
var erEmail = document.getElementById("erEmail");
var erPhone = document.getElementById("erPhone");

console.log(us);
console.log(pwd);
console.log(email);
console.log(phone);
btnSubmit.addEventListener("click", () => {
  // check validate
  let check = () => {
    console.log(us);
    console.log(pwd);
    console.log(email);
    console.log(phone);
    if (us === "") {
      erUS.innerHTML = "Username is required";
      erUS.style.display = "block";
      return false;
    } else {
      erUS.style.display = "none";
    }
    if (pwd === "") {
      erPWD.innerHTML = "Password is required";
      erPWD.style.display = "block";
      return false;
    } else {
      erPWD.style.display = "none";
    }
    if (email === "") {
      erEmail.innerHTML = "Email is required";
      erEmail.style.display = "block";
      return false;
    } else {
      erEmail.style.display = "none";
    }
    if (phone === "") {
      erPhone.innerHTML = "Phone is required";
      erPhone.style.display = "block";
      return false;
    } else {
      erPhone.style.display = "none";
    }
    return true;
  };
  if (check()) {
    console.log("ok");
    btnSubmit.type = "submit";
  }
});
