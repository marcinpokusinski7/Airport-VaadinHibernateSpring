package com.vaadin.fastui.ui.views.dashboard;

import com.vaadin.fastui.backend.service.ApiCityPhotosService;
import com.vaadin.fastui.backend.service.CustomerService;
import com.vaadin.fastui.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.IOException;

@PageTitle("Dashboard")
@Route(value = "", layout = MainLayout.class)
public class ExampleDestinations extends VerticalLayout {
    private CustomerService customerService;
    private ApiCityPhotosService apiCityPhotosService;

    HorizontalLayout layout = new HorizontalLayout();
    HorizontalLayout layout2 = new HorizontalLayout();
    HorizontalLayout layout3 = new HorizontalLayout();
    Button goTo = new Button("Check Price");
    Button goTo2 = new Button("Check Price");
    Button goTo3 = new Button("Check Price");
    public ExampleDestinations(CustomerService customerService,
                               ApiCityPhotosService apiCityPhotosService) throws IOException {
        this.customerService = customerService;
        this.apiCityPhotosService = apiCityPhotosService;

        addClassName("dashboard-view");
        layout.addClassName("ordered-layout");
        layout2.addClassName("ordered-layout");
        layout3.addClassName("ordered-layout");
        goTo.addClassName("goTo");
        goTo2.addClassName("goTo");
        goTo3.addClassName("goTo");

        H1 h1 = new H1("Example desinations");
        h1.addClassName("h1");
        h1.setSizeFull();
        configureOrderedLayout();
        configureOrderedLayoutTwo();
        configureOrderedLayoutThree();
        add(h1, layout, layout2, layout3);

    }

    private void configureOrderedLayout() throws IOException {


        String t = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna" +
                " aliqua. Ut enim ad minim veniam, quis nostrud exercitation" +
                " ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit " +
                "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint " +
                "occaecat cupidatat non proident, sunt in culpa qui officia " +
                "deserunt mollit anim id est laborum.";

        Div component1 = new Div();
        Div component2 = new Div();

        component1.add(getImage("Paris"));
        component2.setText(String.valueOf(new H1("Paris")));
        component2.setText(t);
        goTo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(component2, goTo);
        verticalLayout.setHorizontalComponentAlignment(Alignment.END, goTo);
        layout.addAndExpand(component1, verticalLayout);

        layout.setPadding(true);
        layout.getAlignItems();
    }
    private void configureOrderedLayoutTwo() throws IOException {

        String t = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna" +
                " aliqua. Ut enim ad minim veniam, quis nostrud exercitation" +
                " ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit " +
                "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint " +
                "occaecat cupidatat non proident, sunt in culpa qui officia " +
                "deserunt mollit anim id est laborum.";

        Div component1 = new Div();
        Div component2 = new Div();
        component1.add(getImage("Berlin"));
        component2.setText(String.valueOf(new H1("Berlin")));
        component2.setText(t);

        goTo2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(component2, goTo2);
        verticalLayout.setHorizontalComponentAlignment(Alignment.END, goTo2);
        layout2.addAndExpand(component1, verticalLayout);
        layout2.setPadding(true);
        layout2.getAlignItems();
    }

    private void configureOrderedLayoutThree() throws IOException {

        String t = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna" +
                " aliqua. Ut enim ad minim veniam, quis nostrud exercitation" +
                " ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit " +
                "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint " +
                "occaecat cupidatat non proident, sunt in culpa qui officia " +
                "deserunt mollit anim id est laborum.";

        Div component1 = new Div();
        Div component2 = new Div();
        component1.add(getImage("Greece"));
        component2.setText(String.valueOf(new H1("Greece")));
        component2.setText(t);
        goTo3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(component2, goTo3);
        verticalLayout.setHorizontalComponentAlignment(Alignment.END, goTo3);
        layout3.addAndExpand(component1, verticalLayout);
        layout3.setPadding(true);
        layout3.getAlignItems();
    }

    public Image getImage(String city) throws IOException {

        Image image =new Image(apiCityPhotosService.getImage(city), "city");
        image.addClassName("maxSize500");
        return image;
    }



}


