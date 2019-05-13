package me.kamilkorzeniewski.conference.reservation.exception;

public class TicketsNotAvailableException extends ReservationException {
    public TicketsNotAvailableException(){
        super("Can't do reservation because no tickets left");
    }
}
