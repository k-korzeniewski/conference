package me.kamilkorzeniewski.conference.reservation;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.*;
import me.kamilkorzeniewski.conference.utils.SpringContext;
import me.kamilkorzeniewski.conference.schedule.lecture.Lecture;
import me.kamilkorzeniewski.conference.user.User;

public class ReservationWindow extends Window {

    private final Lecture lecture;
    private final VerticalLayout content;
    private final Binder<User> userBinder;

    private final TextField userNameInput;
    private final TextField emailInput;
    private final Button bookButton;

    public ReservationWindow(Lecture lecture) {
        this.lecture = lecture;
        this.content = new VerticalLayout();
        this.userBinder = new Binder<>(User.class);

        this.userNameInput = new TextField("User name");
        this.emailInput = new TextField("Email address");
        this.bookButton = new Button("Reserve");

        setResizable(false);
        center();
        setWidth(25,Unit.PERCENTAGE);
        setHeight(30,Unit.PERCENTAGE);
        setContent(content);
        setUpComponents();

    }

    private static ReservationWindowController getController() {
        return SpringContext.getBean(ReservationWindowController.class);
    }

    private void setUpComponents() {
        userNameInput.addValueChangeListener((event)-> validateInputs());
        emailInput.addValueChangeListener((event)-> validateInputs());

        userNameInput.addAttachListener((event)-> validateInputs());
        emailInput.addAttachListener((event) -> validateInputs());

        bookButton.addClickListener((event) -> {
            getController().doReservation(userBinder.getBean(),lecture);
            close();
        });

        userBinder.setBean(new User());
        userBinder.forField(userNameInput)
                .asRequired("User name required")
                .withValidator(name -> name.length() >=3,"User name must contain at least 3 characters")
                .bind(User::getName,User::setName);
        userBinder.forField(emailInput)
                .asRequired("Email required")
                .withValidator(new EmailValidator("Email incorrect"))
                .bind(User::getEmail,User::setEmail);

        content.addComponents(userNameInput, emailInput, bookButton);
        content.setComponentAlignment(userNameInput,Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(emailInput,Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(bookButton,Alignment.MIDDLE_CENTER);
    }

    private void validateInputs(){
        final boolean valid = userBinder.isValid();
        bookButton.setEnabled(valid);
    }

}
