package servlet.admin;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        System.out.println(login);
        User user = userService.getUserByLogin(login);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/view/admin/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String pass = req.getParameter("password");
        int id = Integer.parseInt(req.getParameter("id"));

        System.out.println(id + " " + name  + " " + pass + " " );
        if (userService.updateUser(id, name, pass)) {
            req.setAttribute("message", "User is changed!");
            resp.setStatus(200);
        } else {
            req.setAttribute("message", "Fall. User not changed!!");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        req.getRequestDispatcher("/view/result.jsp").forward(req, resp);
    }
}