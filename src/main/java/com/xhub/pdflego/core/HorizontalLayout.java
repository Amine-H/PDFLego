package com.xhub.pdflego.core;

import java.util.List;

/**
 * Created by amine
 */
public class HorizontalLayout extends Composite{

    public HorizontalLayout(Component parent){
        super(parent);
        if(parent != null) this.setWidth(parent.getWidth());
    }

    @Override
    public void postAdd(Component component) {
        this.calculateDimensions();
    }

    @Override
    public void setWidth(Integer width){
        super.setWidth(width);
        this.calculateDimensions();
    }

    private void calculateDimensions(){
        Integer width = this.getWidth();
        List<Component> childComponents = this.getChildComponents();
        Integer count = (childComponents != null)? childComponents.size() : 0;
        if(count > 0){
            Integer componentWidth = width / count;
            for(Integer i = 0;i < count;i++){
                Component component = childComponents.get(i);
                component.setWidth(componentWidth);
                component.setX(i * componentWidth);
            }
        }
    }

    @Override
    public void preRemove(Component component) {
        this.calculateDimensions();
    }
}
