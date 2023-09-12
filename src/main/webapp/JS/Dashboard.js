let table = document.getElementById("tbList");
fetch(
  "http://localhost:8080/week01_lab_BuiTriThuc_20088361/ControllerServlet?action=getall"
)
  .then((response) => response.json())
  .then((data) =>
    data.forEach((acc) => {
      let row = table.insertRow(-1);
      row.addEventListener("click", () => {console.log("click");});
      row.insertCell(0).innerHTML = acc.account_id;
      row.insertCell(1).innerHTML = acc.full_name;
      row.insertCell(2).innerHTML = acc.password;
      row.insertCell(3).innerHTML = acc.email;
      row.insertCell(4).innerHTML = acc.phone;
      row.insertCell(5).innerHTML = acc.status;
    })
  );

// let rows = document.querySelectorAll("#tbList tbody tr");
// console.log(rows);

// Gán sự kiện click cho từng hàng
// rows.forEach((row) => {
//   row.addEventListener("click", () => {
//     // rows.forEach((r) => {
//     //   r.classList.remove("selected");
//     // });
//     this.classList.add("selected");
//   });
// });

// // Lấy dữ liệu từ hàng được chọn
// let selectRow = document.getElementsByTagName("td");
// console.log(selectRow);
// let id = selectRow[0].textContent;
// let fname = selectRow[1].textContent;
// let pass = selectRow[2].textContent;
// let email = selectRow[3].textContent;
// let phone = selectRow[4].textContent;
// let status = selectRow[5].textContent;

// // Hiển thị thông tin hàng được chọn (ví dụ: alert)
// console.log(id + fname);
