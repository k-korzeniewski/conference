package me.kamilkorzeniewski.conference.reservation;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Notification;
import me.kamilkorzeniewski.conference.reservation.exception.ReservationException;
import me.kamilkorzeniewski.conference.schedule.lecture.Lecture;
import me.kamilkorzeniewski.conference.user.User;
import me.kamilkorzeniewski.conference.user.UserService;

@SpringComponent
public class ReservationWindowController {

    private final ReservationService reservationService;
    private final UserService userService;

    ReservationWindowController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    void doReservation(User user, Lecture lecture) {
        User fetchedUser = userService.findUserByName(user.getName()).orElseGet(()->userService.createUser(user));
        if (!fetchedUser.isEmailEqual(user.getEmail())) {
            Notification.show("User already exists",Notification.Type.WARNING_MESSAGE);
            return;
        }
        try {
            reservationService
                    .createReservation(new Reservation(fetchedUser.getId(), lecture.getId()));
            Notification.show("Reservation successful");
        } catch (ReservationException ex) {
            Notification.show(ex.getMessage(),Notification.Type.WARNING_MESSAGE);
        }
    }



}
