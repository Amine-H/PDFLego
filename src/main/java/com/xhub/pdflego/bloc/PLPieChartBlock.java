package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.ColorVO;

/**
 * Created by amine
 */
public class PLPieChartBlock extends Component{
    public PLPieChartBlock(Component parent){
        super(parent);
    }
    private String title;
    private Integer[] data;
    private ColorVO pieColor;
    private ColorVO[] colors;
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

    public ColorVO getPieColor() {
        return pieColor;
    }

    public void setPieColor(ColorVO pieColor) {
        this.pieColor = pieColor;
    }

    public ColorVO[] getColors() {
        return colors;
    }

    public void setColors(ColorVO... colors) {
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
