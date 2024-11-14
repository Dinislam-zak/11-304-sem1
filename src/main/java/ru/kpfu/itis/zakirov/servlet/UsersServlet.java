package ru.kpfu.itis.zakirov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.zakirov.dto.UserDto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "usersServlet", urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDto> users = List.of(new UserDto("Atlas", 123, "AtlasPomidor"));
        req.setAttribute("users", users);

        for (UserDto user : users) {
            LOG.info("New user: {} with score {} and username {} has been created", user.getName(), user.getScore(), user.getLogin());
        }

        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }
}
