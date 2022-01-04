package servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.GoodDao;
import service.GoodService;

/**
 * Application Lifecycle Listener implementation class MyContextListener
 * 
 *
 */
@WebListener
public class MyContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	GoodService service = new GoodService();
    	service.setMain(new GoodDao());
    	sce.getServletContext().setAttribute("service", service);
    }
	
}
