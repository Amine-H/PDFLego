package com.xhub.pdflego.bloc;

import com.xhub.pdflego.bloc.data.barplot.PLBarPlotData;
import com.xhub.pdflego.bloc.data.barplot.PLBarPlotPoint;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.PLColor;

import java.util.List;

/**
 * Created by amine
 */
public class PLBarPlotBlock extends AbstractPlotBlock{
    private List<PLBarPlotData> plots;
    private double barWidth;
    private float borderStroke;
    private PLColor borderColor;
    private boolean valueVisible;

    public PLBarPlotBlock(Component parent){
        super(parent);
    }

    public List<PLBarPlotData> getPlots() {
        return plots;
    }

    public void setPlots(List<PLBarPlotData> plots) {
        this.plots = plots;
    }

    public double getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(double barWidth) {
        this.barWidth = barWidth;
    }

    public float getBorderStroke() {
        return borderStroke;
    }

    public void setBorderStroke(float borderStroke) {
        this.borderStroke = borderStroke;
    }

    public PLColor getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(PLColor borderColor) {
        this.borderColor = borderColor;
    }

    public boolean isValueVisible() {
        return valueVisible;
    }

    public void setValueVisible(boolean valueVisible) {
        this.valueVisible = valueVisible;
    }
}