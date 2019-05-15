package me.kamilkorzeniewski.conference.user.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import me.kamilkorzeniewski.conference.ApplicationUI;
import me.kamilkorzeniewski.conference.MainView;
import me.kamilkorzeniewski.conference.user.User;

@SpringView(name = UserView.NAME)
public class UserView extends VerticalLayout implements View {
    public static final String NAME = "user";

    public UserView(){
        addComponents(createMenu());
    }

    private HorizontalLayout createMenu(){
        final Button updateEmailButton = new Button("Update email",e-> getUI().addWindow(new EmailUpdateWindow(currentUser())));
        final Button logOutButton = new Button("Log out",e -> {
            ((ApplicationUI)getUI()).setCurrentUser(null);
            getUI().getNavigator().navigateTo(MainView.NAME);
        });
        HorizontalLayout menu = new HorizontalLayout();
        menu.addComponents(updateEmailButton,logOutButton);
        return menu;
    }

    private User currentUser(){
        return ((ApplicationUI) getUI()).getCurrentUser();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if(currentUser() == null)
            getUI().getNavigator().navigateTo(LogInView.NAME);
    }


}
