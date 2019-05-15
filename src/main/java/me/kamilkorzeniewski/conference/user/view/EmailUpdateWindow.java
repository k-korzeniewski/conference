package me.kamilkorzeniewski.conference.user.view;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.*;
import me.kamilkorzeniewski.conference.ApplicationUI;
import me.kamilkorzeniewski.conference.user.User;
import me.kamilkorzeniewski.conference.user.UserService;
import me.kamilkorzeniewski.conference.utils.SpringContext;

class EmailUpdateWindow extends Window {
    private final TextField emailInput;
    private final Button saveButton;
    private final User user;
    private final Binder<EmailValue> binder;

    EmailUpdateWindow(User user) {
        final VerticalLayout content = new VerticalLayout();
        this.user = user;
        this.emailInput = new TextField("new emailInput");
        this.saveButton = new Button("Save", e -> saveButtonHandler());

        emailInput.addAttachListener(e -> validateInput());
        emailInput.addValueChangeListener(e -> validateInput());

        binder = new Binder<>(EmailValue.class);
        binder.setBean(new EmailValue());
        binder.forField(emailInput)
                .withValidator(new EmailValidator("Wrong email"))
                .asRequired("Email required")
                .bind(EmailValue::getEmail, EmailValue::setEmail);

        content.addComponents(emailInput, saveButton);
        setContent(content);
    }

    private static UserService getUserService() {
        return SpringContext.getBean(UserService.class);
    }


    private void saveButtonHandler() {
        User newUser = user.withEmail(binder.getBean().getEmail());
        User updatedUser = getUserService().saveUser(newUser);
        ((ApplicationUI) getUI()).setCurrentUser(updatedUser);
        Notification.show("Email has been updated");
        close();
    }

    private void validateInput() {
        final boolean valid = binder.isValid();
        saveButton.setEnabled(valid);
    }

    private class EmailValue {
        private String email;
        String getEmail() {
            return email;
        }
        void setEmail(String email) {
            this.email = email;
        }
    }
}

