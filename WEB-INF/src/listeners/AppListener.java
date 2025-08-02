package listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import models.Role;
import models.Category;
import utils.DBConnect;

@WebListener
public class AppListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent ev) {
        ServletContext context = ev.getServletContext();

        DBConnect.dbURL = context.getInitParameter("dburl");
        DBConnect.dbUser = context.getInitParameter("dbuser");
        DBConnect.dbPassword = context.getInitParameter("dbpassword");

        List<Role> roles = Role. getAllRoles(); 
        context.setAttribute("roles", roles);

        List<Category> categoryList = Category.getAllCategories(); 
        context.setAttribute("categories", categoryList);

        
    }

    public void contextDestroyed(ServletContextEvent ev) {

    }
}