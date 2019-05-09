package me.kamilkorzeniewski.conference;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import me.kamilkorzeniewski.conference.schedule.ScheduleGrid;

@SpringUI
public class ApplicationUI extends UI {

    private final VerticalLayout content;
    private final ScheduleGrid scheduleGrid;


    public ApplicationUI(ScheduleGrid scheduleGrid){
        content = new VerticalLayout();
        this.scheduleGrid = scheduleGrid;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(content);
        addHeader();
        addScheduleGrid();
    }

    private void addScheduleGrid(){
        content.addComponent(scheduleGrid);
        content.setComponentAlignment(scheduleGrid,Alignment.TOP_CENTER);
        content.setExpandRatio(scheduleGrid,1.0f);
    }

    private void addHeader(){
        HorizontalLayout header = new HorizontalLayout();
        header.addComponent(new Label("IT CONFERENCE"));
        content.addComponent(header);
    }


}
