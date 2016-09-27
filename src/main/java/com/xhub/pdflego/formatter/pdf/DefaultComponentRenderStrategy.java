package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.layout.element.Div;
import com.xhub.pdflego.bloc.PLImageBlock;
import com.xhub.pdflego.core.Component;
import com.itextpdf.layout.Canvas;
import com.itextpdf.kernel.color.Color;
import com.xhub.pdflego.core.vo.ColorVO;

/**
 * Created by amine
 */
public class DefaultComponentRenderStrategy implements ComponenRenderStrategy<Component>{
    @Override
    public void render(Canvas componentCanvas, Component component) {
        Color backgroundColor = ColorVO.create(component.getBackgroundColor(), Color.class);
        ImageData backgroundImage = component.getBackgroundImage();
        if(backgroundImage != null){
            PLImageBlock image = PLImageBlock.create(component);
            image.setImage(backgroundImage);
            ImageRenderStrategy imageRenderer = new ImageRenderStrategy();
            imageRenderer.render(componentCanvas, image);
        }
        else if(backgroundColor != null){
            Div div = new Div();
            div.setWidth(component.getWidth());
            div.setHeight(component.getHeight());
            div.setBackgroundColor(backgroundColor);
            componentCanvas.add(div);
        }
    }
}
