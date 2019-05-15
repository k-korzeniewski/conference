package me.kamilkorzeniewski.conference;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import me.kamilkorzeniewski.conference.user.UserView;

@SpringUI
public class ApplicationUI extends UI {
    private final VerticalLayout content;
    private final Panel panel;

    private final SpringNavigator springNavigator;

    public ApplicationUI(SpringNavigator springNavigator) {
        content = new VerticalLayout();
        panel = new Panel();
        this.springNavigator = springNavigator;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(content);
        addHeader();
        content.addComponent(panel);
        springNavigator.init(this,panel);
        setNavigator(springNavigator);
    }


    private void addHeader() {
        HorizontalLayout header = new HorizontalLayout();
        Label title = new Label("IT CONFERENCE");
        Button mainViewButton = new Button("Home page", e -> springNavigator.navigateTo(MainView.NAME));
        Button userViewButton = new Button("User", e -> springNavigator.navigateTo(UserView.NAME));
        header.addComponents(title, mainViewButton, userViewButton);

        header.setComponentAlignment(mainViewButton,Alignment.MIDDLE_RIGHT);
        header.setComponentAlignment(userViewButton,Alignment.MIDDLE_RIGHT);
        header.setExpandRatio(title,1f);
        header.setWidth(100,Unit.PERCENTAGE);

        content.addComponent(header);
    }


}
