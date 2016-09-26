package com.xhub.pdflego.formatter;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.xhub.pdflego.bloc.*;
import com.xhub.pdflego.formatter.pdf.*;
import org.apache.log4j.Logger;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.Composite;
import com.itextpdf.layout.Canvas;


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
    private Map<Class, Class> renderStrategies;
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
        this.renderStrategies = new HashMap<Class, Class>(){{
            put(Component.class, DefaultComponentRenderStrategy.class);
            put(PLImageBlock.class, ImageRenderStrategy.class);
            put(PLTextBlock.class, TextRenderStrategy.class);
            put(PLTableBlock.class, TableRenderStrategy.class);
            put(PLXYChartBlock.class, XYChartRenderStrategy.class);
            put(PLPieChartBlock.class, PieChartRenderStrategy.class);
        }};
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

        this.renderStrategies.forEach((c, strategy) -> {
            if(c.isInstance(component)){
                try{
                    ComponenRenderStrategy strategyInstance = (ComponenRenderStrategy)strategy.getConstructor().newInstance();
                    strategyInstance.render(currentBlock, component);
                }catch(NoSuchMethodException e){
                    this.logger.error("Could not find default Constructor for " + strategy, e);
                } catch (IllegalAccessException e) {
                    this.logger.error("Wong accessor was set to the strategy " + strategy, e);
                } catch (InstantiationException e) {
                    this.logger.error("Could not instantiate " + strategy, e);
                } catch (InvocationTargetException e) {
                    this.logger.error("Exception thrown by invoked method of " + strategy, e);
                }
            }
        });
        this.currentBlock = null;
        logger.info(component + " finished rendering");
    }


    @Override
    public ByteArrayOutputStream render() {
        this.renderBlock(rootComponent);
        document.close();
        return outputStream;
    }
}
