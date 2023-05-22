package dev.danit_fs4.filters;

import dev.danit_fs4.Utils.Auth;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements HTTPFilter {

    @Override
    public void doHTTPFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(Auth.getCookie(request).isPresent()){
            chain.doFilter(request, response);
        } else{
            response.sendRedirect("/login");
        }
    }
}
