package com.vaadin.fastui.ui;

import com.vaadin.fastui.backend.entity.Customer;
import com.vaadin.fastui.backend.service.CustomerService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("")
public class MainView extends VerticalLayout {

    Dialog dialog = new Dialog();

    Grid<Customer> grid = new Grid<>(Customer.class);
    private CustomerService customerService;

    public MainView(CustomerService customerService) {
        this.customerService = customerService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(grid);
        updateList();
        deleteCustomer();
    }

    private void updateList() {
        grid.setItems(customerService.findAll());
    }

    private void configureGrid() {
        grid.addClassName("customer-grid");
        grid.setSizeFull();
        grid.setColumns("id", "firstName","lastName","email");
        grid.addColumn("delete");
    }
    private void deleteCustomer(){

        GridMultiSelectionModel<Customer> selectionModel =
                (GridMultiSelectionModel<Customer>)
                        grid.setSelectionMode(Grid.SelectionMode.MULTI);

                if(selectionModel.isSelected(customerService.getCustomerRepository().getOne())){
                    customerService.delete(customer);
                }else{
                    dialog.add(new Text("Nothing is selected"))
                }
    }
}
