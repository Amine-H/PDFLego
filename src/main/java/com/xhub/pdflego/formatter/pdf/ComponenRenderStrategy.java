package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.xhub.pdflego.core.Component;

/**
 * Created by amine
 */
public interface ComponenRenderStrategy<T extends Component> {
    public void render(Canvas componentCanvas, T component);
}
