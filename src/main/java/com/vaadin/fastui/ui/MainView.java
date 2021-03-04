package com.vaadin.fastui.ui;

import com.vaadin.fastui.backend.entity.Customer;
import com.vaadin.fastui.backend.service.CustomerService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    private final CustomerForm customerForm;
    Dialog dialog = new Dialog();
    Grid<Customer> grid = new Grid<>(Customer.class);
    TextField filterText = new TextField();
    private CustomerService customerService;

    public MainView(CustomerService customerService) {
        this.customerService = customerService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureFilter();

        customerForm = new CustomerForm(customerService.findAll());
        customerForm.addListener(CustomerForm.SaveEvent.class, this::saveContact);
        customerForm.addListener(CustomerForm.DeleteEvent.class,this::deleteContact);

        new Div(grid, customerForm);

        Div content = new Div(grid, customerForm);
        content.addClassName("content");
        content.setSizeFull();

        add(filterText, content);
        updateList();
        closeEditor();
        //deleteCustomer();
    }

    private void deleteContact(CustomerForm.DeleteEvent evt) {

    }


    private void saveContact(CustomerForm.SaveEvent evt) {
        customerService.save(evt.getCustomer());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassName("customer-grid");
        grid.setSizeFull(); // full screen
        grid.setColumns("id", "firstName", "lastName", "email", "phoneNumber"); // order columns by names
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); // automatic width

        grid.asSingleSelect().addValueChangeListener(event -> editCustomer(event.getValue()));
    }

    private void editCustomer(Customer customer) {
        if(customer == null){
            closeEditor();
        }else{
            customerForm.setCustomer(customer);
            customerForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList()); //everytime value in button changes it updates list
    }

    private void closeEditor() {

        customerForm.setCustomer(null);
        customerForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(customerService.findAll(filterText.getValue()));
    }

//    private void deleteCustomer() {
//
//        GridMultiSelectionModel<Customer> selectionModel =
//                (GridMultiSelectionModel<Customer>)
//                        grid.setSelectionMode(Grid.SelectionMode.MULTI);
//
//    }
}
