package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.Reward;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RewardRepository extends CrudRepository<Reward, Integer> {
    Optional<Reward> findById(Integer Id);
}
