package dev.danit_fs4.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HTTPFilter extends Filter {
    @Override
    default void init(FilterConfig filterConfig) throws ServletException {    }

    @Override
    default void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(isHTTP(request, response)){
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            doHTTPFilter(req, resp, chain);
        } else{
            chain.doFilter(request, response);
        }

    }
    void doHTTPFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;
    private boolean isHTTP(ServletRequest request, ServletResponse response){
        return request instanceof HttpServletRequest &&
                response instanceof HttpServletResponse;
    }
    @Override
    default void destroy() {    }
}
