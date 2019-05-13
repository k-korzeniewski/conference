package me.kamilkorzeniewski.conference.reservation;


import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    @Column(name="reservation_id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="lecture_id")
    private int lectureId;

    private Reservation(){}

    Reservation(int userId, int lectureId) {
        this.userId = userId;
        this.lectureId = lectureId;
    }

    int getLectureId() {
        return lectureId;
    }

    int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }
}
