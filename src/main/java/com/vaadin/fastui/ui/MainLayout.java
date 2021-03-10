package com.vaadin.fastui.ui;

import com.vaadin.fastui.ui.views.dashboard.ExampleDestinations;
import com.vaadin.fastui.ui.views.lists.FlightsList;
import com.vaadin.fastui.ui.views.lists.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@CssImport("./styles/shared-styles.css")
@JsModule( "@vaadin/vaadin-lumo-styles/presets/compact.js" )
@Theme( Lumo.class )
public class MainLayout extends AppLayout {

    public MainLayout() {
    createHeader();
    createDrawer();
    
    }

    private void createHeader() {
        H1 logo = new H1("Customer service");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("Customers List", ListView.class);
        RouterLink flightLink = new RouterLink("Flights List", FlightsList.class);
        flightLink.setHighlightCondition(HighlightConditions.sameLocation());
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        Tabs tabs = new Tabs(new Tab(flightLink), new Tab(listLink), new Tab(new RouterLink("Example Destination", ExampleDestinations.class)));
        tabs.addClassName("tabs-drawer");
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        setDrawerOpened(false);
        addToDrawer(tabs);

    }


}
