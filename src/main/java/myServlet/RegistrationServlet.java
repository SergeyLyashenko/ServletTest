package myServlet;

import myServlet.dao.UserDaoImpl;
import myServlet.model.User;
import myServlet.services.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;


public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/registration")) {
            System.out.println("daaaaaaaaaaaaa");
            request.getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
        } else {
            System.out.println("neeeeeeeeeeeeeeeee");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            randomString.append((char) (random.nextInt(74) + 48));
        }

        User user = new User();

        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(DigestUtils.md5Hex(request.getParameter("password")));
        user.setHash(DigestUtils.md5Hex(randomString.toString()));


        try {
            new UserServiceImpl(new UserDaoImpl(Factory.getConnection())).save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.addCookie(new Cookie("name", user.getHash() + user.getUsername()));
        //request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        response.sendRedirect("/");
    }
}
