package me.kamilkorzeniewski.conference.schedule.lecture;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Lecture {
    private int id;
    private String name;
    private LectureTopic topic;
    private int tickets;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

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

    LocalDateTime getStartTime() {
        return startTime;
    }

    boolean isTopicEquals(LectureTopic topic){
        return this.topic.equals(topic);
    }


}
