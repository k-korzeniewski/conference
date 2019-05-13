package me.kamilkorzeniewski.conference.email;

import me.kamilkorzeniewski.conference.reservation.Reservation;
import me.kamilkorzeniewski.conference.user.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Email {
    private final LocalDateTime sendTime;
    private final String to;
    private final String content;

    private Email(LocalDateTime sendTime, String to, String content) {
        this.sendTime = sendTime;
        this.to = to;
        this.content = content;
    }

    public static Email from(Reservation reservation, User user) {
        LocalDateTime sendTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        String to = user.getEmail();
        String content = "Potwierdzenie dokonania rezerwacji nr " + reservation.getId() + "\n"
                + "W celu anulowania rezerwacji prosimy zalogować się na naszej stronie";
        return new Email(sendTime, to, content);
    }

    @Override
    public String toString() {
        return "To: " + to
                + "\n" +
                "Date : " + sendTime.toString()
                + "\n" +
                content;
    }
}
