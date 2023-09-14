var table = document.getElementById("tbList");
var txtID = document.getElementById("txtID");
var txtFullName = document.getElementById("txtFullName");
var txtPassword = document.getElementById("txtPassword");
var txtEmail = document.getElementById("txtEmail");
var txtPhone = document.getElementById("txtPhone");
var txtStatus = document.getElementById("txtStatus");

// error element
var errorID = document.getElementById("erID");
var errorFullName = document.getElementById("erFullName");
var errorPassword = document.getElementById("erPassword");
var errorEmail = document.getElementById("erEmail");
var errorPhone = document.getElementById("erPhone");
var errorStatus = document.getElementById("erStatus");



// btn logout
document.getElementById("btnLogout").addEventListener("click", () => {
  window.location.href = "index.html";
});

fetch(
  "http://localhost:8080/week01_lab_BuiTriThuc_20088361/ControllerServvar?action=getall"
)
  .then((response) => response.json())
  .then((data) =>
    data.forEach((acc) => {
      var row = table.insertRow(-1);

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
  var url =
    "http://localhost:8080/week01_lab_BuiTriThuc_20088361/ControllerServvar?action=test";

  var id = txtID.value;
  var fname = txtFullName.value;
  var pass = txtPassword.value;
  var email = txtEmail.value;
  var phone = txtPhone.value;
  var sts = txtStatus.value;
  var objUpdate = { id, fname, pass, email, phone, sts };

  var validObj = (objUpdate) => {
    if (objUpdate.id == "") {
      errorID.innerHTML = "ID is required";
      errorID.style.display = "block";
      return false;
    }
    else {
      errorID.style.display = "none";
    }
    if (objUpdate.fname == "") {
      errorFullName.innerHTML = "Full Name is required";
      errorFullName.style.display = "block";
      return false;
    }
    else {
      errorFullName.style.display = "none";
    }
    if (objUpdate.pass == "") {
      errorPassword.innerHTML = "Password is required";
      errorPassword.style.display = "block";
      return false;
    }
    else {
      errorPassword.style.display = "none";
    }
    if (objUpdate.email == "") {
      errorEmail.innerHTML = "Email is required";
      errorEmail.style.display = "block";
      return false;
    }
    else {
      errorEmail.style.display = "none";
    }
    if (objUpdate.phone == "") {
      errorPhone.innerHTML = "Phone is required";
      errorPhone.style.display = "block";
      return false;
    }
    else {
      errorPhone.style.display = "none";
    }
    if (objUpdate.sts == "") {
      errorStatus.innerHTML = "Status is required";
      errorStatus.style.display = "block";
      return false;
    }
    else {
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

document.getElementById("btnDevare").addEventListener("click", (even) => {
  even.preventDefault();
  var url =
    "http://localhost:8080/week01_lab_BuiTriThuc_20088361/ControllerServvar";

  var id = txtID.value;
  fetch(url, {
    method: "DEvarE",
    headers: {
      "Content-Type": "text/plain",
    },
    body: id,
  }).then((response) => {
    if (response.ok) {
      alert("Devare success account with id: " + id);
    } else {
      alert("Devare failed with status: " + response.status);
    }
  });
});

