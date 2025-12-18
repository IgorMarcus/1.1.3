package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(255), " +
                            "lastName VARCHAR(255), " +
                            "age TINYINT)"
            ).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS            ллддддддддддбб      users").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("DELETE FROM users WHERE id = ?")
                    .setParameter(1, id)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            List<Object[]> rows = session.createNativeQuery("SELECT name, lastName, age FROM users").list();
            tx.commit();

            List<User> users = new ArrayList<>();
            for (Object[] row : rows) {
                users.add(new User((String) row[0], (String) row[1], (Byte) row[2]));
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("DELETE FROM users").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
