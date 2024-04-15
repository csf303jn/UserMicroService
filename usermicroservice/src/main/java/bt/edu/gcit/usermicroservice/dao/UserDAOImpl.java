package bt.edu.gcit.usermicroservice.dao;

import bt.edu.gcit.usermicroservice.entity.User;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import bt.edu.gcit.usermicroservice.exception.UserNotFoundException;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public User save(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User findByID(int theId) {
        // Implement the logic to find a user by their ID in the database
        // and return the user object
        User user = entityManager.find(User.class, theId);
        return user;
    }

    @Override
    public List<User> findAll() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    // @Override
    // @Transactional
    // public User update(User user) {
    // return entityManager.merge(user);
    // }

    @Override
    public void deleteById(int theId) {
        // Implement the logic to delete a user by their ID from the database
        // find user by id
        User user = findByID(theId);
        // remove user
        entityManager.remove(user);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("from User where email =:email", User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        System.out.println(users.size());
        if (users.isEmpty()) {
            return null;
        } else {
            System.out.println(users.get(0) + "" + users.get(0).getEmail());
            return users.get(0);
        }
    }

    @Override
    public void updateUserEnabledStatus(int id, boolean enabled) {
        User user = entityManager.find(User.class, id);
        System.out.println(user);
        if (user == null) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        user.setEnabled(enabled);
        entityManager.persist(user);
    }

}
