package myServlet.dao;


import myServlet.model.User;

import java.sql.SQLException;

public interface UserDao {
    User save(User user) throws SQLException;

    User getByName(User user) throws SQLException;

    User remove(User user);

    User update(User user);
}
