package com.xhub.pdflego.bloc.data.barplot;

/**
 * Created by amine
 */
public class PLBarPlotPoint {
    private Double xData;
    private Double yData;
    private String label;

    public Double getxData() {
        return xData;
    }

    public void setxData(Double xData) {
        this.xData = xData;
    }

    public Double getyData() {
        return yData;
    }

    public void setyData(Double yData) {
        this.yData = yData;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}