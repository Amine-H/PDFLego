package com.xhub.pdflego.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amine
 */
public class VerticalGridLayout extends Composite{
    private String className = "VerticalGridLayout";
    private List<Float> componentsSize = new ArrayList<>();

    public VerticalGridLayout(Component parent){
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

    public void add(Component component, Float percentage){
        componentsSize.add(percentage);
        super.add(component);
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
        int components = super.childComponents.size();
        int componentsSize = this.componentsSize.size();
        if(components == componentsSize){
            for(int i = 0;i < components;i++){//calculate height
                Float sizePercent = this.componentsSize.get(i);
                Component component = super.childComponents.get(i);
                component.setHeight(Math.round((sizePercent*this.getHeight())/100));
            }
            for(int i = 0;i < components;i++){//calculate Y
                Component component = super.childComponents.get(i);
                Integer offset = super.childComponents.subList(0, i).stream().filter(c -> c != component).map(Component::getHeight).mapToInt(h -> h).sum();
                component.setY(offset);
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
