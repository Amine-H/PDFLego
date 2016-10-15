package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.PLColor;

/**
 * Created by amine
 */
public class PLPieChartBlock extends AbstractPlotBlock{
    private String className = "PLPieChartBlock";
    private Integer[] data;
    private PLColor pieColor;
    private PLColor[] colors;
    private Float gap;
    private Float innerRadius;
    private Float outerRadius;

    public PLPieChartBlock(){

    }

    public PLPieChartBlock(Component parent){
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

    public Integer[] getData() {
        return data;
    }

    public void setData(Integer[] data) {
        this.data = data;
    }

    public PLColor getPieColor() {
        return pieColor;
    }

    public void setPieColor(PLColor pieColor) {
        this.pieColor = pieColor;
    }

    public PLColor[] getColors() {
        return colors;
    }

    public void setColors(PLColor... colors) {
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
