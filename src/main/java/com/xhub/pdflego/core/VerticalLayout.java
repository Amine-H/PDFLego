package com.xhub.pdflego.core;

import java.util.List;

/**
 * Created by amine
 */
public class VerticalLayout extends Composite{
    private String className = "VerticalLayout";

    public VerticalLayout(Component parent){
        super(parent);
        if(parent != null){
            this.setHeight(parent.getHeight());
            this.setWidth(parent.getWidth());
        }
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    protected void postAdd(Component component) {
        component.setWidth(this.getWidth());
        this.calculateDimensions();
    }

    @Override
    public void setHeight(Integer height){
        super.setHeight(height);
        this.calculateDimensions();
    }

    private void calculateDimensions(){
        Integer height = this.getHeight();
        List<Component> childComponents = this.getChildren();
        Integer count = (childComponents != null)? childComponents.size() : 0;
        if(count > 0){
            Integer componentHeight = height / count;
            for(Integer i = 0;i < count;i++){
                Component component = childComponents.get(i);
                component.setHeight(componentHeight);
                component.setY(i * componentHeight);
            }
        }
    }

    @Override
    public void validate() {
        this.calculateDimensions();
        for(Component child:children){
            child.validate();
        }
    }

    @Override
    protected void preRemove(Component component) {
        this.calculateDimensions();
    }
}
