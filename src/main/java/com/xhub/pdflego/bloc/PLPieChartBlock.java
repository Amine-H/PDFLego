package com.xhub.pdflego.bloc;

import java.awt.Color;
import com.xhub.pdflego.core.Component;

/**
 * Created by amine
 */
public class PLPieChartBlock extends Component{
    public PLPieChartBlock(Component parent){
        super(parent);
    }
    private String title;
    private Integer[] data;
    private Color pieColor;
    private Color[] colors;
    private Float gap;
    private Float innerRadius;
    private Float outerRadius;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer[] getData() {
        return data;
    }

    public void setData(Integer[] data) {
        this.data = data;
    }

    public Color getPieColor() {
        return pieColor;
    }

    public void setPieColor(Color pieColor) {
        this.pieColor = pieColor;
    }

    public Color[] getColors() {
        return colors;
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }

    public Float getGap() {
        return gap;
    }

    public void setGap(Float gap) {
        this.gap = gap;
    }

    public Float getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(Float innerRadius) {
        this.innerRadius = innerRadius;
    }

    public Float getOuterRadius() {
        return outerRadius;
    }

    public void setOuterRadius(Float outerRadius) {
        this.outerRadius = outerRadius;
    }
}
