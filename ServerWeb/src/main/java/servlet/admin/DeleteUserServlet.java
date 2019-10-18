package servlet.admin;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        if (userService.deleteUser(login)) {
            req.setAttribute("message", "User is Delete!");
            resp.setStatus(200);
        } else {
            req.setAttribute("message", "Fall. User not Delete!!");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        req.getRequestDispatcher("/view/result.jsp").forward(req, resp);
    }
}
