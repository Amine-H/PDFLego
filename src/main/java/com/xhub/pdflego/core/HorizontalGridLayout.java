package com.xhub.pdflego.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amine
 */
public class HorizontalGridLayout extends Composite{
    private String className = "HorizontalGridLayout";
    private List<Float> childrenSize = new ArrayList<>();

    public HorizontalGridLayout(Component parent){
        super(parent);
        if(parent != null){
            this.setHeight(parent.getHeight());
            this.setWidth(parent.getWidth());
        }
    }

    public List<Float> getChildrenSize() {
        return childrenSize;
    }

    public void setChildrenSize(List<Float> childrenSize) {
        this.childrenSize = childrenSize;
        this.calculateDimensions();
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
        childrenSize.add(percentage);
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
        int components = super.children.size();
        int componentsSize = this.childrenSize.size();
        if(components == componentsSize){
            for(int i = 0;i < components;i++){//calculate width
                Float sizePercent = this.childrenSize.get(i);
                Component component = super.children.get(i);
                component.setWidth(Math.round((sizePercent*this.getWidth())/100));
                component.setHeight(this.getHeight());
            }
            for(int i = 0;i < components;i++){// calculate X
                Component component = super.children.get(i);
                Integer offset = super.children.subList(0, i).stream().filter(c -> c != component).map(Component::getWidth).mapToInt(w -> w).sum();
                component.setX(offset);
            }
        }else{
            //redistribute the size
            this.childrenSize = (new ArrayList<>(components)).stream().map(gridSize -> (100f/components)).collect(Collectors.toList());
            this.calculateDimensions();
        }
    }

    @Override
    public void validate() {
        this.calculateDimensions();
        for(Component child:this.children){
            child.validate();
        }
    }

    @Override
    protected void preRemove(Component component) {
        this.calculateDimensions();
    }
}
