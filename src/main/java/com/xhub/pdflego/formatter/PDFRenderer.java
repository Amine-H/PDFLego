package com.xhub.pdflego.formatter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.property.UnitValue;
import com.xhub.pdflego.bloc.PLImageBlock;
import com.xhub.pdflego.bloc.PLLineChartBlock;
import com.xhub.pdflego.bloc.PLTextBlock;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.Composite;

/**
 * Created by amine
 */
public class PDFRenderer extends DocumentRenderer<ByteArrayOutputStream> {
    private Logger logger = Logger.getLogger(PDFRenderer.class);
    private PageSize pageSize;
    private Document document;

    public PDFRenderer(Composite rootComponent){
        this(rootComponent, PageSize.A4);
    }

    public PDFRenderer(Composite rootComponent, PageSize pageSize){
        super(rootComponent);
        this.pageSize = pageSize;
    }

    @Override
    public void renderBlock(Component component) {
        logger.info(component + " started rendering");
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
        else if(component.getClass().equals(PLLineChartBlock.class)){
            this.renderLineChartBlock((PLLineChartBlock) component);
        }else{
            logger.warn("Unhandled type");
        }
        logger.info(component + " finished rendering");
    }

    @Override
    public void renderDefaultBlock(Component component) {

    }

    @Override
    public void renderImageBlock(PLImageBlock imageBlock) {

    }

    @Override
    public void renderLineChartBlock(PLLineChartBlock lineChartBlock) {

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
        float x = Float.valueOf(textBlock.getX());
        float y = Float.valueOf(textBlock.getY());
        float textHeight = 0f;

        for(String line: lines){
            float lineHeight = textBlock.getFontSize() + textBlock.getLineSpacing();
            if(textHeight + lineHeight > blockHeight){
                this.logger.warn("text is bigger than the Component's Height, ignoring the rest of the text");
                break;
            }
            document.setFixedPosition(x, y, UnitValue.POINT);
            document.add(new Paragraph(line)
                    .setFont(textBlock.getFont()))
                    .setFontSize(textBlock.getFontSize());
            textHeight += lineHeight;
            y += lineHeight;
        }
    }

    @Override
    public ByteArrayOutputStream render() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        document = new Document(pdfDocument, pageSize);
        for(Component component: rootComponent.getChildComponents()){
            this.renderBlock(component);
        }
        document.close();
        return outputStream;
    }
}
