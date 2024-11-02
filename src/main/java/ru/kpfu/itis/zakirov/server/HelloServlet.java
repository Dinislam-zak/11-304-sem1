package ru.kpfu.itis.zakirov.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        StringBuilder parameters = new StringBuilder("GET request received with parameters: ");
        if (parameterMap.isEmpty()) {
            parameters.append("No parameters received.");
        } else {
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                parameters.append(entry.getKey()).append("=");
                parameters.append(String.join(", ", entry.getValue())).append("; ");
            }
        }
        writer.println(parameters);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        writer.println(requestBody);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        writer.println(requestBody);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("DELETE request received with parameters: " + req.getParameterMap().toString());
    }
}
