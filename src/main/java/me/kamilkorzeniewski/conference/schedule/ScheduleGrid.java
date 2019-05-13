package me.kamilkorzeniewski.conference.schedule;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import me.kamilkorzeniewski.conference.schedule.lecture.Lecture;
import me.kamilkorzeniewski.conference.schedule.lecture.LectureService;
import me.kamilkorzeniewski.conference.schedule.lecture.LectureTopic;

import java.util.List;

@UIScope
@SpringComponent
public class ScheduleGrid extends GridLayout {
    private final static int ROWS = 5;
    private final static int COLS = 4;

    public ScheduleGrid() {
        super(COLS, ROWS);
        setUpLayout();
        addTitles();
        addLectures();
    }

    private void setUpLayout() {
        setWidth(50, Unit.PERCENTAGE);
        setHeight(70, Unit.PERCENTAGE);
        setColumnExpandRatio(0, 0.1f);
        setColumnExpandRatio(1, 0.3f);
        setColumnExpandRatio(2, 0.3f);
        setColumnExpandRatio(3, 0.3f);
        setSpacing(true);
    }

    private void addTitles() {
        addComponentWithAlignment(new Label("Topic I"), Alignment.MIDDLE_LEFT, 1, 0);
        addComponentWithAlignment(new Label("Topic I"), Alignment.MIDDLE_LEFT, 2, 0);
        addComponentWithAlignment(new Label("Topic I"), Alignment.MIDDLE_LEFT, 3, 0);

        addComponentWithAlignment(new Label("DAY I (10:00-11:45)"), Alignment.MIDDLE_CENTER, 0, 1);
        addComponentWithAlignment(new Label("DAY I (12:00-13:45)"), Alignment.MIDDLE_CENTER, 0, 2);
        addComponentWithAlignment(new Label("DAY II (11:00-11:45)"), Alignment.MIDDLE_CENTER, 0, 3);
        addComponentWithAlignment(new Label("DAY II (12:00-13:45)"), Alignment.MIDDLE_CENTER, 0, 4);
    }


    private void addLectures() {
        final int ROW_OFFSET = 1;
        addLecturesByTopic(LectureTopic.FIRST, 1, ROW_OFFSET);
        addLecturesByTopic(LectureTopic.SECOND, 2, ROW_OFFSET);
        addLecturesByTopic(LectureTopic.THIRD, 3, ROW_OFFSET);
    }

    /*
     * Insert lectures from specific topic to given column.
     * Lectures are sorted by startTime ascending.
     */
    private void addLecturesByTopic(LectureTopic topic, int column, int row_offset) {
        final List<Lecture> lectures = LectureService.getLecturesByTopic(topic);
        for (int i = 0; i < lectures.size(); i++) {
            Lecture lecture = lectures.get(i);
            addComponent(new ScheduleGridItem(lecture), column, i + row_offset);
        }
    }

    private void addComponentWithAlignment(Component c, Alignment alignment, int column, int row) {
        addComponent(c, column, row);
        setComponentAlignment(c, alignment);
    }
}
