package dao;

import entity.User;
import exception.FailedRegistrationException;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.Nullable;

public final class HibUserDAO implements UserDAO{

    @Override
    @Nullable
    public User getByLogin(String name){
        User user;
        try(Session session = HibernateUtils.getSession()){
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, name);
            transaction.commit();
        }
        return user;
    }

    @Override
    public void removeByLogin(String name) {
        try(Session session = HibernateUtils.getSession()){
            session.createQuery("DELETE FROM User where login = :name")
                    .setParameter("name", name)
                    .executeUpdate();
        }
    }

    @Override
    public void register(User user) throws FailedRegistrationException {
        try(Session session = HibernateUtils.getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (Throwable t){
            FailedRegistrationException e = new FailedRegistrationException();
            e.initCause(t);
            throw e;
        }
    }
}
