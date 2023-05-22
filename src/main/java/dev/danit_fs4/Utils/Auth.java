package dev.danit_fs4.Utils;

import dev.danit_fs4.DAO.UserDatabaseDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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

    public static void logout(HttpServletRequest req, HttpServletResponse res, UserDatabaseDao user) throws SQLException, IOException {
        Optional<String> cookie = getCookie(req);

        if (cookie.isEmpty()) return;

        user.updateCookie(cookie.get());
        res.sendRedirect("/login");
    }
}
