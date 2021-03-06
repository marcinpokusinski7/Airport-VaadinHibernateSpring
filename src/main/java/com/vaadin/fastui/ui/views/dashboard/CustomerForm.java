package com.vaadin.fastui.ui.views.dashboard;

import com.vaadin.fastui.backend.entity.Customer;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class CustomerForm extends FormLayout {
    private Customer customer;
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField phoneNumber = new TextField("PhoneNumber");
    EmailField email = new EmailField("Email");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Customer> binder = new BeanValidationBinder<>(Customer.class); //validator, works based on annoations in Customer.class


    public CustomerForm(List<Customer> customerList) {
        addClassName("customer-form");
        binder.bindInstanceFields(this);

        add(firstName,
                lastName,
                email,
                phoneNumber,
                createButtonsLayout());

    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, customer)));
        close.addClickListener(close -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(event -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
       try{
           binder.writeBean(customer);
           fireEvent(new SaveEvent(this,customer));
       } catch (ValidationException e){
           e.printStackTrace();
       }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        binder.setBean(customer);
    }


    public static abstract class CustomerFormEvent extends ComponentEvent<CustomerForm> {
        private Customer customer;

        protected CustomerFormEvent(CustomerForm source, Customer customer) {
            super(source, false);
            this.customer = customer;
        }

        public Customer getCustomer() {
            return customer;
        }
    }

    public static class SaveEvent extends CustomerFormEvent {
        SaveEvent(CustomerForm source, Customer customer) {
            super(source, customer);
        }
    }

    public static class DeleteEvent extends CustomerFormEvent {
        DeleteEvent(CustomerForm source, Customer customer) {
            super(source, customer);
        }
    }

    public static class CloseEvent extends CustomerFormEvent {
        CloseEvent(CustomerForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
