package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findAllByControle(boolean controle);
    @Query("select e from Event e where e.datum >= :currentDate")
    List<Event> findAllByDateAfter(
            @Param("currentDate") Date currentDate);
}
