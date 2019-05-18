package me.kamilkorzeniewski.conference.utils;

import com.vaadin.ui.*;

public class ConfirmDialog extends Window {
    private final Button acceptButton;
    private final Button cancelButton;
    public ConfirmDialog(String desc){
        final VerticalLayout content = new VerticalLayout();
        final HorizontalLayout buttons = new HorizontalLayout();
        final Label description = new Label(desc);
        acceptButton = new Button("OK");
        cancelButton = new Button("cancel");
        buttons.addComponents(acceptButton,cancelButton);
        content.addComponents(description,buttons);
        content.setComponentAlignment(description,Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(buttons,Alignment.MIDDLE_CENTER);
        setContent(content);

        setClosable(false);
        setResizable(false);
    }

    public void addOnAcceptHandler(Action action){
        acceptButton.addClickListener(e->action.execute());
    }

    public void addOnCancelHandler(Action action){
        cancelButton.addClickListener(e->action.execute());
    }



    @FunctionalInterface
    public interface Action {
        void execute();
    }
}
