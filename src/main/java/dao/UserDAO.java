package dao;

import entity.User;
import exception.FailedRegistrationException;

public interface UserDAO {

    User getByLogin(String name);

    void removeByLogin(String name);

    void register(User user) throws FailedRegistrationException;

}
