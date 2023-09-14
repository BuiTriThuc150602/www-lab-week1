package vn.edu.iuh.fit.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
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


@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

  private final AccountRepository accRep;
  private final GrantAccessRepository GAR;
  private final Gson gson = new Gson();
  private BufferedReader reader;
  private StringBuilder jsonBuilder;

  public ControllerServlet() throws Exception {
    GAR = new GrantAccessRepository();
    accRep = new AccountRepository();
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

      System.out.println(us);
      System.out.println(pwd);
      System.out.println(acc);

      if (acc != null) {
        GrantAccess grantAccess;
        try {
          grantAccess = GAR.grantAccess(acc.getAccount_id());
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
        System.out.println(acc);
        System.out.println(grantAccess);

        String account = gson.toJson(acc);
        String role = gson.toJson(grantAccess);

        JsonObject objData = new JsonObject();
        objData.addProperty("account",account);
        objData.addProperty("role",role);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(objData.toString());
        resp.setStatus(HttpServletResponse.SC_OK);

      } else {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }
    }


  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String action = req.getParameter("action");
    if (action.equalsIgnoreCase("insert_account")) {
      String us = req.getParameter("us");
      String pwd = req.getParameter("pwd");
      String email = req.getParameter("email");
      String phone = req.getParameter("phone");
      int status = 1;
      String[] last_name_to_id = us.split("\\s");
      String id = last_name_to_id[last_name_to_id.length - 1];

      Account account = new Account(id, us, pwd, email, phone, status);
      boolean rs;
      try {
        rs = accRep.insert_account(account);
        if (rs) {
          resp.setStatus(HttpServletResponse.SC_CREATED);
        }
      } catch (Exception e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
          resp.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
          throw new RuntimeException(e);
        }

      }
    }
    if (action.equalsIgnoreCase("test")) {
      reader = req.getReader();
      jsonBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        jsonBuilder.append(line);
      }
      Account acc = gson.fromJson(jsonBuilder.toString(), Account.class);
      System.out.println(acc);


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
    System.out.println("ID Delete: "+jsonBuilder.toString());
    try {
      if(accRep.delete_account(jsonBuilder.toString())){
        resp.setStatus(HttpServletResponse.SC_OK);
      }
      else{
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


  }
}
