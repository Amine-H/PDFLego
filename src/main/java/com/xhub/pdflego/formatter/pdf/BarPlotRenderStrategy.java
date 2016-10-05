package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.xhub.pdflego.bloc.PLBarPlotBlock;
import com.xhub.pdflego.bloc.data.barplot.PLBarPlotData;
import com.xhub.pdflego.bloc.data.barplot.PLBarPlotPoint;
import com.xhub.pdflego.core.vo.PLColor;
import com.xhub.pdflego.formatter.PlotRenderHelper;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.BarPlot;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.List;

/**
 * Created by amine
 */
public class BarPlotRenderStrategy extends PlotRenderHelper<PLBarPlotBlock> implements ComponenRenderStrategy<PLBarPlotBlock>{
    private Logger logger = Logger.getLogger(BarPlotRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLBarPlotBlock component) {
        List<PLBarPlotData> plots = component.getPlots();
        double barWidth = component.getBarWidth();
        if(plots != null & plots.size() > 0){
            BarPlot plot = new BarPlot();
            super.preparePlot(plot, component);

            for(PLBarPlotData p:plots){
                DataTable dataTable = new DataTable(double.class, double.class, String.class);
                for(PLBarPlotPoint point:p.getData()){
                    dataTable.add(point.getxData(), point.getyData(), point.getLabel());
                }
                plot.add(dataTable);
                BarPlot.BarRenderer pointRenderer = (BarPlot.BarRenderer) plot.getPointRenderers(dataTable).get(0);
                Color color = PLColor.create(p.getColor(), Color.class);
                pointRenderer.setColor(color);
                pointRenderer.setBorderStroke(new BasicStroke(component.getBorderStroke()));
                pointRenderer.setBorderColor(PLColor.create(component.getBorderColor(), Color.class));
                pointRenderer.setValueVisible(component.isValueVisible());
            }
            if(barWidth > 0) plot.setBarWidth(barWidth);

            super.drawPlot(plot, component, componentCanvas);
        }else{
            this.logger.warn("data for " + component + " isn't good enough, block was not rendered");
        }
    }
}
