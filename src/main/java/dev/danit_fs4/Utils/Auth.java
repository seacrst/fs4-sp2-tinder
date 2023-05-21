package dev.danit_fs4.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Stream;

public class Auth {
    public static String cookieName = "UUID";
    public static Optional<String> getCookie(HttpServletRequest req){
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        return Stream.of(cookies)
                .filter(c -> c.getName().equals(cookieName))
                .map(Cookie::getValue)
                .findFirst();
    }
}
