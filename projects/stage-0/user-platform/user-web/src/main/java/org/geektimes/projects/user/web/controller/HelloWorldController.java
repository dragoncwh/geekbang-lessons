package org.geektimes.projects.user.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.ws.rs.HttpMethod;
import org.apache.commons.io.IOUtils;
import org.geektimes.projects.user.domain.User;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/hello")
public class HelloWorldController implements PageController {

    @GET
    @POST
    @Path("/world") // /hello/world -> HelloWorldController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        if (request.getMethod().equals(HttpMethod.POST)) {
            try (
                InputStream is = request.getInputStream();
                Reader reader = new InputStreamReader(is, request.getCharacterEncoding())
            ) {
                ObjectMapper mapper = new ObjectMapper();
                User user = mapper.readValue(reader, User.class);
                System.out.println(user);
                request.setAttribute("login_name", user.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "index.jsp";
    }
}
