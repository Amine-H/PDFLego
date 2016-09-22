package com.xhub.pdflego.formatter;

import com.xhub.pdflego.bloc.*;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.Composite;
import org.apache.log4j.Logger;

/**
 * Created by amine
 */
public abstract class DocumentRenderer<T> {
    private Logger logger = Logger.getLogger(DocumentRenderer.class);
    protected Composite rootComponent;
    public DocumentRenderer(Composite rootDocument){
        this.rootComponent = rootDocument;
    }
    public abstract T render();
    public void renderBlock(Composite compositeBlock){
        if(compositeBlock != null && compositeBlock.getChildComponents() != null){
            compositeBlock.getChildComponents().forEach(component -> this.renderBlock(component));
        }
    }
    public abstract void renderBlock(Component component);
}
