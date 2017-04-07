package myServlet.controllers;


import myServlet.ViewModel;
import myServlet.model.User;
import myServlet.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrationController implements Controller{

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    public ViewModel process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        User user = new User();

        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));

        userService.save(user);

        return new ViewModel("postProcPage");
    }
}
