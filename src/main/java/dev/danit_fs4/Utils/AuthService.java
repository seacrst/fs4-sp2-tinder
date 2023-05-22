package dev.danit_fs4.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class AuthService {
    public static String cookieName = "UUID";
    public static Optional<String> getCookie(HttpServletRequest req){
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        return Stream.of(cookies)
                .filter(c -> c.getName().equals(cookieName))
                .map(Cookie::getValue)
                .findFirst();
    }
    public static String addCookie(HttpServletResponse resp){
        Cookie cookie = new Cookie("UUID", UUID.randomUUID().toString());
        resp.addCookie(cookie);
        return cookie.getValue();
    }
    public static void deleteCookie(HttpServletResponse resp){
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
