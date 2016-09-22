package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.xhub.pdflego.bloc.PLTextBlock;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amine
 */
public class TextRenderStrategy implements ComponenRenderStrategy<PLTextBlock>{
    private Logger logger = Logger.getLogger(TextRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLTextBlock component) {
        String text = new String(component.getText());
        Integer blockWidth = component.getWidth();
        Integer blockHeight = component.getHeight();
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        String myLine = "";

        // get all words from the text
        for(String word : words) {
            if(!myLine.isEmpty()) {
                myLine += " ";
            }
            // test the width of the current line + the current word
            int textWidth = (int) (component.getFontSize() * component.getFont().getWidth(myLine + word) / 1000);
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
        float lineHeight = component.getFontSize() + component.getLineSpacing();

        for(String line: lines){
            if(textHeight + lineHeight > blockHeight){
                this.logger.warn("text is bigger than the Component's Height, ignoring the rest of the text");
                break;
            }
            Paragraph paragraph = new Paragraph(line)
                    .setFixedLeading(component.getLineSpacing())
                    .setFont(component.getFont())
                    .setFontSize(component.getFontSize());
            componentCanvas.add(paragraph);
            textHeight += lineHeight;
        }
    }
}
