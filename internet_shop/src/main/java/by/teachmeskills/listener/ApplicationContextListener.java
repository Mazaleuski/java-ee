package by.teachmeskills.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        String url = ctx.getInitParameter("DBURL");
        String login = ctx.getInitParameter("DBUSER");
        String password = ctx.getInitParameter("DBPASSWORD");

        DBConnectionManager dbManager = new DBConnectionManager(url, login, password);
        ctx.setAttribute("DBManager", dbManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
        dbManager.closeConnection();
    }
}