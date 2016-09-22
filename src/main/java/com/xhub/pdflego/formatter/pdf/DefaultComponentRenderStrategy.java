package com.xhub.pdflego.formatter.pdf;

import com.xhub.pdflego.core.Component;
import com.itextpdf.layout.Canvas;

/**
 * Created by amine
 */
public class DefaultComponentRenderStrategy implements ComponenRenderStrategy<Component>{
    @Override
    public void render(Canvas componentCanvas, Component component) {
        //@TODO fix this
        /**
        com.itextpdf.kernel.color.Color backgroundColor = component.getBackgroundColor();
        if(backgroundColor != null){
            componentCanvas
                    .saveState()
                    .setFillColor(backgroundColor)
                    .rectangle(this.currentBlock.getRootArea()).fill()
                    .restoreState();
        }
         **/
    }
}
