package com.vaadin.fastui.ui.views.dashboard;

import com.vaadin.fastui.backend.service.ApiCityPhotosService;
import com.vaadin.fastui.ui.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.IOException;

@Route(value = "flights/reservation", layout = MainLayout.class)
@PageTitle("Reservation")
public class ReservationPage extends VerticalLayout {
    private ApiCityPhotosService apiCityPhotosService;
    private MainLayout mainLayout;
    VerticalLayout layoutFull = new VerticalLayout();
    HorizontalLayout layout = new HorizontalLayout();
    Button confirm = new Button("Confirm");
    Button back = new Button("Back");
    Dialog dialog = new Dialog(new Text("siema"));

    public ReservationPage(ApiCityPhotosService apiCityPhotosService) throws IOException {
        this.apiCityPhotosService = apiCityPhotosService;

        addClassName("reservation-page");
        layoutFull.addClassName("layout-full");
        setSizeFull();

        configureLayout();
        configureButtonOperations();

        layout.add(back, confirm);
        layoutFull.add(configureSplitLayouts(), layout);

        add(layoutFull);
    }

    private void configureLayout() {
        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        back.addThemeVariants(ButtonVariant.LUMO_ERROR);

        layoutFull.setHeightFull();
    }

    private void configureButtonOperations() {
        back.addClickListener(e ->
                back.getUI().ifPresent(ui ->
                        ui.navigate("flights")));
        confirm.addClickListener(e -> dialog.open()); ///email sender // download

    }

    private HorizontalLayout configureSplitLayouts() throws IOException {
        HorizontalLayout orderedLayout = new HorizontalLayout();

        // Create component 1 and 2 with vertical layout
        VerticalLayout twoScreenShoots = new VerticalLayout();
        HorizontalLayout firstSs = new HorizontalLayout();
        HorizontalLayout secondSs = new HorizontalLayout();

        // configure horizontals
        twoScreenShoots.add(firstSs,secondSs);
        // create component 3 as horizontal layout
        HorizontalLayout thirdSs = new HorizontalLayout();

        // add api
        firstSs.add(retrieveCityImage("berlin"));
        secondSs.add(retrieveCityImage("paris"));
        thirdSs.add(retrieveCityImage("Warsaw"));
        // add to layout
        orderedLayout.add(twoScreenShoots,thirdSs);
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
