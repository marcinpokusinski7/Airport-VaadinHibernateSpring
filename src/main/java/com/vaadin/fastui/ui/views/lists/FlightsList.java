package com.vaadin.fastui.ui.views.lists;

import com.vaadin.fastui.backend.entity.Flight;
import com.vaadin.fastui.backend.service.ApiCityPhotosService;
import com.vaadin.fastui.backend.service.FlightService;
import com.vaadin.fastui.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "flights", layout = MainLayout.class)
@PageTitle("FlightsList")
public class FlightsList extends VerticalLayout {

    private FlightService flightService;
    private ApiCityPhotosService apiCityPhotosService;
    Grid<Flight> flightGrid = new Grid<>(Flight.class);
   /* TextField areoplaneFlightId = new TextField();
    TextField fromCity = new TextField();
    TextField toCity = new TextField();
    TextArea seatsLeft = new TextArea();

    Button add = new Button("Add");
    Button delete = new Button("Delete");
*/
    public FlightsList(FlightService flightService) {
        this.flightService = flightService;
        addClassName("flight-view");
        setSizeFull();
        configureGrid();


        add(flightGrid);
        updateList();


    }

    private void updateList() {
        flightGrid.setItems(flightService.findAll());

    }

    private void configureGrid() {
            flightGrid.addClassName("flight-grid");
            flightGrid.setSizeFull();
            flightGrid.setColumns("fromCity", "toCity","price", "seatsLeft", "date");

    }

}
