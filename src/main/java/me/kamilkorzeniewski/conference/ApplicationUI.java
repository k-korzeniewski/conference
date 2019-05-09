package me.kamilkorzeniewski.conference;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class ApplicationUI extends UI {

    private VerticalLayout content;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        createLayout();

    }

    private void createLayout(){
        content = new VerticalLayout();
        content.setSizeFull();
        setContent(content);
    }
}
