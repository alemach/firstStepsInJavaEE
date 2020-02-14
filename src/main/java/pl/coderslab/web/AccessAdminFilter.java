package pl.coderslab.web;

import pl.coderslab.model.Admin;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/admin/*")
public class AccessAdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        if (session.getAttribute("admin")!=null) {
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin.getSuperadmin() == 0) {
                ((HttpServletResponse) resp).sendRedirect(request.getContextPath() + "/app/dashboard");
                return;

            } else {
                chain.doFilter(req, resp);
            }
        } else ((HttpServletResponse) resp).sendRedirect(request.getContextPath() + "/");
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
