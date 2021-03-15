package com.vaadin.fastui.ui.views.dashboard;

import com.vaadin.fastui.backend.service.ApiCityPhotosService;
import com.vaadin.fastui.ui.MainLayout;
import com.vaadin.fastui.ui.views.lists.FlightsList;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;

@Route(value = "flights/reservation", layout = MainLayout.class)
@PageTitle("Reservation")
public class ReservationPage extends VerticalLayout {
    private ApiCityPhotosService apiCityPhotosService;
    private FlightsList flightsList;
    private MainLayout mainLayout;
    private String city;
    VerticalLayout layoutFull = new VerticalLayout();

    Button back = new Button("Back");
    Button confirm = new Button("Confirm");

    H1 toCityH1 = new H1("Flight to state: ");
    H1 fromCityH1 = new H1("Flight from state: ");
    H1 seatsLeftH1 = new H1("Seats left: ");
    H1 departureH1 = new H1("Departure: ");
    H1 priceH1 = new H1("Ticket price: ");

    Text toCity = new Text("Flight to state: ");
    Text fromCity = new Text("Flight from state: ");
    Text seatsLeft = new Text("Seats left: ");
    Text departure = new Text("Departure: ");
    Text price = new Text("Ticket price: ");


    Dialog dialog = new Dialog(new Text("siema"));


    public ReservationPage(ApiCityPhotosService apiCityPhotosService,
                           FlightsList flightsList) throws IOException {
        this.flightsList = flightsList;
        this.apiCityPhotosService = apiCityPhotosService;
        setSizeFull();
        addClassName("reservation-page");
        layoutFull.addClassName("layout-full");

        layoutFull.setHeightFull();

        configureButtonOperations();
        ;
        layoutFull.add(configureSplitLayouts(), configureH1Informations(), configureLayoutButton());
        add(layoutFull);
    }

    private VerticalLayout configureH1Informations() {
        VerticalLayout textLayout = new VerticalLayout();
        toCity.setText(flightsList.returnId().get().getToCity());
        fromCity.setText(flightsList.returnId().get().getFromCity());
        seatsLeft.setText(String.valueOf(flightsList.returnId().get().getSeatsLeft()));
        departure.setText(DateFormatUtils.format(flightsList.returnId().get().getDate(), "yyyy-MM-dd"));
        price.setText(String.valueOf(flightsList.returnId().get().getPrice() + " $"));
        toCityH1.add(toCity);
        fromCityH1.add(fromCity);
        seatsLeftH1.add(seatsLeft);
        departureH1.add(departure);
        priceH1.add(price);
        textLayout.add(fromCityH1,
                toCityH1,
                seatsLeftH1,
                departureH1,
                priceH1 );
        return textLayout;
    }

    private HorizontalLayout configureLayoutButton() {
        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        back.addThemeVariants(ButtonVariant.LUMO_ERROR);
        confirm.addClassName("confirm");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);

        layout.addAndExpand(back, confirm);
        return layout;
    }

    private void configureButtonOperations() {
        back.addClickListener(e ->
                back.getUI().ifPresent(ui ->
                        ui.navigate("flights")));
        confirm.addClickListener(e -> dialog.open()); ///email sender // download

    }

    private HorizontalLayout configureSplitLayouts() throws IOException {

        HorizontalLayout orderedLayout = new HorizontalLayout();
        orderedLayout.addClassName("firstSs");
        // add api
        orderedLayout.add(retrieveCityImage(flightsList.returnId().get().getToCity()));
        orderedLayout.setPadding(false);
        orderedLayout.setMargin(true);
        orderedLayout.setSpacing(true);

        return orderedLayout;
    }

    private Image retrieveCityImage(String city) throws IOException {
        Image image = new Image(apiCityPhotosService.getImage(city), "city");
        return image;
    }


}
