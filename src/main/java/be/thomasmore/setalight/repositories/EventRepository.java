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

    List<Event> findAllByControl(boolean control);

    Optional<Event> findById(int id);

    List<Event> findAllByUsersAndDateBefore(User user, Date date);

    @Query("select e from Event e where e.date >= :currentDate")
    List<Event> findAllByDateAfter(
            @Param("currentDate") Date currentDate);

    List<Event> findAllByDatumBetween(Date firstDate, Date secondDate);

    @Query("select e from Event e where e.datum >= :currentDate ")
    List<Event> findAllByDateAfterAndUsers(
            @Param("currentDate") Date currentDate);
    List<Event> findAllByUsersAndDateAfter(User user, Date date);
}
