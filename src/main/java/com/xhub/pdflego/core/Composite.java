package com.xhub.pdflego.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public abstract class Composite extends Component{
	private List<Component> childComponents = new ArrayList<>();
	
	@Override
	public void render(PDDocument document, PDPage page){
		beforeRender(document, page);
		for(Component component: childComponents){
			component.render(document, page);
		}
		afterRender(document, page);
	}
	
	public void add(Component component){
		childComponents.add(component);
	}
	
	public void remove(Component component){
		childComponents.remove(component);
	}
}
