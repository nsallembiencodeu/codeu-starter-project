package com.google.codeu.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.JsonObject;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Returns login data as JSON, e.g. {"isLoggedIn": true, "username": "Ada"}
 */
@WebServlet("/login-status")
public class LoginStatusServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    JsonObject jsonObject = new JsonObject();

    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      jsonObject.addProperty("isLoggedIn", true);
      jsonObject.addProperty("username", userService.getCurrentUser().getEmail());
    } else {
      jsonObject.addProperty("isLoggedIn", false);
    }

    response.setContentType("application/json");
    response.getOutputStream().println(jsonObject.toString());
  }
}
