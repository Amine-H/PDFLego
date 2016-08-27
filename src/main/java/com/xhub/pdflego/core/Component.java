package com.xhub.pdflego.core;
import org.apache.log4j.Logger;
import org.pdfclown.documents.Page;

/**
 * Component has the attributes of a rectangle
 * @author Amine Hakkou
 */
public abstract class Component {
	protected Integer x = 0;
	protected Integer y = 0;
	protected Integer width;
	protected Integer height;
	protected Component parent;
	private Boolean inheritFromParent = false;
	private Logger logger = Logger.getLogger(Component.class);

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
				this.logger.error(e.getMessage(), e);
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
			ComponentOverflow e = new ComponentOverflow();
			this.logger.error(e.getMessage(), e);
			throw e;
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

	public Boolean getInheritFromParent() {
		return inheritFromParent;
	}

	public void setInheritFromParent(Boolean inheritFromParent) {
		this.inheritFromParent = inheritFromParent;
	}
}
