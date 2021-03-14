package com.vaadin.fastui.ui.views.lists;

import com.vaadin.fastui.backend.entity.Flight;
import com.vaadin.fastui.backend.service.FlightService;
import com.vaadin.fastui.ui.MainLayout;
import com.vaadin.fastui.ui.views.dashboard.ReservationPage;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.IOException;

@Route(value = "flights", layout = MainLayout.class)
@PageTitle("FlightsList")
public class FlightsList extends VerticalLayout {

    private Flight flight;
    private FlightService flightService;
    private ReservationPage reservationPage;
    Grid<Flight> flightGrid = new Grid<>(Flight.class);

    H1 toCityH1 = new H1("To city: ");
    H1 fromCityH1 = new H1("From city: ");
    H1 seatsLeftH1 = new H1("Seats Left: ");
    H1 datePickH1 = new H1("Departure: ");
    H1 priceH1 = new H1("Ticket price: ");

    Text toCity = new Text("");
    Text fromCity = new Text("");
    Text seatsLeft = new Text("");
    Text datePick = new Text("");
    Text price = new Text("");

    Dialog dialog = new Dialog();
    Button close = new Button("Close", new Icon(VaadinIcon.ARROW_LEFT));
    Button continueDialog = new Button("Continue", new Icon(VaadinIcon.ARROW_RIGHT));
    TextField filterText = new TextField();

    public FlightsList(FlightService flightService) throws IOException {
        this.flightService = flightService;
        addClassName("list-view");

        buttonConfigure();
        setSizeFull();
        configureGrid();
        configureDialog();
        add(getToolBar(), flightGrid);
        updateList();
    }

    private void updateList() {
        flightGrid.setItems(flightService.findAll(filterText.getValue()));

    }

    private void configureGrid() {
        flightGrid.addClassName("flight-grid");
        flightGrid.setSizeFull();
        flightGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        flightGrid.setColumns("fromCity", "toCity", "price", "seatsLeft", "date");

    }

    private void configureDialog() {
        dialog.setHeight("450px");
        dialog.setWidth("700px");
        HorizontalLayout layout = new HorizontalLayout();
        layout.addAndExpand(close, continueDialog);

        toCityH1.add(toCity);
        fromCityH1.add(fromCity);
        seatsLeftH1.add(seatsLeft);
        datePickH1.add(datePick);
        priceH1.add(price);
        flightGrid.addSelectionListener(e -> dialog.open());
        dialog.add(toCityH1,
                fromCityH1,
                seatsLeftH1,
                datePickH1,
                priceH1,
                layout);


    }

    private void buttonConfigure() {
        close.addThemeVariants(ButtonVariant.LUMO_ERROR);
        continueDialog.setIconAfterText(true);
        close.addClickListener(e -> dialog.close());
        continueDialog.addClickListener(e ->
                continueDialog.getUI().ifPresent(ui ->
                        ui.navigate("flights/reservation")));
        continueDialog.addClickListener(e -> dialog.close());
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by city...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList()); //everytime value in button changes it updates list
        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }


}

