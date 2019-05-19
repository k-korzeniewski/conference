package me.kamilkorzeniewski.conference.reservation;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Notification;
import me.kamilkorzeniewski.conference.email.Email;
import me.kamilkorzeniewski.conference.email.EmailService;
import me.kamilkorzeniewski.conference.reservation.exception.ReservationException;
import me.kamilkorzeniewski.conference.schedule.lecture.Lecture;
import me.kamilkorzeniewski.conference.user.User;
import me.kamilkorzeniewski.conference.user.UserService;


@SpringComponent
public class ReservationWindowController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final EmailService emailService;

    ReservationWindowController(ReservationService reservationService, UserService userService, EmailService emailService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.emailService = emailService;
    }

    void doReservation(User user, Lecture lecture) {
        User fetchedUser = userService.findUserByName(user.getName())
                                        .orElseGet(() -> userService.saveUser(user));
        if (!fetchedUser.isEmailEqual(user.getEmail())) {
            showNotification("User already exists", Notification.Type.WARNING_MESSAGE);
            return;
        }
        try {
            Reservation reservation = reservationService
                    .createReservation(new Reservation(fetchedUser.getId(), lecture.getId()));
            Email email = Email.from(reservation, user);
            emailService.saveEmail(email);
            showNotification("Reservation successful, email has been send",Notification.Type.HUMANIZED_MESSAGE);
        } catch (ReservationException ex) {
            Notification.show(ex.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
    }


    private void showNotification(String message,Notification.Type type){
        Notification notification = new Notification(message,type);
        notification.setDelayMsec(3000);
        notification.setPosition(Position.MIDDLE_CENTER);
        notification.show(Page.getCurrent());
    }



}
