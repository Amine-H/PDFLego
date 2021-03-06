package com.xhub.pdflego.formatter.pdf;

import com.xhub.pdflego.bloc.AbstractPlotBlock;
import com.xhub.pdflego.bloc.PLImageBlock;
import com.xhub.pdflego.core.vo.PLColor;
import com.xhub.pdflego.core.vo.PLFile;
import com.xhub.pdflego.core.vo.PLImage;
import de.erichseifert.gral.graphics.Drawable;
import de.erichseifert.gral.graphics.DrawingContext;
import de.erichseifert.gral.graphics.Location;
import de.erichseifert.gral.io.plots.DrawableWriter;
import de.erichseifert.gral.io.plots.DrawableWriterFactory;
import de.erichseifert.gral.plots.AbstractPlot;
import com.itextpdf.layout.Canvas;
import org.apache.log4j.Logger;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by amine
 */
public class PlotRenderHelper<T extends AbstractPlotBlock> {
    private Logger logger = Logger.getLogger(PlotRenderHelper.class);

    public void preparePlot(AbstractPlot plot, T component){
        String title = component.getTitle();
        String legendLocationString = component.getLegendLocation();
        Location legendLocation = null;
        Color backgroundColor = PLColor.create(component.getBackgroundColor(), Color.class);
        Color titleColor = PLColor.create(component.getFontColor(), Color.class);
        try{
            legendLocation = (legendLocationString != null)? Location.valueOf(legendLocationString) : Location.CENTER;
        }catch(IllegalArgumentException e){
            logger.warn("location " + legendLocationString + " not supported", e);
        }
        boolean legendVisible = component.isLegendVisible();
        if(title != null) plot.getTitle().setText(title);
        if(legendVisible) plot.setLegendVisible(component.isLegendVisible());
        if(backgroundColor != null){
            plot.setBackground(backgroundColor);
            plot.getPlotArea().setBackground(backgroundColor);
            plot.getTitle().setBackground(backgroundColor);
        }
        if(titleColor != null){
            plot.getTitle().setColor(titleColor);
        }
        plot.setLegendLocation(legendLocation);
    }

    public void drawPlot(Drawable plot, T component, Canvas componentCanvas){
        int width = component.getWidth();
        int height = component.getHeight();
        BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bImage.getGraphics();
        DrawingContext context = new DrawingContext(g2d);
        plot.draw(context);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DrawableWriter wr = DrawableWriterFactory.getInstance().get("image/png");
        try {
            wr.write(plot, stream, width, height);
            stream.flush();
            PLImageBlock image = PLImageBlock.create(component);
            image.setImage(new PLImage(PLFile.createInstance(stream.toByteArray())));
            ImageRenderStrategy imageRenderer = new ImageRenderStrategy();
            imageRenderer.render(componentCanvas, image);
            stream.close();
        } catch (IOException e) {
            logger.error("could not write the plot to " + stream, e);
        }
    }
}
