package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost/mydb";
    private static final String DB_USERNAME = "ilya";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            getConnection().close();
            System.out.println("Connection close");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory factory = null;
        try {
            Configuration configuration = new Configuration();
            Properties setting = new Properties();
            setting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            setting.put(Environment.URL, DB_URL);
            setting.put(Environment.USER, DB_USERNAME);
            setting.put(Environment.PASS, DB_PASSWORD);
            setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            setting.put(Environment.SHOW_SQL, "true");
            setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            setting.put(Environment.HBM2DDL_AUTO, "");
            configuration.setProperties(setting);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Factory OK");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Factory ERROR");
        }
        return factory;
    }

    public static void closeSessionFactory() {
        try {
            getSessionFactory().close();
            System.out.println("Factory CLOSE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
