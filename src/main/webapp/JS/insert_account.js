let btnSubmit = document.getElementById("btnSubmit");
let erUS = document.getElementById("erUS");
let erPWD = document.getElementById("erPwd");
let erEmail = document.getElementById("erEmail");
let erPhone = document.getElementById("erPhone");

// check validate
let check = () => {
  let us = document.getElementById("us").value;
  let pwd = document.getElementById("pwd").value;
  let email = document.getElementById("email").value;
  let phone = document.getElementById("phone").value;
  console.log(us, pwd, email, phone);
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
btnSubmit.addEventListener("click", (even) => {
  even.preventDefault();
  if (check()) {
    // btnSubmit.type = "submit";
    let obj = { 
      full_name: document.getElementById("us").value,
      password: document.getElementById("pwd").value,
      email: document.getElementById("email").value,
      phone: document.getElementById("phone").value,
     };
    console.log(obj);
    let url =
      "ControllerServlet?action=insert_account";
    fetch(url, {
      mode: "no-cors",
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(obj),
    }).then((response) => {
      if (response.ok) {
        alert("Sign up success, Go to Login to continue");
        window.location.href = "index.html";
      } else {
        alert("Sign up fail, Try again");
      }
    });
  }
});
