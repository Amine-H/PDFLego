package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.xhub.pdflego.bloc.PLXYChartBlock;
import com.xhub.pdflego.core.vo.ColorVO;
import com.xhub.pdflego.formatter.PlotRenderHelper;
import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import org.apache.log4j.Logger;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by amine
 */
public class XYChartRenderStrategy extends PlotRenderHelper<PLXYChartBlock> implements ComponenRenderStrategy<PLXYChartBlock>{
    private Logger logger = Logger.getLogger(XYChartRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLXYChartBlock component) {
        XYPlot plot = new XYPlot(component.getData());
        plot.getAxisRenderer(XYPlot.AXIS_X).setIntersection(-Double.MAX_VALUE);
        plot.getAxisRenderer(XYPlot.AXIS_Y).setIntersection(-Double.MAX_VALUE);
        plot.getTitle().setText(component.getTitle());
        plot.setLegendVisible(component.isLegendVisible());
        Color backgroundColor = ColorVO.create(component.getPlotBackgroundColor(), Color.class);
        Color titleColor = ColorVO.create(component.getTitleColor(), Color.class);
        Color[] seriesColor = (Color[])Arrays.stream(component.getSeriesColor()).map(color -> ColorVO.create(color, Color.class)).toArray();
        if(backgroundColor != null){
            plot.setBackground(backgroundColor);
            plot.getPlotArea().setBackground(backgroundColor);
            plot.getTitle().setBackground(backgroundColor);
        }
        if(titleColor != null){
            plot.getTitle().setColor(titleColor);
        }
        if(seriesColor != null){
            for(int i = 0;i < component.getSeriesColor().length;i++){
                try{
                    PointRenderer ptRenderer = new DefaultPointRenderer2D();
                    LineRenderer lineRenderer = new DefaultLineRenderer2D();
                    DataSeries[] data = component.getData();
                    Color lineColor = ColorVO.create(component.getSeriesColor()[i], Color.class);
                    ptRenderer.setColor(lineColor);
                    lineRenderer.setColor(lineColor);
                    plot.setPointRenderers(data[i], ptRenderer);
                    plot.setLineRenderers(data[i], lineRenderer);
                }catch(NullPointerException e){
                    logger.error("Error caused when trying to set color for a series", e);
                }
            }
        }
        drawPlot(plot, component, componentCanvas);
    }
}