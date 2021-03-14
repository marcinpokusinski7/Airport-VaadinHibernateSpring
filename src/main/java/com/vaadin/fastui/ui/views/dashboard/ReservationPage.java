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

    Button back = new Button("Back");
    Button confirm = new Button("Confirm");

    Dialog dialog = new Dialog(new Text("siema"));

    public ReservationPage(ApiCityPhotosService apiCityPhotosService) throws IOException {
        this.apiCityPhotosService = apiCityPhotosService;
        setSizeFull();
        addClassName("reservation-page");
        layoutFull.addClassName("layout-full");
        layoutFull.setHeightFull();

        configureButtonOperations();

        layoutFull.add(configureSplitLayouts(), configureLayoutButton());

        add(layoutFull);
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
        orderedLayout.add(retrieveCityImage("Greece"));

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
