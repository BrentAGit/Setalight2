package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.ProductiehuisProfile;
import be.thomasmore.setalight.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductiehuisProfileRepository extends CrudRepository<ProductiehuisProfile,Integer> {

    Optional<ProductiehuisProfile> findByUserId(User user);

    List<ProductiehuisProfile> findAllByVerified(boolean verified);

}
