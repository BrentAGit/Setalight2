package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findAllByControle(boolean controle);

    Optional<Event> findById(int id);

    List<Event> findAllByUsersAndDatumBefore(User user, Date date);
    @Query("select e from Event e where e.datum >= :currentDate")
    List<Event> findAllByDateAfter(
            @Param("currentDate") Date currentDate);
}
