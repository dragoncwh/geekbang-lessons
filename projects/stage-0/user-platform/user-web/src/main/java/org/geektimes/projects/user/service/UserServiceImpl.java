package org.geektimes.projects.user.service;

import java.sql.Connection;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.InMemoryUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.sql.DBConnectionManager;

public class UserServiceImpl implements UserService {

  private UserRepository userRepository = new InMemoryUserRepository();
  // private UserRepository userRepository = initUserRepository();

  @Override
  public boolean register(User user) {
    return userRepository.save(user);
  }

  @Override
  public boolean deregister(User user) {
    return userRepository.deleteById(user.getId());
  }

  @Override
  public boolean update(User user) {
    return userRepository.update(user);
  }

  @Override
  public User queryUserById(Long id) {
    return userRepository.getById(id);
  }

  @Override
  public User queryUserByNameAndPassword(String name, String password) {
    return userRepository.getByNameAndPassword(name, password);
  }

  // private UserRepository initUserRepository() {
  //   Properties props = new Properties();
  //   props.put("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");
  //   // props.setProperty("java.naming.provider.url", "rmi://server:1099");
  //
  //   try {
  //     // Context context = new InitialContext(props);
  //     Context context = new InitialContext();
  //     DataSource dataSource = (DataSource) context.lookup("jdbc/UserPlatformDB");
  //     Connection connection = dataSource.getConnection();
  //     DBConnectionManager dbConnectionManager = new DBConnectionManager();
  //     dbConnectionManager.setConnection(connection);
  //     return new DatabaseUserRepository(dbConnectionManager);
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //     return new InMemoryUserRepository();
  //     // return null;
  //   }
  // }
}
