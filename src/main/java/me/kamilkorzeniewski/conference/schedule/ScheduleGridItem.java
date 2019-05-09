package me.kamilkorzeniewski.conference.schedule;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import me.kamilkorzeniewski.conference.schedule.lecture.Lecture;

 class ScheduleGridItem extends VerticalLayout {

     ScheduleGridItem(Lecture lecture){
        final Label name = new Label(lecture.getName());
        final Button bookButton = new Button("Book");
        addComponents(name,bookButton);
     }
}
