package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.Canvas;
import com.xhub.pdflego.bloc.PLPieChartBlock;
import com.xhub.pdflego.formatter.PlotRenderHelper;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.PiePlot.PieSliceRenderer;
import de.erichseifert.gral.plots.colors.LinearGradient;
import org.apache.log4j.Logger;

/**
 * Created by amine
 */
public class PieChartRenderStrategy extends PlotRenderHelper<PLPieChartBlock> implements ComponenRenderStrategy<PLPieChartBlock>{
    private Logger logger = Logger.getLogger(PieChartRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLPieChartBlock component) {
        Integer[] dataset = component.getData();
        Float gap = component.getGap();
        Float radius = component.getRadius();
        Color[] colors = component.getColors();
        if(dataset != null){
            DataTable dataTable = new DataTable(Integer.class);
            String title = component.getTitle();
            dataTable.add(dataset);
            PiePlot plot = new PiePlot(dataTable);
            PieSliceRenderer pointRenderer = (PieSliceRenderer) plot.getPointRenderer(dataTable);

            if(title != null){
                plot.getTitle().setText(title);
            }
            if(gap != null){
                pointRenderer.setGap(gap);
            }
            if(radius != null){
                pointRenderer.setInnerRadius(radius);
            }
            if(colors != null){
                java.awt.Color color1 = java.awt.Color.BLUE;
                java.awt.Color color2 = java.awt.Color.CYAN;
                pointRenderer.setColor(new LinearGradient(color1, color2));
            }
            drawPlot(plot, component, componentCanvas);
        }else{
            logger.warn("no data found for " + component + ", ignoring Block");
        }
    }
}
