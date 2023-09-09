package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.Account;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("insert_account")) {
            PrintWriter out = resp.getWriter();
            String us = req.getParameter("us");
            String pwd = req.getParameter("us");
            String email = req.getParameter("us");
            String phone = req.getParameter("us");
            int status = 1;
            String[] last_name_to_id = us.split("\\s");
            String id = last_name_to_id[last_name_to_id.length-1];

            Account account = new Account(id,us,pwd,email,phone,status);
            out.println(account);





        }

    }
}
