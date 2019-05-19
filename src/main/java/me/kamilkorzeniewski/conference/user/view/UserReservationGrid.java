package me.kamilkorzeniewski.conference.user.view;

import com.vaadin.ui.Grid;
import me.kamilkorzeniewski.conference.reservation.Reservation;
import me.kamilkorzeniewski.conference.reservation.ReservationService;
import me.kamilkorzeniewski.conference.schedule.lecture.Lecture;
import me.kamilkorzeniewski.conference.schedule.lecture.LectureService;
import me.kamilkorzeniewski.conference.user.User;
import me.kamilkorzeniewski.conference.utils.SpringContext;

import java.util.List;
import java.util.stream.Collectors;

class UserReservationGrid extends Grid<UserReservationGrid.GridReservationItem> {
    private final User user;

    UserReservationGrid(User user) {
        this.user = user;
        setItems(loadData());
        addColumn(GridReservationItem::getReservationId).setCaption("ID");
        addColumn(GridReservationItem::getLectureName).setCaption("Lecture");
        addColumn(GridReservationItem::getDuringDateTime).setCaption("Date");
        setSelectionMode(SelectionMode.MULTI);
    }

    void refreshGrid() {
        setItems(loadData());
        getDataProvider().refreshAll();
    }

    private List<GridReservationItem> loadData() {
        return reservationService().getAllReservationsByUser(user)
                .stream().map(GridReservationItem::new).collect(Collectors.toList());
    }

    private ReservationService reservationService() {
        return SpringContext.getBean(ReservationService.class);
    }

    class GridReservationItem {
        final private Reservation reservation;
        final private Lecture lecture;

        GridReservationItem(Reservation reservation) {
            this.reservation = reservation;
            lecture = LectureService.getById(reservation.getLectureId()).orElseThrow(IllegalArgumentException::new);
        }

        int getReservationId() {
            return reservation.getId();
        }

        String getLectureName() {
            return lecture.getName();
        }

        String getDuringDateTime() {
            return lecture.duringDateTime();
        }

    }
}
