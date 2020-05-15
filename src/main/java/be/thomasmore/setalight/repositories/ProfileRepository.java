package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {
}
