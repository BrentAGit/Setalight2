package be.thomasmore.setalight.repositories;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;

public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findAllByControl(boolean control);

    Optional<Event> findById(int id);

    List<Event> findAllByUsersAndDateBefore(User user, Date date);

    @Query("select e from Event e where e.date >= :currentDate")
    List<Event> findAllByDateAfter(
            @Param("currentDate") Date currentDate);

    List<Event> findAllByDateBetween(Date firstDate, Date secondDate);

    @Query("select e from Event e where e.date >= :currentDate and e.createdBy = :user ")
    List<Event> findAllByDateAfterAndUsers(
            @Param("currentDate") Date currentDate,
            @Param("user") User user);

    List<Event> findAllByUsersAndDateAfter(User user, Date date);

    List<Event> findAllByUsers(User user);

    @Query("select e from Event e where e.createdBy = :user ")
    Optional<User> findCreatedBy( @Param("user") User user);

    List<Event> findAllByCreatedBy(User user);
}
