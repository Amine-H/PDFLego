package com.xhub.pdflego.bloc.data.lineplot;

import java.util.List;

/**
 * Created by amine
 */
public class PLLinePlotData {
    private List<Double> x;
    private List<PLLineYData> y;

    public List<Double> getX() {
        return x;
    }

    public void setX(List<Double> x) {
        this.x = x;
    }

    public List<PLLineYData> getY() {
        return y;
    }

    public void setY(List<PLLineYData> y) {
        this.y = y;
    }
}
