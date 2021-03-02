package com.vaadin.fastui.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class CustomerForm extends FormLayout {

    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField phoneNumber = new TextField("PhoneNumber");
    EmailField email = new EmailField("Email");



    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public CustomerForm() {
        addClassName("contact-form");
        add(firstName, lastName, email, phoneNumber, createButtonsLayout());

    }

    private Component createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save,delete,close);
    }
}
