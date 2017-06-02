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


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getRequestURI().equals("/login")) {
            Cookie cookie = new Cookie("name","");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            System.out.println("servletlogindaaaaaaaaaaaaa");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            System.out.println("servletloginneeeeeeeeeeeeeeeee");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User requestUser = new User();
        requestUser.setUsername(request.getParameter("username"));
        requestUser.setPassword(DigestUtils.md5Hex(request.getParameter("password")));

        User daoUser = null;
        try {
            daoUser = new UserServiceImpl(new UserDaoImpl(Factory.getConnection())).getByName(requestUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (daoUser != null && daoUser.getPassword() != null && daoUser.getPassword().equals(requestUser.getPassword())) {
            response.addCookie(new Cookie("name", daoUser.getHash() + daoUser.getUsername()));
            //request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            response.sendRedirect("/");
        } else {
            ///dodelat
            request.setAttribute("error","Sorry, username and password do not match");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

    }
}
