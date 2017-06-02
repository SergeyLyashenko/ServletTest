package myServlet.services;

import myServlet.dao.UserDao;
import myServlet.model.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User save(User user) throws SQLException {
        return userDao.save(user);
    }

    public User getByName(User user) throws SQLException {
        return userDao.getByName(user);
    }

    public User remove(User user) {
        return null;
    }

    public User update(User user) {
        return null;
    }
}
