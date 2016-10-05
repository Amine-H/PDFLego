package com.xhub.pdflego.bloc.data.barplot;

import com.xhub.pdflego.core.vo.PLColor;

import java.util.List;

/**
 * Created by amine
 */
public class PLBarPlotData {
    private String title;
    private List<PLBarPlotPoint> data;
    private PLColor color;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PLBarPlotPoint> getData() {
        return data;
    }

    public void setData(List<PLBarPlotPoint> data) {
        this.data = data;
    }

    public PLColor getColor() {
        return color;
    }

    public void setColor(PLColor color) {
        this.color = color;
    }
}
