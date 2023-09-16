package vn.edu.iuh.fit.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.GrantAccess;
import vn.edu.iuh.fit.repositories.AccountRepository;

import java.io.IOException;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.LogsRepository;


@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

  private final AccountRepository accRep;
  private final GrantAccessRepository GAR;
  private final LogsRepository logRep;
  private final Gson gson = new Gson();
  private BufferedReader reader;
  private StringBuilder jsonBuilder;

  public ControllerServlet() throws Exception {
    GAR = new GrantAccessRepository();
    accRep = new AccountRepository();
    logRep = new LogsRepository();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    String action = req.getParameter("action");
    if (action.equalsIgnoreCase("getall")) {
      List<Account> list = accRep.getAllAccount();
      String jsonData = gson.toJson(list);
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      resp.getWriter().write(jsonData);
    }
    // login 
    if (action.equalsIgnoreCase("login")) {
      String us = req.getParameter("us");
      String pwd = req.getParameter("pwd");
      Account acc = accRep.getAccount(us, pwd);

      if (acc != null) {
        GrantAccess grantAccess;
        try {
          grantAccess = GAR.grantAccess(acc.getAccount_id());
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }

        String account = gson.toJson(acc);
        String role = gson.toJson(grantAccess);

        JsonObject objData = new JsonObject();
        objData.addProperty("account", account);
        objData.addProperty("role", role);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(objData.toString());
        resp.setStatus(HttpServletResponse.SC_OK);

        //wirte log login
        logRep.setLogTime(acc.getAccount_id(), "");
      } else {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }

    }
    if (action.equalsIgnoreCase("setTimeLogout")) {
      logRep.setLogoutTime(req.getParameter("account_id"));
    }


  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String action = req.getParameter("action");
    if (action.equalsIgnoreCase("insert_account")) {
      reader = req.getReader();
      jsonBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        jsonBuilder.append(line);
      }

      Account account = gson.fromJson(jsonBuilder.toString(), Account.class);
      String[] newID = account.getFull_name().split("\\s");
      account.setAccount_id(newID[newID.length-1]);
      System.out.println(account);
      boolean rs;
      try {
        rs = accRep.insert_account(account);
        if (rs) {
          rs = GAR.setRoleAccount(account.getAccount_id(), "user");
          if (rs) {
            System.out.println(rs);
            resp.setStatus(HttpServletResponse.SC_OK);
          }
        }
      } catch (Exception e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
          resp.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
          throw new RuntimeException(e);
        }

      }
    }
    if (action.equalsIgnoreCase("update")) {
      reader = req.getReader();
      jsonBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        jsonBuilder.append(line);
      }
      Account acc = gson.fromJson(jsonBuilder.toString(), Account.class);
      if (accRep.update_account(acc)) {
        resp.setStatus(HttpServletResponse.SC_OK);
      } else {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }


    }

    if (action.equalsIgnoreCase("setRole")) {
      String AccID = req.getParameter("account_id");
      String roleAccess = req.getParameter("role_access");
      if (GAR.setRoleAccount(AccID, roleAccess)) {
        resp.setStatus(HttpServletResponse.SC_OK);
      } else {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    }

  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    reader = req.getReader();
    jsonBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      jsonBuilder.append(line);
    }
    System.out.println("ID Delete: " + jsonBuilder.toString());
    try {
      if (accRep.delete_account(jsonBuilder.toString())) {
        resp.setStatus(HttpServletResponse.SC_OK);
      } else {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


  }
}
