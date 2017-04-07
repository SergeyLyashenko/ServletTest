package myServlet;

import myServlet.controllers.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static myServlet.Factory.getConnection;
import static myServlet.Factory.getUserDaoImpl;
import static myServlet.Factory.getUserServiceImpl;

public class MainServlet extends HttpServlet {

    private final Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        controllerMap.put(new Request("GET", "/"), Factory.getHomeController());
        controllerMap.put(new Request("GET", "/login"), Factory.getLoginController());
        controllerMap.put(new Request("POST", "/postProcPage"), Factory.getRegistrationController(
                getUserServiceImpl(
                        getUserDaoImpl(
                                getConnection()))));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        handleRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        Request request = new Request(servletRequest.getMethod(), servletRequest.getRequestURI());
        Controller controller = controllerMap.get(request);

        try {
            if (controller != null) {
                servletRequest.getRequestDispatcher(getView(controller.process(servletRequest, servletResponse)))
                        .forward(servletRequest, servletResponse);
            } else {
                servletRequest.getRequestDispatcher("/WEB-INF/error.jsp").forward(servletRequest, servletResponse);
            }
        } catch (ServletException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private String getView(ViewModel view) {
        String prefix = "/WEB-INF/";
        String suffix = ".jsp";
        return prefix + view.getView() + suffix;
    }
}