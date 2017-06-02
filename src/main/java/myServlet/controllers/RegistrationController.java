package myServlet.controllers;


import myServlet.ViewModel;
import myServlet.model.User;
import myServlet.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Random;

public class RegistrationController implements Controller {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    public ViewModel process(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        /*Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            randomString.append((char) (random.nextInt(74) + 48));
        }

        User user = new User();

        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(DigestUtils.md5Hex(request.getParameter("password")));
        user.setHash(DigestUtils.md5Hex(randomString.toString()));






        userService.save(user);*/

        return new ViewModel("postProcPage");
    }
}
