var table = document.getElementById("tbList");
var obj;
var txtID = document.getElementById("txtID");
var txtFullName = document.getElementById("txtFullName");
var txtPassword = document.getElementById("txtPassword");
var txtEmail = document.getElementById("txtEmail");
var txtPhone = document.getElementById("txtPhone");
var txtStatus = document.getElementById("txtStatus");

fetch(
  "http://localhost:8080/week01_lab_BuiTriThuc_20088361/ControllerServlet?action=getall"
)
  .then((response) => response.json())
  .then((data) =>
    data.forEach((acc) => {
      let row = table.insertRow(-1);

      row.addEventListener("click", () => {
        id = acc.account_id;
        fname = acc.full_name;
        pass = acc.password;
        email = acc.email;
        phone = acc.phone;
        sts = acc.status;
        obj = { id, fname, pass, email, phone, sts };
        txtID.value = obj.id;
        txtFullName.value = obj.fname;
        txtPassword.value = obj.pass;
        txtEmail.value = obj.email;
        txtPhone.value = obj.phone;
        txtStatus.value = obj.sts;
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
    "http://localhost:8080/week01_lab_BuiTriThuc_20088361/ControllerServlet?action=test";

  let id = txtID.value;
  let fname = txtFullName.value;
  let pass = txtPassword.value;
  let email = txtEmail.value;
  let phone = txtPhone.value;
  let sts = txtStatus.value;
  let objUpdate = { id, fname, pass, email, phone, sts };
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
});

document.getElementById("btnDelete").addEventListener("click", (even) => {
  even.preventDefault();
  var url =
    "http://localhost:8080/week01_lab_BuiTriThuc_20088361/ControllerServlet";

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
