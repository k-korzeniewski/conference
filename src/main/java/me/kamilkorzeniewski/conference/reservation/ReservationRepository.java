package me.kamilkorzeniewski.conference.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Integer> {
    List<Reservation> findAllByLectureId(int lectureId);
    List<Reservation> findAllByUserId(int userId);
}
