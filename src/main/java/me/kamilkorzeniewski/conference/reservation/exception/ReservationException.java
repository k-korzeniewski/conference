package me.kamilkorzeniewski.conference.reservation.exception;


public class ReservationException extends RuntimeException {
    ReservationException(String message) {
        super(message);
    }
}
