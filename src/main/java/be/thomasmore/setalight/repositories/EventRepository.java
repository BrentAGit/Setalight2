package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.Event;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findAllByControle(boolean controle);
}
