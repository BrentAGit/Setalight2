package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
