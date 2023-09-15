let usLogin = document.getElementById("usLogin");
let table = document.getElementById("tbList");
let txtID = document.getElementById("txtID");
let txtFullName = document.getElementById("txtFullName");
let txtPassword = document.getElementById("txtPassword");
let txtEmail = document.getElementById("txtEmail");
let txtPhone = document.getElementById("txtPhone");
let txtStatus = document.getElementById("txtStatus");

// error element
let errorID = document.getElementById("erID");
let errorFullName = document.getElementById("erFullName");
let errorPassword = document.getElementById("erPassword");
let errorEmail = document.getElementById("erEmail");
let errorPhone = document.getElementById("erPhone");
let errorStatus = document.getElementById("erStatus");

//url api
let url =
  "http://localhost:8080/week01_lab_BuiTriThuc_20088361/ControllerServlet";

// /get user name login
let accountLogin = JSON.parse(localStorage.getItem("accLogin"));
usLogin.innerHTML = accountLogin.full_name;

// btn logout
document.getElementById("btnLogout").addEventListener("click", () => {
  window.location.href = "index.html";
});

fetch(url + "?action=getall")
  .then((response) => response.json())
  .then((data) =>
    data.forEach((acc) => {
      let row = table.insertRow(-1);

      row.addEventListener("click", () => {
        txtID.value = acc.account_id;
        txtFullName.value = acc.full_name;
        txtPassword.value = acc.password;
        txtEmail.value = acc.email;
        txtPhone.value = acc.phone;
        txtStatus.value = acc.status;
      });
      row.insertCell(0).innerHTML = acc.account_id;
      row.insertCell(1).innerHTML = acc.full_name;
      row.insertCell(2).innerHTML = acc.password;
      row.insertCell(3).innerHTML = acc.email;
      row.insertCell(4).innerHTML = acc.phone;
      row.insertCell(5).innerHTML = acc.status;
    })
  );

document.getElementById("btnAdd").addEventListener("click", () => {
  window.location.href = "insert_account.html";
});

document.getElementById("btnUpdate").addEventListener("click", (even) => {
  even.preventDefault();

  let id = txtID.value;
  let fname = txtFullName.value;
  let pass = txtPassword.value;
  let email = txtEmail.value;
  let phone = txtPhone.value;
  let sts = txtStatus.value;
  let objUpdate = { id, fname, pass, email, phone, sts };

  let validObj = (objUpdate) => {
    if (objUpdate.id === "") {
      errorID.innerHTML = "ID is required";
      errorID.style.display = "block";
      return false;
    } else {
      errorID.style.display = "none";
    }
    if (objUpdate.fname === "") {
      errorFullName.innerHTML = "Full Name is required";
      errorFullName.style.display = "block";
      return false;
    } else {
      errorFullName.style.display = "none";
    }
    if (objUpdate.pass === "") {
      errorPassword.innerHTML = "Password is required";
      errorPassword.style.display = "block";
      return false;
    } else {
      errorPassword.style.display = "none";
    }
    if (objUpdate.email === "") {
      errorEmail.innerHTML = "Email is required";
      errorEmail.style.display = "block";
      return false;
    } else if (
      /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(objUpdate.email) === false
    ) {
      errorEmail.innerHTML = "Email is invalid";
      errorEmail.style.display = "block";
      return false;
    } else {
      errorEmail.style.display = "none";
    }
    if (objUpdate.phone === "") {
      errorPhone.innerHTML = "Phone is required";
      errorPhone.style.display = "block";
      return false;
    } else if (
      /(84|0[3|5|7|8|9])+([0-9]{8})\b/.test(objUpdate.phone) === false
    ) {
      errorPhone.innerHTML = "Phone is invalid";
      errorPhone.style.display = "block";
      return false;
    } else {
      errorPhone.style.display = "none";
    }
    if (objUpdate.sts === "") {
      errorStatus.innerHTML = "Status is required";
      errorStatus.style.display = "block";
      return false;
    } else {
      errorStatus.style.display = "none";
    }
    return true;
  };
  if (validObj(objUpdate)) {
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(objUpdate),
    }).then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error("Request failed with status: " + response.status);
      }
    });
  }
});

document.getElementById("btnDelete").addEventListener("click", (even) => {
  even.preventDefault();

  let id = txtID.value;
  fetch(url, {
    method: "DELETE",
    headers: {
      "Content-Type": "text/plain",
    },
    body: id,
  }).then((response) => {
    if (response.ok) {
      alert("Delete success account with id: " + id);
    } else {
      alert("Delete failed with status: " + response.status);
    }
  });
});

//set logout time
document.getElementById("btnLogout").addEventListener("click", () => {
  let params = {
    action: "setTimeLogout",
    account_id: accountLogin.account_id,
  };
  let queryString = Object.keys(params)
    .map((key) => key + "=" + encodeURIComponent(params[key]))
    .join("&");
  fetch(url +"?" + queryString);
});
