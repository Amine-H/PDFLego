package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.xhub.pdflego.bloc.PLImageBlock;
import com.xhub.pdflego.core.vo.PLImage;
import org.apache.log4j.Logger;

/**
 * Created by amine
 */
public class ImageRenderStrategy implements ComponenRenderStrategy<PLImageBlock>{
    private Logger logger = Logger.getLogger(ImageRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLImageBlock component) {
        ImageData imageData = PLImage.create(component.getImage(), ImageData.class);
        if(imageData != null){
            Image image = new Image(imageData);
            componentCanvas.add(image);
        }else{
            this.logger.warn(component + " doesn't have any data, object not drawn");
        }
    }
}
