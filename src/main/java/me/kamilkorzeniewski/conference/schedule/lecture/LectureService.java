package me.kamilkorzeniewski.conference.schedule.lecture;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LectureService {

    public static List<Lecture> getLectures() {
        final List<Lecture> lectureList = new ArrayList<>();
        final int TICKETS_POOL = 5;
        final LocalDate FIRST_DAY = LocalDate.of(2019, 6, 1);
        final LocalDate SECOND_DAY = LocalDate.of(2019, 6, 2);
        // FIRST TOPIC
        lectureList.add(new Lecture(0, "some lecture I",
                LectureTopic.FIRST, TICKETS_POOL,
                LocalDateTime.of(FIRST_DAY, LocalTime.of(10, 0)),
                LocalDateTime.of(FIRST_DAY, LocalTime.of(11, 45))));
        lectureList.add(new Lecture(1, "next lecture I",
                LectureTopic.FIRST, TICKETS_POOL,
                LocalDateTime.of(FIRST_DAY, LocalTime.of(12, 0)),
                LocalDateTime.of(FIRST_DAY, LocalTime.of(13, 45))));
        lectureList.add(new Lecture(3, "great lecture I",
                LectureTopic.FIRST, TICKETS_POOL,
                LocalDateTime.of(SECOND_DAY, LocalTime.of(10, 0)),
                LocalDateTime.of(SECOND_DAY, LocalTime.of(11, 45))));
        lectureList.add(new Lecture(4, "boring lecture I",
                LectureTopic.FIRST, TICKETS_POOL,
                LocalDateTime.of(SECOND_DAY, LocalTime.of(12, 0)),
                LocalDateTime.of(SECOND_DAY, LocalTime.of(13, 45))));

        //SECOND TOPIC
        lectureList.add(new Lecture(5, "some lecture II",
                LectureTopic.SECOND, TICKETS_POOL,
                LocalDateTime.of(FIRST_DAY, LocalTime.of(10, 0)),
                LocalDateTime.of(FIRST_DAY, LocalTime.of(11, 45))));
        lectureList.add(new Lecture(6, "next lecture II",
                LectureTopic.SECOND, TICKETS_POOL,
                LocalDateTime.of(FIRST_DAY, LocalTime.of(12, 0)),
                LocalDateTime.of(FIRST_DAY, LocalTime.of(13, 45))));
        lectureList.add(new Lecture(7, "great lecture II",
                LectureTopic.SECOND, TICKETS_POOL,
                LocalDateTime.of(SECOND_DAY, LocalTime.of(10, 0)),
                LocalDateTime.of(SECOND_DAY, LocalTime.of(11, 45))));
        lectureList.add(new Lecture(8, "boring lecture II",
                LectureTopic.SECOND, TICKETS_POOL,
                LocalDateTime.of(SECOND_DAY, LocalTime.of(12, 0)),
                LocalDateTime.of(SECOND_DAY, LocalTime.of(13, 45))));

        // THIRD TOPIC
        lectureList.add(new Lecture(9, "some lecture III ",
                LectureTopic.THIRD, TICKETS_POOL,
                LocalDateTime.of(FIRST_DAY, LocalTime.of(10, 0)),
                LocalDateTime.of(FIRST_DAY, LocalTime.of(11, 45))));
        lectureList.add(new Lecture(10, "next lecture III",
                LectureTopic.THIRD, TICKETS_POOL,
                LocalDateTime.of(FIRST_DAY, LocalTime.of(12, 0)),
                LocalDateTime.of(FIRST_DAY, LocalTime.of(13, 45))));
        lectureList.add(new Lecture(11, "great lecture III",
                LectureTopic.THIRD, TICKETS_POOL,
                LocalDateTime.of(SECOND_DAY, LocalTime.of(10, 0)),
                LocalDateTime.of(SECOND_DAY, LocalTime.of(11, 45))));
        lectureList.add(new Lecture(12, "boring lecture III",
                LectureTopic.THIRD, TICKETS_POOL,
                LocalDateTime.of(SECOND_DAY, LocalTime.of(12, 0)),
                LocalDateTime.of(SECOND_DAY, LocalTime.of(13, 45))));
        return lectureList;
    }

    public static List<Lecture> getLecturesByTopic(LectureTopic topic){
        return getLectures().stream()
                .filter((lecture -> lecture.isTopicEquals(topic)))
                .sorted(Comparator.comparing(Lecture::getStartTime))
                .collect(Collectors.toList());
    }
}
