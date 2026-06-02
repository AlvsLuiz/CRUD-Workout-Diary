package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        HttpServletResponse resp =
                (HttpServletResponse) response;

        String uri =
                req.getRequestURI();

        boolean recursoPublico =

                uri.endsWith("login.jsp")
             || uri.endsWith("register.jsp")
             || uri.contains("/login")
             || uri.contains("/register")
             || uri.contains("/css/")
             || uri.contains("/js/")
             || uri.contains("/images/")
             || uri.contains("bootstrap");

        if (recursoPublico) {

            chain.doFilter(request, response);
            return;
        }

        HttpSession session =
                req.getSession(false);

        boolean logado =
                session != null
                && session.getAttribute(
                        "usuario_logado")
                   != null;

        if (logado) {

            chain.doFilter(request, response);

        } else {

            resp.sendRedirect(
                    req.getContextPath()
                    + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
    }
}