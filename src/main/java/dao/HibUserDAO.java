package dao;

import entity.User;
import exception.EmailIsTakenException;
import exception.FailedRegistrationException;
import exception.LoginIsTakenException;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.annotation.Nullable;

public final class HibUserDAO implements UserDAO {

    @Override
    @Nullable
    public User getByLogin(String name) {
        User user;
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, name);
            transaction.commit();
        }
        return user;
    }

    @Override
    public void removeByLogin(String name) {
        try (Session session = HibernateUtils.getSession()) {
            session.createQuery("DELETE FROM User where login = :name")
                    .setParameter("name", name)
                    .executeUpdate();
        }
    }

    @Override
    public void register(User user) throws FailedRegistrationException {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            if (userExists(user.getLogin())) {
                transaction.commit();
                throw new LoginIsTakenException();
            } else if (emailExists(user.getEmail())) {
                transaction.commit();
                throw new EmailIsTakenException();
            }
            session.save(user.getGameStatistics());
            session.save(user);
            transaction.commit();
        } catch (Throwable t) {
            FailedRegistrationException e = new FailedRegistrationException();
            e.initCause(t);
            throw e;
        }
    }

    private boolean userExists(String login) {
        return getByLogin(login) != null;
    }

    private boolean emailExists(String email) {
        try (Session session = HibernateUtils.getSession()) {
            Query<String> emailQuery = session.createQuery(
                    "SELECT email FROM User WHERE email = :email", String.class);
            emailQuery.setParameter("email", email);
            String fetchedEmail = emailQuery.uniqueResult();
            return fetchedEmail == null;
        }
    }
}
