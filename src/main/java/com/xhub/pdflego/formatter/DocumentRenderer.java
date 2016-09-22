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
        this.renderBlock((Component)compositeBlock);
        if(compositeBlock != null && compositeBlock.getChildComponents() != null){
            logger.info("rendering child components of " + compositeBlock);
            compositeBlock.getChildComponents().forEach(component -> {
                if(component instanceof Composite){
                    this.renderBlock((Composite)component);
                }else{
                    this.renderBlock(component);
                }
            });
        }
    }
    public abstract void renderBlock(Component component);
}
