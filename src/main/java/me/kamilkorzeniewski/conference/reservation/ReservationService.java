package me.kamilkorzeniewski.conference.reservation;

import me.kamilkorzeniewski.conference.reservation.exception.TicketsNotAvailableException;
import me.kamilkorzeniewski.conference.reservation.exception.TimeConflictException;
import me.kamilkorzeniewski.conference.schedule.lecture.Lecture;
import me.kamilkorzeniewski.conference.schedule.lecture.LectureService;
import me.kamilkorzeniewski.conference.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    Reservation createReservation(Reservation reservation) {
        if (!isAvailableTicket(reservation.getLectureId()))
            throw new TicketsNotAvailableException();
        if (isTimeConflict(reservation))
            throw new TimeConflictException();
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservationsByUser(User user){
        return reservationRepository.findAllByUserId(user.getId());
    }

    public void removeReservation(Reservation reservation){
        reservationRepository.delete(reservation);
    }
    public Optional<Reservation> getReservationById(int id){
        return reservationRepository.findById(id);
    }

    private boolean isAvailableTicket(int lectureId) {
        List<Reservation> reservationList = reservationRepository.findAllByLectureId(lectureId);
        int ticketsPool = LectureService.getById(lectureId).map(Lecture::getTickets).orElse(0);
        int ticketsLeft = ticketsPool - reservationList.size();
        return ticketsLeft > 0;
    }

    private boolean isTimeConflict(Reservation reservation) {
        Lecture reservationLecture = LectureService.getById(reservation.getLectureId()).orElseThrow(IllegalAccessError::new);
        Stream<Lecture> userLecturesStream = reservationRepository.findAllByUserId(reservation.getUserId()).stream()
                .map(res -> LectureService.getById(res.getLectureId())).filter(Optional::isPresent).map(Optional::get);
        long equalTimeReservationCount = userLecturesStream.filter(lecture -> lecture.isStartTimeEqual(reservationLecture)).count();
        return equalTimeReservationCount > 0;
    }
}
