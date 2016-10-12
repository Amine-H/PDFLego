package com.xhub.pdflego.core;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Composite is a {@link Component} that can have child components
 * @author Amine Hakkou
 */
public abstract class Composite extends Component{
	protected List<Component> childComponents = new ArrayList<>();
	private Logger logger = Logger.getLogger(Composite.class);

	public Composite(){
		this(null);
	}

	public Composite(Component parent){
		super(parent);
	}

	public void add(Component component){
		if(childComponents.add(component)){
			component.setParent(this);
			this.logger.info(this + " added " + component + " as child component");
			postAdd(component);
		}
	}

	public List<Component> getChildComponents(){
		return this.childComponents;
	}
	
	protected abstract void postAdd(Component component);
	protected abstract void preRemove(Component component);

	public void remove(Component component){
		preRemove(component);
		if(childComponents.remove(component)){
			this.logger.info(this + " removed " + component + " from its child component");
		}
	}
}
