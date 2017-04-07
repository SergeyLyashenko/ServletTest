package myServlet.dao;

import myServlet.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao{

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public User save(User user) throws SQLException {
        String name = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();


        String query = "INSERT INTO USERS VALUES ('" + name + "', '" + password + "', '" + email + "')";


        connection.createStatement().execute(query);
        connection.close();


        return user;
    }

    public User getByName(User user) {
        return null;
    }

    public User remove(User user) {
        return null;
    }

    public User update(User user) {
        return null;
    }
}
