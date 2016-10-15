package com.xhub.pdflego.core;

import com.xhub.pdflego.core.vo.PLClass;
import com.xhub.pdflego.core.vo.PLColor;
import com.xhub.pdflego.core.vo.PLFont;
import com.xhub.pdflego.core.vo.PLImage;
import org.apache.log4j.Logger;

/**
 * Component has the attributes of a rectangle
 * @author Amine Hakkou
 */
public abstract class Component implements PLClass{
	private Integer x = 0;
	private Integer y = 0;
	private Integer width;
	private Integer height;
	private Component parent;
	private PLColor backgroundColor;
	private PLImage backgroundImage;
	private PLFont font;
	private Float fontSize;
	private PLColor fontColor;
	private Logger logger = Logger.getLogger(Component.class);

	public Component(Component parent){
		this.parent = parent;
	}

	public Integer getX() {
		if(this.parent != null){
			return this.parent.getX() + this.x;
		}else{
			return this.x;
		}
	}

	public void setX(Integer x) {
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

	public PLColor getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(PLColor backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public PLImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(PLImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public Float getFontSize() {
		return fontSize;
	}

	public void setFontSize(Float fontSize) {
		this.fontSize = fontSize;
	}

	public PLColor getFontColor() {
		return fontColor;
	}

	public void setFontColor(PLColor fontColor) {
		this.fontColor = fontColor;
	}

	public PLFont getFont() {
		return font;
	}

	public void setFont(PLFont font) {
		this.font = font;
	}
}
