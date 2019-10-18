package servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin")
public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String role = (String) req.getSession().getAttribute("role");
        System.out.println(role);
        if (role.equals("admin")) {
            req.getRequestDispatcher("/admin").forward(req, resp);
        } else {
            req.setAttribute("message", "У вас нет прав доступа");
            req.getRequestDispatcher("/view/result.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
