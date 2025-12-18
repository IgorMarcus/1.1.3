package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/kata_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root123";

    public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);

    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
                sessionFactory = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/kata_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true")
                        .setProperty("hibernate.connection.username", "root")
                        .setProperty("hibernate.connection.password", "root123")
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                        .setProperty("hibernate.hbm2ddl.auto", "none")
                        .setProperty("hibernate.show_sql", "true")
                        .buildSessionFactory();
            }
        return sessionFactory;
        }
    }



