package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.Profile;
import be.thomasmore.setalight.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {

    Optional<Profile> findByUserId(User user);

}
