package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {

            Transaction transaction = session.beginTransaction();
            String SQL = """
                    CREATE TABLE IF NOT EXISTS user\s
                    (id int NOT NULL AUTO_INCREMENT,
                    name varchar(15),
                    lastname varchar(25),
                    age int,
                    PRIMARY KEY (id)
                    );""";
            session.createSQLQuery(SQL).executeUpdate();
            transaction.commit();

        } catch (HibernateException ignored) {
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {

            Transaction transaction = session.beginTransaction();
            String SQL = "DROP TABLE IF EXISTS user";
            session.createSQLQuery(SQL).executeUpdate();
            transaction.commit();

        } catch (HibernateException ignored) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {

            Transaction transaction = session.beginTransaction();

            Query query = session.createSQLQuery("INSERT INTO user(name, lastname, age) VALUES (?,?,?)");
            query.setParameter(1, name);
            query.setParameter(2, lastName);
            query.setParameter(3, age);

            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException ignored) {
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {

            Transaction transaction = session.beginTransaction();

            Query query = session.createSQLQuery("DELETE FROM user WHERE id=?");
            query.setParameter(1, id);

            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException ignored) {
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.getCurrentSession()) {

            Transaction transaction = session.beginTransaction();
            String SQL = "SELECT * FROM user";
            users = session.createSQLQuery(SQL).addEntity(User.class).list();
            transaction.commit();

        } catch (HibernateException ignored) {
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {

            Transaction transaction = session.beginTransaction();
            String SQL = "TRUNCATE user";
            session.createSQLQuery(SQL).executeUpdate();
            transaction.commit();

        } catch (HibernateException ignored) {
        }
    }
}
