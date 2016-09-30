package com.xhub.pdflego.core;

import java.util.List;

/**
 * Created by amine
 */
public class VerticalLayout extends Composite{

    public VerticalLayout(Component parent){
        super(parent);
        if(parent != null) this.setHeight(parent.getHeight());
    }

    @Override
    public void postAdd(Component component) {
        this.calculateDimensions();
    }

    @Override
    public void setHeight(Integer height){
        super.setHeight(height);
        this.calculateDimensions();
    }

    private void calculateDimensions(){
        Integer height = this.getHeight();
        List<Component> childComponents = this.getChildComponents();
        Integer count = (childComponents != null)? childComponents.size() : 0;
        if(count > 0){
            Integer componentHeight = height / count;
            for(Integer i = 0;i < count;i++){
                Component component = childComponents.get(i);
                component.setY(i * componentHeight);
                component.setHeight(componentHeight);
            }
        }
    }

    @Override
    public void preRemove(Component component) {
        this.calculateDimensions();
    }
}
