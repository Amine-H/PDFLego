package com.xhub.pdflego.formatter;

import com.xhub.pdflego.bloc.PLImageBlock;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.formatter.pdf.ImageRenderStrategy;
import de.erichseifert.gral.graphics.DrawingContext;
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
public class PlotRenderHelper<T extends Component> {
    private Logger logger = Logger.getLogger(PlotRenderHelper.class);
    public void drawPlot(AbstractPlot plot, T component, Canvas componentCanvas){
        int width = component.getWidth();
        int height = component.getHeight();
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
