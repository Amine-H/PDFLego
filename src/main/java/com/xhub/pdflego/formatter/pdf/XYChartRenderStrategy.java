package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.xhub.pdflego.bloc.PLImageBlock;
import com.xhub.pdflego.bloc.PLXYChartBlock;
import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.graphics.DrawingContext;
import de.erichseifert.gral.io.plots.DrawableWriter;
import de.erichseifert.gral.io.plots.DrawableWriterFactory;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import org.apache.log4j.Logger;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by amine
 */
public class XYChartRenderStrategy implements ComponenRenderStrategy<PLXYChartBlock>{
    private Logger logger = Logger.getLogger(XYChartRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLXYChartBlock component) {
        int width = component.getWidth();
        int height = component.getHeight();
        XYPlot plot = new XYPlot(component.getData());
        plot.getAxisRenderer(XYPlot.AXIS_X).setIntersection(-Double.MAX_VALUE);
        plot.getAxisRenderer(XYPlot.AXIS_Y).setIntersection(-Double.MAX_VALUE);
        plot.getTitle().setText(component.getTitle());
        plot.setLegendVisible(component.isLegendVisible());
        Color backgroundColor = component.getPlotBackgroundColor();
        Color titleColor = component.getTitleColor();
        Color[] seriesColor = component.getSeriesColor();
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
                    ptRenderer.setColor(component.getSeriesColor()[i]);
                    lineRenderer.setColor(component.getSeriesColor()[i]);
                    plot.setPointRenderers(data[i], ptRenderer);
                    plot.setLineRenderers(data[i], lineRenderer);
                }catch(NullPointerException e){
                    logger.error("Error caused when trying to set color for a series", e);
                }
            }
        }
        BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bImage.getGraphics();
        DrawingContext context = new DrawingContext(g2d);
        plot.draw(context);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DrawableWriter wr = DrawableWriterFactory.getInstance().get("image/jpeg");
        try {
            wr.write(plot, stream, width, height);
            stream.flush();
            PLImageBlock image = PLImageBlock.create(component);
            image.setImage(stream.toByteArray());
            ImageRenderStrategy imageRenderer = new ImageRenderStrategy();
            imageRenderer.render(componentCanvas, image);
            stream.close();
        } catch (IOException e) {
            logger.error("could not write the plot to " + stream, e);
        }
    }
}
