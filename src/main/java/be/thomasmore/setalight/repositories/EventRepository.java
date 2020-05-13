package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findAllByControle(boolean controle);

    Optional<Event> findById(int id);

    List<Event> findAllByUsersAndDatumBefore(User user, Date date);
}
