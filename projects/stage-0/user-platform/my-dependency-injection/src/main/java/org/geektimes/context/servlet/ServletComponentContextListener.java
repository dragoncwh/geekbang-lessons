package org.geektimes.context.servlet;

import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.geektimes.context.JndiComponentContext;

public class ServletComponentContextListener implements ServletContextListener {

  @Override
  @PreDestroy
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext servletContext = sce.getServletContext();
    JndiComponentContext context = new JndiComponentContext();
    context.init(servletContext);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}
