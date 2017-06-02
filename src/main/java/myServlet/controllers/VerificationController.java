package myServlet.controllers;

import myServlet.Factory;
import myServlet.ViewModel;
import myServlet.dao.UserDao;
import myServlet.dao.UserDaoImpl;
import myServlet.model.User;
import myServlet.services.UserServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class VerificationController {

    public static User isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            User requestUser = new User();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name") && cookie.getValue().length() > 32) {
                    requestUser.setUsername(cookie.getValue().substring(32));
                    requestUser.setHash(cookie.getValue().substring(0, 32));
                }
            }

            if (requestUser.getUsername() != null) {
                User daoUser = new UserServiceImpl(new UserDaoImpl(Factory.getConnection())).getByName(requestUser);
                if (requestUser.getHash().equals(daoUser.getHash())) {
                    return daoUser;
                } else {
                    response.sendRedirect("login");
                    System.out.println("333333333333333");
                    return null;
                }

            } else {
                response.sendRedirect("login");
                System.out.println("2222222222222222");
                return null;
            }
        } else {
            response.sendRedirect("login");
            System.out.println("11111111111");
            return null;
        }
    }
}
