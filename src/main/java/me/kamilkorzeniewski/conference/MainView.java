package me.kamilkorzeniewski.conference;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import me.kamilkorzeniewski.conference.schedule.ScheduleGrid;

@SpringView(name=MainView.NAME)
public class MainView extends VerticalLayout implements View {
    static final String NAME = "";
    private final ScheduleGrid scheduleGrid;


    public MainView(ScheduleGrid scheduleGrid){
        this.scheduleGrid = scheduleGrid;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addScheduleGrid();
    }


    private void addScheduleGrid(){
        addComponent(scheduleGrid);
        setComponentAlignment(scheduleGrid,Alignment.TOP_CENTER);
        setExpandRatio(scheduleGrid,1.0f);
    }
}
