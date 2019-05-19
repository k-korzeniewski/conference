package me.kamilkorzeniewski.conference.schedule.lecture;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Lecture {
    private final int id;
    private final String name;
    private final LectureTopic topic;
    private final int tickets;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    Lecture(int id, String name, LectureTopic topic, int tickets, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.name = name;
        this.tickets = tickets;
        this.startTime = startTime.truncatedTo(ChronoUnit.MINUTES);
        this.endTime = endTime.truncatedTo(ChronoUnit.MINUTES);
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public int getTickets() {
        return tickets;
    }

    LocalDateTime getStartTime() {
        return startTime;
    }

    public boolean isStartTimeEqual(Lecture lecture){
        return getStartTime().equals(lecture.getStartTime());
    }

    boolean isTopicEquals(LectureTopic topic) {
        return this.topic.equals(topic);
    }

    public String duringDateTime(){
        return startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))
                + " - " +
                endTime.format(DateTimeFormatter.ofPattern("HH:mm"));

    }

    public int getId() {
        return id;
    }
}
