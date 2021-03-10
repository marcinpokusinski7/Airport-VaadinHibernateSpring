package com.vaadin.fastui.ui.views.dashboard;

import com.vaadin.fastui.backend.service.ApiCityPhotosService;
import com.vaadin.fastui.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
    HorizontalLayout layout = new HorizontalLayout();
    Image image = new Image();
    Button confirm = new Button("Confirm");
    Button back = new Button("Back");

    public ReservationPage(ApiCityPhotosService apiCityPhotosService) {
        this.apiCityPhotosService = apiCityPhotosService;
        addClassName("reservation-page");
        configureLayoutButtons();
        layout.add(back, confirm);
        add(layout);

    }

    private void configureLayoutButtons(){
        layout.setPadding(true);
        confirm.getElement().getStyle().set("margin-left","center");
        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        back.addThemeVariants(ButtonVariant.LUMO_ERROR);
    }

    private void retrieveCityImage(String city) throws IOException {
        apiCityPhotosService.getImage(city);
    }

}
