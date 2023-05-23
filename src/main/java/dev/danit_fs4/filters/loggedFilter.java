package dev.danit_fs4.filters;

import dev.danit_fs4.utils.Auth;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loggedFilter implements HTTPFilter {

    @Override
    public void doHTTPFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(Auth.getCookie(request).isPresent()){
            response.sendRedirect("/users");
        } else{
            chain.doFilter(request, response);
        }
    }
}
