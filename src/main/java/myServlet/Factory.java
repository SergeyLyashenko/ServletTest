package myServlet;

import myServlet.controllers.Controller;
import myServlet.controllers.HomeController;
import myServlet.controllers.LoginController;
import myServlet.controllers.RegistrationController;
import myServlet.dao.UserDao;
import myServlet.dao.UserDaoImpl;
import myServlet.services.UserService;
import myServlet.services.UserServiceImpl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

    public static Controller getHomeController() {
        return new HomeController();
    }

    public static Controller getLoginController() {
        return new LoginController();
    }

    public static Controller getRegistrationController(UserService userService) {
        return new RegistrationController(userService);
    }

    public static UserService getUserServiceImpl(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

    public static UserDao getUserDaoImpl(Connection connection) {
        return new UserDaoImpl(connection);
    }

    public static Connection getConnection() {
        String userName = "sa";
        String password = "";
        String url = "jdbc:h2:tcp://localhost/~/test";
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException | ClassNotFoundException  e) {
            e.printStackTrace();
            System.out.println("sdfasdfasdfasdgsdfgsdfgsdfg5879765464");
        }


        return connection;

    }
}
