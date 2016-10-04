package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.xhub.pdflego.bloc.PLBarPlotBlock;
import com.xhub.pdflego.formatter.PlotRenderHelper;
import de.erichseifert.gral.plots.BarPlot;
import org.apache.log4j.Logger;

/**
 * Created by amine
 */
public class BarPlotRenderStrategy extends PlotRenderHelper<PLBarPlotBlock> implements ComponenRenderStrategy<PLBarPlotBlock>{
    private Logger logger = Logger.getLogger(BarPlotRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLBarPlotBlock component) {
        double data[] = component.getData();
        if(data != null & data.length > 0){
            BarPlot plot = new BarPlot();
            super.preparePlot(plot, component);


            super.drawPlot(plot, component, componentCanvas);
        }else{
            this.logger.warn("no data for " + component + " block was not rendered");
        }
    }
}
