package me.kamilkorzeniewski.conference;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import me.kamilkorzeniewski.conference.user.User;
import me.kamilkorzeniewski.conference.user.view.UserView;

@SpringUI
@PreserveOnRefresh
public class ApplicationUI extends UI {
    private final VerticalLayout content;
    private final Panel panel;
    private final SpringNavigator springNavigator;

    private User currentUser;

    public ApplicationUI(SpringNavigator springNavigator) {
        content = new VerticalLayout();
        panel = new Panel();
        this.springNavigator = springNavigator;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(content);
        content.addComponents(createHeader(), panel);
        springNavigator.init(this, panel);
        setNavigator(springNavigator);
    }

    private HorizontalLayout createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        Label title = new Label("IT CONFERENCE");
        Button mainViewButton = new Button("Home page", e -> springNavigator.navigateTo(MainView.NAME));
        Button userViewButton = new Button("User", e -> springNavigator.navigateTo(UserView.NAME));
        header.addComponents(title, mainViewButton, userViewButton);
        header.setComponentAlignment(mainViewButton, Alignment.MIDDLE_RIGHT);
        header.setComponentAlignment(userViewButton, Alignment.MIDDLE_RIGHT);
        header.setExpandRatio(title, 1f);
        header.setWidth(100, Unit.PERCENTAGE);
        return header;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}
