package com.xhub.pdflego.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite extends Component{
	private List<Component> childComponents = new ArrayList<>();
	
	@Override
	public void render(){
		beforeRender();
		for(Component component: childComponents){
			component.render();
		}
		afterRender();
	}
	
	public void add(Component component){
		childComponents.add(component);
	}
	
	public void remove(Component component){
		childComponents.remove(component);
	}
}
