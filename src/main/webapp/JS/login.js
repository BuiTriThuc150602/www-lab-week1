document.getElementById("submit").addEventListener("click", (even) => {
  even.preventDefault();
  var params = {
    action: "login",
    us: document.getElementById("us").value,
    pwd: document.getElementById("pwd").value,
  };
  var queryString = Object.keys(params)
    .map((key) => key + "=" + encodeURIComponent(params[key]))
    .join("&");
  var apiUrl =
    "http://localhost:8080/week01_lab_BuiTriThuc_20088361/ControllerServlet";
  var urlWithParams = apiUrl + "?" + queryString;
  fetch(urlWithParams)
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        alert("Login failed");
        throw new Error("Request failed with status: " + response.status);
      }
    })
    .then((data) => {
      var role =JSON.parse(data.role);
      var account = JSON.parse(data.account);
      console.log(data);
      console.log(role);
      console.log(account);
      if (role.role_id === "admin") {
        alert("Login successfully as admin :" + account.full_name);
        localStorage.clear();
        localStorage.setItem("accLogin", JSON.stringify(account));
        localStorage.setItem("roleLogin", JSON.stringify(role));
        window.location.href = "Dashboard.html";
      } else {
        alert("Login successfully as user : " + account.full_name);
        localStorage.clear();
        localStorage.setItem("accLogin", JSON.stringify(account));
        localStorage.setItem("roleLogin", JSON.stringify(role));
        window.location.href = "userInfo.html";
      }
    }); 
});
