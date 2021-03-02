package com.vaadin.fastui.ui;

import com.vaadin.fastui.backend.dao.CustomerDAO;
import com.vaadin.fastui.backend.entity.Customer;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("")
public class MainView extends VerticalLayout {

    Grid<Customer> grid = new Grid<>(Customer.class);
    private CustomerDAO customerDAO;

    public MainView(CustomerDAO customerDAO) {
        addClassName("list-view");
        setSizeFull();
        configureGrid();


        add(grid);

        updateList();
    }

    private void updateList() {
        grid.setItems(customerDAO.getCustomers());
    }

    private void configureGrid() {
        grid.addClassName("customer-grid");
        grid.setSizeFull();
        grid.setColumns("id", "firstName", "lastName", "email");
    }

}
