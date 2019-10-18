package servlet.filter;

import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/login")
public class AuthFilter implements Filter {
    UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String role = null;

        HttpSession session = req.getSession();

        if (nonNull(session.getAttribute("role"))) {
            role = (String) session.getAttribute("role");
            moveToMenu(req, resp, role);
        } else if (userService.validateClient(login, pass)) {
            role = userService.getRole();

            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            moveToMenu(req, resp, role);
        } else {
            req.setAttribute("message", "Ошибка авторизации");
            req.getRequestDispatcher("/view/result.jsp").forward(req, resp);
        }

    }

    private void moveToMenu(HttpServletRequest req,
                            HttpServletResponse resp,
                            String role)
            throws IOException, ServletException {

        if (role.equals("admin")) {
            req.getRequestDispatcher("/admin").forward(req, resp);
        } else if (role.equals("user")) {
            req.getRequestDispatcher("/user").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
