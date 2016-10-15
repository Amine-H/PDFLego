package com.xhub.pdflego.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amine
 */
public class VerticalGridLayout extends Composite{
    private String className = "VerticalGridLayout";
    @JsonProperty("childrenSize")
    private List<Float> childrenSize = new ArrayList<>();

    public VerticalGridLayout(){

    }

    public VerticalGridLayout(Component parent){
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
        component.setWidth(this.getWidth());
        this.calculateDimensions();
    }

    @Override
    public void setHeight(Integer height){
        super.setHeight(height);
        this.calculateDimensions();
    }

    private void calculateDimensions(){
        int components = super.children.size();
        int componentsSize = this.childrenSize.size();
        if(components == componentsSize){
            for(int i = 0;i < components;i++){//calculate height
                Float sizePercent = this.childrenSize.get(i);
                Component component = super.children.get(i);
                component.setHeight(Math.round((sizePercent*this.getHeight())/100));
                component.setWidth(this.getWidth());
            }
            for(int i = 0;i < components;i++){//calculate Y
                Component component = super.children.get(i);
                Integer offset = super.children.subList(0, i).stream().filter(c -> c != component).map(Component::getHeight).mapToInt(h -> h).sum();
                component.setY(offset);
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
        for(Component child:children){
            child.validate();
        }
    }

    @Override
    protected void preRemove(Component component) {
        this.calculateDimensions();
    }
}
