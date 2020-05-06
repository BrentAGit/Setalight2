package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(int id);

    List<User> findUserByRoleAndVerified(String role, boolean verified);
}
