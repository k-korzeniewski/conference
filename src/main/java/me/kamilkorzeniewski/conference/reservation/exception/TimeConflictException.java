package me.kamilkorzeniewski.conference.reservation.exception;

public class TimeConflictException extends ReservationException {
    public TimeConflictException() {
        super("Can't do more than one reservation on specific time");
    }
}
