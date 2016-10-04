package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;

/**
 * Created by amine
 */
public class PLBarPlotBlock extends AbstractPlotBlock{
    private double[] data;
    private double barWidth;

    public PLBarPlotBlock(Component parent){
        super(parent);
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public double getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(double barWidth) {
        this.barWidth = barWidth;
    }
}
