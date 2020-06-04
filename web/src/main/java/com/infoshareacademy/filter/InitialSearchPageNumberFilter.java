package com.infoshareacademy.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        filterName = "SearchPageNumberFilter",
        urlPatterns = {"/search"},
        initParams = {
                @WebInitParam(name = "page", value = "1")
        })
public class InitialSearchPageNumberFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

    }


    @Override
    public void destroy() {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       
        String reqParameter = servletRequest.getParameter("page");
        if (reqParameter == null || reqParameter.isEmpty()) {

            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/search?page=1");
            requestDispatcher.forward(servletRequest, servletResponse);

        }

        filterChain.doFilter(servletRequest, servletResponse);


    }



}
