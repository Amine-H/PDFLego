package com.xhub.pdflego.formatter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
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

    }

    @Override
    public void renderXYChartBlock(PLXYChartBlock lineChartBlock) {

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
