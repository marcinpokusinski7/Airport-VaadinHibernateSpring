package com.vaadin.fastui.ui.views.lists;

import com.vaadin.fastui.backend.entity.Flight;
import com.vaadin.fastui.backend.service.ApiCityPhotosService;
import com.vaadin.fastui.backend.service.FlightService;
import com.vaadin.fastui.ui.MainLayout;
import com.vaadin.fastui.ui.views.dashboard.ReservationPage;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.IOException;

@Route(value = "flights", layout = MainLayout.class)
@PageTitle("FlightsList")
public class FlightsList extends VerticalLayout {

    private FlightService flightService;
    private ApiCityPhotosService apiCityPhotosService;
    private ReservationPage reservationPage;
    Grid<Flight> flightGrid = new Grid<>(Flight.class);
    Dialog dialog = new Dialog();
    Button close = new Button("Close");
    Button continueDialog = new Button("Continue", new Icon(VaadinIcon.ARROW_RIGHT));

    /* TextField areoplaneFlightId, = new TextField();
     TextField fromCity = new TextField();
     TextField toCity = new TextField();
     TextArea seatsLeft = new TextArea();
 
     Button add = new Button("Add");
     Button delete = new Button("Delete");
 */
    public FlightsList(FlightService flightService, ApiCityPhotosService apiCityPhotosService) throws IOException {
        this.flightService = flightService;
        this.apiCityPhotosService = apiCityPhotosService;
        addClassName("list-view");
        buttonConfigure();
        setSizeFull();
        configureGrid();
        configureDialog();
        add(flightGrid);
        updateList();
    }

    private void updateList() {
        flightGrid.setItems(flightService.findAll());

    }

    private void configureGrid() {
        flightGrid.addClassName("flight-grid");
        flightGrid.setSizeFull();
        flightGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        flightGrid.setColumns("fromCity", "toCity", "price", "seatsLeft", "date");
        flightGrid.addSelectionListener(e-> dialog.open());
    }


    private void configureDialog(){
        dialog.add(new Text("Close me with escape\n"));
        dialog.setHeight("400px");
        dialog.setWidth("650px");
        close.addClickListener(e -> dialog.close());
        continueDialog.addClickListener(e->
                continueDialog.getUI().ifPresent(ui ->
                        ui.navigate("flights/reservation")));
        continueDialog.addClickListener(e-> dialog.close());
        dialog.add(close,continueDialog);
    }

    private void buttonConfigure(){
        close.addThemeVariants(ButtonVariant.LUMO_ERROR);
        continueDialog.setIconAfterText(true);
    }





}
