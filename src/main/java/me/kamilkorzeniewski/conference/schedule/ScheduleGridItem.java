package me.kamilkorzeniewski.conference.schedule;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import me.kamilkorzeniewski.conference.reservation.ReservationWindow;
import me.kamilkorzeniewski.conference.schedule.lecture.Lecture;

 class ScheduleGridItem extends VerticalLayout {

     ScheduleGridItem(Lecture lecture){
        final Label name = new Label(lecture.getName());
        final Button bookButton = new Button("Reserve");
        addComponents(name,bookButton);
        bookButton.addClickListener((event) -> getUI().addWindow(new ReservationWindow(lecture)));
     }
}
