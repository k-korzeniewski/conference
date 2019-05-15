package me.kamilkorzeniewski.conference.user.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import me.kamilkorzeniewski.conference.ApplicationUI;
import me.kamilkorzeniewski.conference.user.UserService;

@SpringView(name = LogInView.NAME)
public class LogInView extends VerticalLayout implements View {

    static final String NAME = "login";
    private final UserService userService;
    private final TextField userNameInput;

    public LogInView(UserService userService) {
        this.userService = userService;
        this.userNameInput = new TextField("User name");
        final Button logInButton = new Button("Login", e -> logInButtonHandler());
        addComponents(userNameInput, logInButton);
    }



    private void logInButtonHandler() {
        userService.findUserByName(userNameInput.getValue()).ifPresentOrElse(usr -> {
            ((ApplicationUI) getUI()).setCurrentUser(usr);
            getUI().getNavigator().navigateTo(UserView.NAME);
            },() -> Notification.show("User not exists", Notification.Type.WARNING_MESSAGE));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (((ApplicationUI) getUI()).getCurrentUser() != null)
            getUI().getNavigator().navigateTo(UserView.NAME);
    }
}
