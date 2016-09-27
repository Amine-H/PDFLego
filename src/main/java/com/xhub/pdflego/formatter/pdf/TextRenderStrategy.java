package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.xhub.pdflego.bloc.PLTextBlock;
import com.xhub.pdflego.core.vo.ColorVO;
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
        Color fontColor = ColorVO.create(component.getFontColor(), Color.class);
        Float fontSize = component.getFontSize();

        Paragraph paragraph = new Paragraph();
        paragraph.setMargin(0);
        paragraph.setFirstLineIndent(0);
        paragraph.setFixedLeading(leading);
        paragraph.setFont(component.getFont());
        if(fontColor != null) paragraph.setFontColor(fontColor);
        if(fontSize != null) paragraph.setFontSize(fontSize);
        paragraph.setFontSize(component.getFontSize());
        paragraph.add(text);
        componentCanvas.add(paragraph);
    }
}
