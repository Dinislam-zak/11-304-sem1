package ru.kpfu.itis.zakirov.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "authFilter")
public class AuthenticationFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(TimingFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String uri = httpServletRequest.getRequestURI();
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null && !uri.contains("login")) {
            ((HttpServletResponse) response).sendRedirect("/login");
            LOG.info("user was not authenticated, login required");
        } else {
            chain.doFilter(request, response);
            LOG.info("user was successfully authenticated");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
