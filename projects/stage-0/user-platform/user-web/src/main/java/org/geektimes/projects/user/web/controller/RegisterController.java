package org.geektimes.projects.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.geektimes.context.ComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

@Path("/register")
public class RegisterController implements PageController {

  private UserService userService = ComponentContext.getInstance().getComponent("bean/UserService");

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
    return "register.jsp";
  }

  private String doPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");

    User user = new User();
    user.setName(username);
    user.setEmail(email);
    user.setPassword(password);
    user.setPhoneNumber(phone);

    try {
      if (userService.register(user)) {
        return "register-success.jsp";
      }
    } catch (ConstraintViolationException e) {
      StringBuilder msgBuilder = new StringBuilder();
      for (ConstraintViolation<?> v : e.getConstraintViolations()) {
          msgBuilder.append(v.getMessage() + ";");
      }
      request.setAttribute("error_msg", msgBuilder.toString());
    }

    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    return "error.jsp";

    // if (userService.register(user)) {
    //   return "register-success.jsp";
    // } else {
    //   response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    //   return "error.jsp";
    // }
  }
}
