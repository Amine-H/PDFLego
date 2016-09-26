package com.xhub.pdflego.bloc;

import com.itextpdf.kernel.color.Color;
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
    private Color[] colors;
    private Float gap;
    private Float radius;

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

    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }
}
