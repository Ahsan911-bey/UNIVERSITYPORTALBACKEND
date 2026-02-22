package com.universityportal.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        // Extract the Origin from the request
        String origin = request.getHeader("Origin");

        System.out.println("====== GLOBAL CORS FILTER HIT ======");
        System.out.println("Method: " + request.getMethod());
        System.out.println("Origin: " + origin);
        System.out.println("URI: " + request.getRequestURI());

        // Blindly reflect the origin if it exists to avoid any string matching issues
        // with proxies
        if (origin != null && !origin.isEmpty()) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            // Fallback for strictly local/direct testing without an origin
            response.setHeader("Access-Control-Allow-Origin", "https://cui-uni-portal.vercel.app");
        }

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");

        // Dynamically reflect whatever requested headers the browser wants
        String requestHeaders = request.getHeader("Access-Control-Request-Headers");
        if (requestHeaders != null && !requestHeaders.isEmpty()) {
            response.setHeader("Access-Control-Allow-Headers", requestHeaders);
            System.out.println("Reflected Headers: " + requestHeaders);
        } else {
            response.setHeader("Access-Control-Allow-Headers",
                    "Authorization, Content-Type, Accept, X-Requested-With, remember-me");
        }

        // If it's a preflight OPTIONS request, stop here and return 200 OK immediately
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            System.out.println("====== RETURNED 200 OK FOR OPTIONS =====");
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
