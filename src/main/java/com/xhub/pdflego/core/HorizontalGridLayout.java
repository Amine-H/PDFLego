package com.xhub.pdflego.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amine
 */
public class HorizontalGridLayout extends Composite{
    private List<Float> componentsSize = new ArrayList<>();

    public HorizontalGridLayout(Component parent){
        super(parent);
        if(parent != null){
            this.setHeight(parent.getHeight());
            this.setWidth(parent.getWidth());
        }
    }

    public void add(Component component, Float percentage){
        componentsSize.add(percentage);
        super.add(component);
    }

    @Override
    protected void postAdd(Component component) {
        component.setHeight(this.getHeight());
        this.calculateDimensions();
    }

    @Override
    public void setWidth(Integer width){
        super.setWidth(width);
        this.calculateDimensions();
    }

    private void calculateDimensions(){
        int components = super.childComponents.size();
        int componentsSize = this.componentsSize.size();
        if(components == componentsSize){
            for(int i = 0;i < components;i++){//calculate width
                Float sizePercent = this.componentsSize.get(i);
                Component component = super.childComponents.get(i);
                component.setWidth(Math.round((sizePercent*this.getWidth())/100));
            }
            for(int i = 0;i < components;i++){// calculate X
                Component component = super.childComponents.get(i);
                Integer offset = super.childComponents.subList(0, i).stream().filter(c -> c != component).map(Component::getWidth).mapToInt(w -> w).sum();
                component.setX(offset);
            }
        }else{
            //redistribute the size
            this.componentsSize = (new ArrayList<>(components)).stream().map(gridSize -> (100f/components)).collect(Collectors.toList());
            this.calculateDimensions();
        }
    }

    @Override
    protected void preRemove(Component component) {
        this.calculateDimensions();
    }
}
