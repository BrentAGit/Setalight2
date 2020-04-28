package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
