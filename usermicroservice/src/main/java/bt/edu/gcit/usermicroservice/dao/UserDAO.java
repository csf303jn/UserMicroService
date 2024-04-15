package bt.edu.gcit.usermicroservice.dao;

import java.util.List;
import bt.edu.gcit.usermicroservice.entity.User;

public interface UserDAO {
    User save(User user);
    User findByID(int theId);
    List<User> findAll();
    // User update(User user);
    void deleteById(int theId);
    User findByEmail(String email);
    void updateUserEnabledStatus(int id, boolean enabled);
}
