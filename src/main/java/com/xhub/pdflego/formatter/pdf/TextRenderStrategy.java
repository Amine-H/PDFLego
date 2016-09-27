package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.xhub.pdflego.bloc.PLTextBlock;
import org.apache.log4j.Logger;

/**
 * Created by amine
 */
public class TextRenderStrategy implements ComponenRenderStrategy<PLTextBlock>{
    private Logger logger = Logger.getLogger(TextRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLTextBlock component) {
        String text = component.getText();
        float leading = component.getFontSize() + component.getLineSpacing();

        Paragraph paragraph = new Paragraph()
                .setMargin(0)
                .setFirstLineIndent(0)
                .setFixedLeading(leading)
                .setFont(component.getFont())
                .setFontSize(component.getFontSize())
                .add(text);
        componentCanvas.add(paragraph);
    }
}
