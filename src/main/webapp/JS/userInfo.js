let account =JSON.parse(localStorage.getItem("accLogin"));
let role = JSON.parse(localStorage.getItem("roleLogin"));
let passInput = document.getElementById("txtPassword");
let showPass = document.getElementById("btnShowPass");
let btnLogout = document.getElementById("btnLogout");
console.log(account);
console.log(role);


//account
document.getElementById("txtID").value = account.account_id;
document.getElementById("txtFullName").value = account.full_name;
passInput.value = account.password;
document.getElementById("txtEmail").value = account.email;
document.getElementById("txtPhone").value = account.phone;
//role
document.getElementById("txtRole").value = role.role_id;

//show/hiden password
showPass.addEventListener( "click", (even)=>{
    even.preventDefault();
    passInput.type = passInput.type === "password" ? "text" : "password";
    showPass.textContent = showPass.textContent === "Show" ? "Hide" : "Show";
});

//logout
btnLogout.addEventListener("click", (even)=>{
    even.preventDefault();
    localStorage.clear();
    window.location.href = "index.html";
});
