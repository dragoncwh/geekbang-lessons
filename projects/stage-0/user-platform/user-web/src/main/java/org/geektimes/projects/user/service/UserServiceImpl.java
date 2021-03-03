package org.geektimes.projects.user.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

  // private UserRepository userRepository = new InMemoryUserRepository();
  private UserRepository userRepository = initUserRepository();

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

  private UserRepository initUserRepository() {
    // Properties props = new Properties();
    // props.put("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");
    // props.setProperty("java.naming.provider.url", "rmi://server:1099");

    try {
      // Context context = new InitialContext(props);
      Context context = new InitialContext();
      DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/UserPlatformDB");
      Connection connection = dataSource.getConnection();
      initDB(connection);
      DBConnectionManager dbConnectionManager = new DBConnectionManager();
      dbConnectionManager.setConnection(connection);
      return new DatabaseUserRepository(dbConnectionManager);
    } catch (Exception e) {
      e.printStackTrace();
      // return new InMemoryUserRepository();
      return null;
    }
  }

  private void initDB(Connection connection) throws Exception {
    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null,null, "USERS",null);
    // if (!rs.next()) {
    //   Statement statement = connection.createStatement();
    //   statement.executeUpdate(DBConnectionManager.CREATE_USERS_TABLE_DDL_SQL);
    // }
    if (rs.next()) {
      Statement statement = connection.createStatement();
      statement.execute(DBConnectionManager.DROP_USERS_TABLE_DDL_SQL);
      statement.execute(DBConnectionManager.CREATE_USERS_TABLE_DDL_SQL);
    } else {
      Statement statement = connection.createStatement();
      statement.execute(DBConnectionManager.CREATE_USERS_TABLE_DDL_SQL);
    }
  }
}
