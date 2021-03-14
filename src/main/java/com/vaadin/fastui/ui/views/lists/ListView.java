package com.vaadin.fastui.ui.views.lists;

import com.vaadin.fastui.backend.entity.Customer;
import com.vaadin.fastui.backend.service.CustomerService;
import com.vaadin.fastui.ui.MainLayout;
import com.vaadin.fastui.ui.views.forms.CustomerForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "list", layout = MainLayout.class)
@PageTitle("Customer List")
public class ListView extends VerticalLayout {

    private final CustomerForm customerForm;

    Grid<Customer> grid = new Grid<>(Customer.class);
    TextField filterText = new TextField();
    private CustomerService customerService;

    public ListView(CustomerService customerService) {
        this.customerService = customerService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        getToolBar();

        customerForm = new CustomerForm(customerService.findAll());
        customerForm.addListener(CustomerForm.SaveEvent.class, this::saveContact);
        customerForm.addListener(CustomerForm.DeleteEvent.class, this::deleteContact);
        customerForm.addListener(CustomerForm.CloseEvent.class, e -> closeEditor());

        new Div(grid, customerForm);

        Div content = new Div(grid, customerForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();

    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList()); //everytime value in button changes it updates list

        Button addCustomerButton = new Button("Add customer", click -> addCustomer());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addCustomerButton);
        toolbar.addClassName("toolbar");
        return toolbar;

    }

    private void addCustomer() {
        grid.asSingleSelect().clear();
        editCustomer(new Customer());
    }

    private void deleteContact(CustomerForm.DeleteEvent evt) {
        customerService.delete(evt.getCustomer());
        updateList();
        closeEditor();
    }


    private void saveContact(CustomerForm.SaveEvent evt) {
        customerService.save(evt.getCustomer());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassName("customer-grid");
        grid.setSizeFull(); // full screen
        grid.setColumns("customerId", "firstName", "lastName", "email", "phoneNumber"); // order columns by names
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); // automatic width

        grid.asSingleSelect().addValueChangeListener(event -> editCustomer(event.getValue()));
    }

    private void editCustomer(Customer customer) {
        if (customer == null) {
            closeEditor();
        } else {
            customerForm.setCustomer(customer);
            customerForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {

        customerForm.setCustomer(null);
        customerForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(customerService.findAll(filterText.getValue()));
    }

}