package by.teachmeskills.shop.servlet;

import by.teachmeskills.shop.commands.BaseCommand;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.utils.CommandFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/shop")
public class ApplicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BaseCommand requestCommand = CommandFactory.defineCommand(req);
        String path;
        try {
            path = requestCommand.execute(req);
            req.getRequestDispatcher(path).forward(req, resp);
        } catch (CommandException e) {
            req.getRequestDispatcher(PagesPathEnum.LOGIN_PAGE.getPath()).forward(req, resp);
        }
    }
}
