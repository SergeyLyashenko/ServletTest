package myServlet.controllers;

import myServlet.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller{

    public ViewModel process(HttpServletRequest request, HttpServletResponse response) {
        return new ViewModel("home");
    }
}
