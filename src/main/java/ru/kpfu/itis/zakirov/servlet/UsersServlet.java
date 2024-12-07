package ru.kpfu.itis.zakirov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.zakirov.dao.UserDao;
import ru.kpfu.itis.zakirov.dao.impl.UserDaoImpl;
import ru.kpfu.itis.zakirov.entity.User;

import java.io.IOException;


@WebServlet(name = "usersServlet", urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class);
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userDao.getAll());

        for (User user : userDao.getAll()) {
            LOG.info("New user: {} with lastname {} has been created", user.getName(), user.getLastname());
        }

        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }
}
