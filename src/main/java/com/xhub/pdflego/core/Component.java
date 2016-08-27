package com.xhub.pdflego.core;
import org.apache.log4j.Logger;
import org.pdfclown.documents.Page;

public abstract class Component {
	protected Integer x;
	protected Integer y;
	protected Integer width;
	protected Integer height;
	protected Component parent;

	protected abstract void beforeRender(Page page);

	protected abstract void render(Page page);

	protected abstract void afterRender(Page page);

	public Integer getX() {
		if(this.parent != null){
			return this.parent.getX() + this.x;
		}else{
			return this.x;
		}
	}

	public void setX(Integer x) {
		if((this.parent != null) &&
				(x + this.width > parent.getX() + parent.getWidth())){
				ComponentOverflow e = new ComponentOverflow();
				Logger.getLogger(Component.class).error(e.getMessage(), e);
				throw e;
			}
		this.x = x;
	}

	public Integer getY() {
		if(this.parent != null){
			return this.parent.getY() + this.y;
		}else{
			return this.y;
		}
	}

	public void setY(Integer y) {
		if((this.parent != null) &&
			(y + this.height > parent.getY() + parent.getHeight())){
			throw new ComponentOverflow();
		}
		this.y = y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Component getParent() {
		return parent;
	}

	public void setParent(Component parent) {
		this.parent = parent;
	}
}
