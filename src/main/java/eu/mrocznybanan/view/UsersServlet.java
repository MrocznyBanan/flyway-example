package eu.mrocznybanan.view;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.mrocznybanan.service.boundry.UsersService;
import eu.mrocznybanan.service.entity.User;

@WebServlet(name = "usersServlet", urlPatterns = "/users", loadOnStartup = 1)
public class UsersServlet extends HttpServlet {

    private static final long serialVersionUID = -6120164521499856300L;

    @Inject
    private UsersService usersService;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = usersService.findAll();
        if (users.isEmpty()) {
            resp.getWriter().append("no users");
            return;
        }

        resp.getWriter().append(users.size() + " users:\n");
        for (User user : users) {
            resp.getWriter().append(String.format("- %s %s\n", user.getFirstName(), user.getLastName()));
        }
    }

}
