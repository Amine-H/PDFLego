package com.xhub.pdflego.core;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public abstract class Component {
	private Integer x;
	private Integer y;
	private Integer offsetX;
	private Integer offsetY;
	private Integer width;
	private Integer height;
	private Component parent;
	
	protected abstract void beforeRender(PDDocument document, PDPage page);

	public abstract void render(PDDocument document, PDPage page);

	protected abstract void afterRender(PDDocument document, PDPage page);

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(Integer offsetX) {
		this.offsetX = offsetX;
	}

	public Integer getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(Integer offsetY) {
		this.offsetY = offsetY;
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
