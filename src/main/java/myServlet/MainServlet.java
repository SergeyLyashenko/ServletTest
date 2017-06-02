package myServlet;

import myServlet.controllers.CategoryController;
import myServlet.controllers.Controller;
import myServlet.controllers.VerificationController;
import myServlet.model.User;

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
        controllerMap.put(new Request("GET", "/category"), Factory.getCategoryController());
        controllerMap.put(new Request("POST", "/dddd"), Factory.getRegistrationController(
                getUserServiceImpl(
                        getUserDaoImpl(
                                getConnection()))));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        try {

            user = VerificationController.isLogin(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            request.setAttribute("user",user);
            if(request.getRequestURI().length()>1&&!request.getRequestURI().equals("/favicon.ico")){
                try {
                    request.getRequestDispatcher(getView(new CategoryController().process(request,response))).forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {

                handleRequest(request, response);
            }
        }


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
                System.out.println(servletRequest.getRequestURI());
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