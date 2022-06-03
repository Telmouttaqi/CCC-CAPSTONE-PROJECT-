package ccc.data;

import ccc.models.Event;
import ccc.models.Performances;
import ccc.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();

//    @Transactional
//    List<User> findById(int userId);

    User findByUserId(int userId);

    User add(User user);

    boolean update(User user);

    @Transactional
    boolean deleteById(int userId);
}