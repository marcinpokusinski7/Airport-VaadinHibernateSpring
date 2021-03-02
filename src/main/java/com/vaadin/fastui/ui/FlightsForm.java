package com.vaadin.fastui.ui;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.awt.*;

public class FlightsForm extends FormLayout {

    TextField areoplaneFlightId = new TextField();
    TextField fromCity = new TextField();
    TextField toCity = new TextField();
    TextArea seatsLeft = new TextArea();




    Button add = new Button("Add");
    Button delete = new Button("Delete");


}
