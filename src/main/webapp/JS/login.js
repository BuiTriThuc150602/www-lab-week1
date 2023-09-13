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
    .then((response) => response.text())
    .then((data) => {
      var role = data;
      if (role === "admin") {
        window.location.href = "Dashboard.html";
      }
    });
});
