package com.xhub.pdflego.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.pdfclown.documents.Page;

/**
 * Composite is a {@link Component} that can have child components
 * @author Amine Hakkou
 */
public abstract class Composite extends Component{
	private List<Component> childComponents = new ArrayList<>();
	private Logger logger = Logger.getLogger(Composite.class);

	@Override
	protected void render(Page page){
		beforeRender(page);
		for(Component component: childComponents){
			component.render(page);
		}
		afterRender(page);
	}

	public void add(Component component){
		if(childComponents.add(component)){
			this.logger.info(this + " added " + component + " as child component");
		}
	}

	public void remove(Component component){
		if(childComponents.remove(component)){
			this.logger.info(this + " removed " + component + " from its child component");
		}
	}
}
