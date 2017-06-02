package myServlet.services;

import myServlet.model.User;

import java.sql.SQLException;

public interface UserService {
    User save(User user) throws SQLException;

    User getByName(User user) throws SQLException;

    User remove(User user);

    User update(User user);

}
