package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event,Integer> {
}
