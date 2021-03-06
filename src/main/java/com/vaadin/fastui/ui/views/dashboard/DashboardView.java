package com.vaadin.fastui.ui.views.dashboard;

import com.vaadin.fastui.backend.service.CustomerService;
import com.vaadin.fastui.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Map;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    private CustomerService customerService;

    public DashboardView(CustomerService customerService) {
        this.customerService = customerService;

        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(
                getCustomerStats(),
                getCustomerChart()
        );
    }

    private Component getCustomerChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        Map<String, Integer> stats = customerService.getStats();
        stats.forEach((name, number) ->
                dataSeries.add(new DataSeriesItem(name,number)));

        chart.getConfiguration().setSeries(dataSeries);
        return chart;
        }


    private Component getCustomerStats() {
        Span stats = new Span(customerService.count() + "customers");
        stats.addClassName("customer-stats");
        return stats;
    }
}
