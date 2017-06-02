package myServlet.dao;

import myServlet.model.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public User save(User user) throws SQLException {
        String name = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String hash = user.getHash();


        String query = "INSERT INTO USERS(USERNAME, PASSWORD, EMAIL, HASH, ROLE) VALUES ('" + name + "', '" + password + "', '" + email + "', '" + hash + "', 'user')";


        connection.createStatement().execute(query);
        connection.close();


        return user;
    }

    public User getByName(User requestUser) throws SQLException {
        String query = "SELECT * FROM USERS WHERE USERNAME = '" + requestUser.getUsername() + "'";
        ResultSet resultSet = connection.createStatement().executeQuery(query);




        User daoUser = new User();
        if (resultSet.next()){

            daoUser.setHash(resultSet.getString("HASH"));
            daoUser.setUsername(resultSet.getString("USERNAME"));
            daoUser.setPassword(resultSet.getString("PASSWORD"));
            daoUser.setRole(resultSet.getString("ROLE"));

        }
        return daoUser;
    }

    public User remove(User user) {
        return null;
    }

    public User update(User user) {
        return null;
    }
}
