document.getElementById("submit").addEventListener("click", (even) => {
  even.preventDefault();
  let params = {
    action: "login",
    us: document.getElementById("us").value,
    pwd: document.getElementById("pwd").value,
  };
  let queryString = Object.keys(params)
    .map((key) => key + "=" + encodeURIComponent(params[key]))
    .join("&");
  let apiUrl ="ControllerServlet";
  let urlWithParams = apiUrl + "?" + queryString;
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
      let role = JSON.parse(data.role);
      let account = JSON.parse(data.account);
      if (role.role_id === "admin") {
        alert(`Login successfully as admin :${account.full_name}`);
        localStorage.clear();
        localStorage.setItem("accLogin", JSON.stringify(account));
        localStorage.setItem("roleLogin", JSON.stringify(role));
        window.location.href = "dashboard.html";
      } else {
        alert("Login successfully as user : " + account.full_name);
        localStorage.clear();
        localStorage.setItem("accLogin", JSON.stringify(account));
        localStorage.setItem("roleLogin", JSON.stringify(role));
        window.location.href = "userInfo.html";
      }
    });
});
