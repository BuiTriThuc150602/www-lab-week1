package vn.edu.iuh.fit.controllers;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

  private final AccountRepository accRep;

  public ControllerServlet() throws Exception {
    accRep = new AccountRepository();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");
    if (action.equalsIgnoreCase("getall")) {
      List<Account> list = accRep.getAllAccount();
      String jsonData = new Gson().toJson(list);
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      resp.getWriter().write(jsonData);
    }


  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String action = req.getParameter("action");
    if (action.equalsIgnoreCase("insert_account")) {
      PrintWriter out = resp.getWriter();
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
  }
}
