package me.kamilkorzeniewski.conference.user;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name=UserView.NAME)
public class UserView extends VerticalLayout implements View {

    public static final String NAME = "user";
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(new Label("TEST"));
    }
}
