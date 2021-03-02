package org.geektimes.projects.user.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

@Path("/login")
public class LoginController implements PageController {

  private UserService userService = new UserServiceImpl();

  @GET
  @POST
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    if (request.getMethod().equalsIgnoreCase(HttpMethod.GET)) {
      return doGet(request, response);
    } else if (request.getMethod().equalsIgnoreCase(HttpMethod.POST)) {
      return doPost(request, response);
    }
    response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    return "error.jsp";
  }

  private String doGet(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    return "login-form.jsp";
  }

  private String doPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    User user = userService.queryUserByNameAndPassword(username, password);
    if (user == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return "error.jsp";
    }

    Cookie usernameCookie = new Cookie("username", username);
    response.addCookie(usernameCookie);

    request.setAttribute("loginname", username);
    return "index.jsp";
  }
}
