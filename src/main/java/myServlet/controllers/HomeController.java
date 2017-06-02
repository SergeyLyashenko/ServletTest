package myServlet.controllers;

import myServlet.Factory;
import myServlet.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HomeController implements Controller{

    public ViewModel process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String query = "SELECT NAME, DESCRIPTION FROM CATEGORY";

        ResultSet resultSet = Factory.getConnection().createStatement().executeQuery(query);
        Map<String,String> categories = new HashMap<>();
        while (resultSet.next()){
            categories.put(resultSet.getString("name"),resultSet.getString("description"));
        }
        for (Map.Entry<String,String>  s : categories.entrySet()) {
            System.out.println(s.getKey());
            System.out.println(s.getValue());
        }

        request.setAttribute("categories",categories);
        return new ViewModel("home");

    }
}
