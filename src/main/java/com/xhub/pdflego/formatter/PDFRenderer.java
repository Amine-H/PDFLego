package com.xhub.pdflego.formatter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.graphics.DrawingContext;
import de.erichseifert.gral.io.plots.DrawableWriter;
import de.erichseifert.gral.io.plots.DrawableWriterFactory;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import org.apache.log4j.Logger;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.xhub.pdflego.bloc.PLImageBlock;
import com.xhub.pdflego.bloc.PLXYChartBlock;
import com.xhub.pdflego.bloc.PLTextBlock;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.Composite;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;



/**
 * Created by amine
 */
public class PDFRenderer extends DocumentRenderer<ByteArrayOutputStream> {
    private Logger logger = Logger.getLogger(PDFRenderer.class);
    private PageSize pageSize;
    private ByteArrayOutputStream outputStream;
    private PdfWriter pdfWriter;
    private PdfDocument pdfDocument;
    private Document document;
    private PdfPage currentPage;
    private PdfCanvas currentPageCanvas;
    private Canvas currentBlock;//@TODO find a better way to do this

    public PDFRenderer(Composite rootComponent){
        this(rootComponent, PageSize.A4);
    }

    public PDFRenderer(Composite rootComponent, PageSize pageSize){
        super(rootComponent);
        this.pageSize = pageSize;
        this.outputStream = new ByteArrayOutputStream();
        this.pdfWriter = new PdfWriter(outputStream);
        this.pdfDocument = new PdfDocument(pdfWriter);
        this.document = new Document(pdfDocument, pageSize);
        this.document.setMargins(0, 0, 0, 0);
        this.newPage();
    }

    private void newPage(){
        this.currentPage = this.pdfDocument.addNewPage();
        this.currentPageCanvas = new PdfCanvas(this.currentPage);
    }

    private float calculateY(Component component){
        return pageSize.getTop() - component.getHeight() - component.getY();
    }

    @Override
    public void renderBlock(Component component) {
        logger.info(component + " started rendering");
        Rectangle rectangle = new Rectangle(component.getX(), calculateY(component), component.getWidth(), component.getHeight());
        this.currentBlock = new Canvas(this.currentPageCanvas, this.pdfDocument, rectangle);
        this.renderDefaultBlock(component);
        //render text block
        if(component.getClass().equals(PLTextBlock.class)){
            this.renderTextBlock((PLTextBlock) component);
        }
        //render image block
        else if(component.getClass().equals(PLImageBlock.class)){
            this.renderImageBlock((PLImageBlock) component);
        }
        //render lineChart block
        else if(component.getClass().equals(PLXYChartBlock.class)){
            this.renderXYChartBlock((PLXYChartBlock) component);
        }else{
            logger.warn("Unhandled type");
        }
        this.currentBlock = null;
        logger.info(component + " finished rendering");
    }

    @Override
    public void renderDefaultBlock(Component component) {

    }

    @Override
    public void renderImageBlock(PLImageBlock imageBlock) {
        ImageData imageData = imageBlock.getImage();
        if(imageData != null){
            Image image = new Image(imageBlock.getImage());
            currentBlock.add(image);
        }else{
            logger.warn(imageBlock + " doesn't have any data, object not drawn");
        }
    }

    @Override
    public void renderXYChartBlock(PLXYChartBlock lineChartBlock) {
        int width = lineChartBlock.getWidth();
        int height = lineChartBlock.getHeight();
        XYPlot plot = new XYPlot(lineChartBlock.getData());
        plot.getAxisRenderer(XYPlot.AXIS_X).setIntersection(-Double.MAX_VALUE);
        plot.getAxisRenderer(XYPlot.AXIS_Y).setIntersection(-Double.MAX_VALUE);
        plot.getTitle().setText(lineChartBlock.getTitle());
        Color backgroundColor = lineChartBlock.getBackgroundColor();
        Color titleColor = lineChartBlock.getTitleColor();
        Color[] seriesColor = lineChartBlock.getSeriesColor();
        if(backgroundColor != null){
            plot.setBackground(backgroundColor);
            plot.getPlotArea().setBackground(backgroundColor);
            plot.getTitle().setBackground(backgroundColor);
        }
        if(titleColor != null){
            plot.getTitle().setColor(titleColor);
        }
        if(seriesColor != null){
            for(int i = 0;i < lineChartBlock.getSeriesColor().length;i++){
                try{
                    PointRenderer ptRenderer = new DefaultPointRenderer2D();
                    LineRenderer lineRenderer = new DefaultLineRenderer2D();
                    DataSeries[] data = lineChartBlock.getData();
                    ptRenderer.setColor(lineChartBlock.getSeriesColor()[i]);
                    lineRenderer.setColor(lineChartBlock.getSeriesColor()[i]);
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
            PLImageBlock image = PLImageBlock.create(lineChartBlock);
            image.setImage(stream.toByteArray());
            this.renderImageBlock(image);
            stream.close();
        } catch (IOException e) {
            logger.error("could not write the plot to " + stream, e);
        }
    }


    @Override
    public void renderTextBlock(PLTextBlock textBlock) {
        String text = new String(textBlock.getText());
        Integer blockWidth = textBlock.getWidth();
        Integer blockHeight = textBlock.getHeight();
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        String myLine = "";

        // get all words from the text
        for(String word : words) {
            if(!myLine.isEmpty()) {
                myLine += " ";
            }
            // test the width of the current line + the current word
            int textWidth = (int) (textBlock.getFontSize() * textBlock.getFont().getWidth(myLine + word) / 1000);
            if(textWidth > blockWidth) {
                // if the line would be too long with the current word, add the line without the current word
                lines.add(myLine);
                // and start a new line with the current word
                myLine = word;
            } else {
                // if the current line + the current word would fit, add the current word to the line
                myLine += word;
            }
        }
        // add the rest to lines
        lines.add(myLine);
        //draw the list of lines
        float textHeight = 0f;
        float lineHeight = textBlock.getFontSize() + textBlock.getLineSpacing();

        for(String line: lines){
            if(textHeight + lineHeight > blockHeight){
                this.logger.warn("text is bigger than the Component's Height, ignoring the rest of the text");
                break;
            }
            Paragraph paragraph = new Paragraph(line)
                    .setFixedLeading(textBlock.getLineSpacing())
                    .setFont(textBlock.getFont())
                    .setFontSize(textBlock.getFontSize());
            currentBlock.add(paragraph);
            textHeight += lineHeight;
        }
    }

    @Override
    public ByteArrayOutputStream render() {
        for(Component component: rootComponent.getChildComponents()){
            this.renderBlock(component);
        }
        document.close();
        return outputStream;
    }
}
