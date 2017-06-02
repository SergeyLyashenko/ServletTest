package myServlet.controllers;


import myServlet.Factory;
import myServlet.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoryController implements Controller {

    public ViewModel process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String category = request.getRequestURI().substring(1);
        String query = "SELECT PRODUCTS.NAME, PRODUCTS.DESCRIPTION FROM PRODUCTS " +
                "JOIN CATEGORY ON CATEGORY.ID=CATEGORY_ID " +
                "WHERE CATEGORY.NAME='" + category + "'";


        ResultSet resultSet = Factory.getConnection().createStatement().executeQuery(query);
        Map<String, String> products = new HashMap<>();
        while (resultSet.next()) {
            products.put(resultSet.getString("name"), resultSet.getString("description"));
        }


        for (Map.Entry<String, String> s : products.entrySet()) {
            System.out.println(s.getKey());
            System.out.println(s.getValue());
        }

        request.setAttribute("products", products);
        request.setAttribute("category", category);
        return new ViewModel("category");
    }
}
