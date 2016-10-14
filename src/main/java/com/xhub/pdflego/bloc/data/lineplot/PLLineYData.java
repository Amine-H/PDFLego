package com.xhub.pdflego.bloc.data.lineplot;

import com.xhub.pdflego.core.vo.PLColor;

import java.util.List;

/**
 * Created by amine
 */
public class PLLineYData {
    private List<Double> y;
    private PLColor color;
    private String name;

    public List<Double> getY() {
        return y;
    }

    public void setY(List<Double> y) {
        this.y = y;
    }

    public PLColor getColor() {
        return color;
    }

    public void setColor(PLColor color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
