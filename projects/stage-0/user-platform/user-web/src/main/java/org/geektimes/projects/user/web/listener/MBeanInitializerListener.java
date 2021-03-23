package org.geektimes.projects.user.web.listener;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.management.UserManager;

public class MBeanInitializerListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      // 获取平台 MBean Server
      MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
      // 为 UserMXBean 定义 ObjectName
      ObjectName objectName = new ObjectName("org.geektimes.projects.user.management:type=User");
      // 创建 UserMBean 实例
      User user = new User();
      user.setName("小马哥");
      user.setPassword("******");
      user.setEmail("mercyblitz@gmail.com");
      user.setPhoneNumber("12345678910");
      mBeanServer.registerMBean(createUserMBean(user), objectName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Object createUserMBean(User user) throws Exception {
    return new UserManager(user);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {

  }
}
