package com.xhub.pdflego.core;

import java.util.ArrayList;
import java.util.List;
import org.pdfclown.documents.Page;

public abstract class Composite extends Component{
	private List<Component> childComponents = new ArrayList<>();

	@Override
	protected void render(Page page){
		beforeRender(page);
		for(Component component: childComponents){
			component.render(page);
		}
		afterRender(page);
	}

	public void add(Component component){
		childComponents.add(component);
	}

	public void remove(Component component){
		childComponents.remove(component);
	}
}
