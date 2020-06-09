package com.infoshareacademy.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        filterName = "PageNumberFilter",
        urlPatterns = {"/list"}
        )
public class InitialPageNumberFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       
        String reqParameter = servletRequest.getParameter("page");
        if (reqParameter == null || reqParameter.isEmpty() ) {

            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect("/list?page=1");

            return;

        } else if (!reqParameter.matches("[1-9]+[0-9]*") || Integer.valueOf(reqParameter) <= 0 ){
            servletRequest.setAttribute("page", 1);
        }

        filterChain.doFilter(servletRequest, servletResponse);

        return;
    }



}
