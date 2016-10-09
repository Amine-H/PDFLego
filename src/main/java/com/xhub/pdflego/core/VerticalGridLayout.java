package com.xhub.pdflego.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amine
 */
public class VerticalGridLayout extends Composite{
    private List<Float> componentsSize = new ArrayList<>();

    public VerticalGridLayout(Component parent){
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
    public void postAdd(Component component) {
        component.setWidth(this.getWidth());
        this.calculateDimensions();
    }

    @Override
    public void setHeight(Integer height){
        super.setHeight(height);
        this.calculateDimensions();
    }

    private void calculateDimensions(){
        int components = super.childComponents.size();
        int componentsSize = this.componentsSize.size();
        if(components == componentsSize){
            for(int i = 0;i < components;i++){
                Float sizePercent = this.componentsSize.get(i);
                Component component = super.childComponents.get(i);
                component.setHeight(Math.round((sizePercent*this.getHeight())/100));
                Integer offset = super.childComponents.subList(i, components).stream().map(Component::getHeight).mapToInt(h -> h).sum();
                component.setY(offset);
            }
        }else{
            //redistribute the size
            this.componentsSize = (new ArrayList<>(components)).stream().map(gridSize -> (100f/components)).collect(Collectors.toList());
            this.calculateDimensions();
        }
    }

    @Override
    public void preRemove(Component component) {
        this.calculateDimensions();
    }
}
