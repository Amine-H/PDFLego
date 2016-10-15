package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;

/**
 * Created by amine
 */
public abstract class AbstractPlotBlock extends Component{
    private String title;
    private boolean legendVisible;
    private String legendLocation;

    public AbstractPlotBlock(){
        super();
    }

    public AbstractPlotBlock(Component parent){
        super(parent);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLegendVisible() {
        return legendVisible;
    }

    public void setLegendVisible(boolean legendVisible) {
        this.legendVisible = legendVisible;
    }

    public String getLegendLocation() {
        return legendLocation;
    }

    public void setLegendLocation(String legendLocation) {
        this.legendLocation = legendLocation;
    }
}
