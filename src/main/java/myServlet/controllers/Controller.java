package myServlet.controllers;


import myServlet.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Controller {
    ViewModel process(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
