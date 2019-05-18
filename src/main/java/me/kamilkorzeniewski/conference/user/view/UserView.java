package me.kamilkorzeniewski.conference.user.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import me.kamilkorzeniewski.conference.ApplicationUI;
import me.kamilkorzeniewski.conference.MainView;
import me.kamilkorzeniewski.conference.reservation.Reservation;
import me.kamilkorzeniewski.conference.reservation.ReservationService;
import me.kamilkorzeniewski.conference.user.User;
import me.kamilkorzeniewski.conference.utils.ConfirmDialog;

@SpringView(name = UserView.NAME)
public class UserView extends VerticalLayout implements View {
    public static final String NAME = "user";
    private final Button deleteButton;
    private  UserReservationGrid grid;
    private final ReservationService reservationService;
    public UserView(ReservationService reservationService) {
        deleteButton = new Button("Delete", e -> handleDeleteButton());
        this.reservationService = reservationService;
    }

    private HorizontalLayout createMenu() {
        final Button updateEmailButton = new Button("Update email", e -> getUI().addWindow(new EmailUpdateWindow(currentUser())));
        final Button logOutButton = new Button("Log out", e -> {
            ((ApplicationUI) getUI()).setCurrentUser(null);
            getUI().getNavigator().navigateTo(MainView.NAME);
        });
        HorizontalLayout menu = new HorizontalLayout();
        menu.addComponents(updateEmailButton, logOutButton);
        return menu;
    }

    private void handleDeleteButton() {
        ConfirmDialog dialog = new ConfirmDialog("Are you sure to remove reservation(s) ?");
        dialog.center();
        dialog.setWidth(30,Unit.PERCENTAGE);
        dialog.setHeight(20,Unit.PERCENTAGE);
        dialog.addOnAcceptHandler(()->{
            removeReservations();
            dialog.close();
        });
        dialog.addOnCancelHandler(dialog::close);
        getUI().addWindow(dialog);
    }

    private void removeReservations(){
        for(UserReservationGrid.GridReservationItem item : grid.getSelectedItems()){
            int reservationId = item.getReservationId();
            Reservation reservation = reservationService.getReservationById(reservationId)
                    .orElseThrow(IllegalArgumentException::new); // change this exception !
            reservationService.removeReservation(reservation);
            grid.refreshGrid();
        }
    }

    private User currentUser() {
        return ((ApplicationUI) getUI()).getCurrentUser();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (currentUser() == null) {
            getUI().getNavigator().navigateTo(LogInView.NAME);
            return;
        }
        grid = new UserReservationGrid(currentUser());
        addComponents(createMenu());
        addComponent(grid);
        addComponent(deleteButton);
    }


}
