package com.xhub.pdflego.bloc;

import com.xhub.pdflego.bloc.data.barplot.PLBarPlotData;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.PLColor;

import java.util.List;

/**
 * Created by amine
 */
public class PLBarPlotBlock extends AbstractPlotBlock{
    private String className = "PLBarPlotBlock";
    private List<PLBarPlotData> plots;
    private Double barWidth;
    private float borderStroke;
    private PLColor borderColor;
    private boolean valueVisible;

    public PLBarPlotBlock(Component parent){
        super(parent);
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    public List<PLBarPlotData> getPlots() {
        return plots;
    }

    public void setPlots(List<PLBarPlotData> plots) {
        this.plots = plots;
    }

    public Double getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(Double barWidth) {
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