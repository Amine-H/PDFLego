package com.xhub.pdflego.core;

import com.itextpdf.io.image.ImageData;
import com.xhub.pdflego.core.vo.ColorVO;
import com.xhub.pdflego.exception.ComponentOverflowException;
import org.apache.log4j.Logger;

/**
 * Component has the attributes of a rectangle
 * @author Amine Hakkou
 */
public abstract class Component {
	private Integer x = 0;
	private Integer y = 0;
	private Integer width;
	private Integer height;
	private Component parent;
	private ColorVO backgroundColor;
	private ImageData backgroundImage;
	private Float fontSize;
	private ColorVO fontColor;
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
		if((this.parent != null) && (x + this.width > parent.getX() + parent.getWidth())){
			ComponentOverflowException e = new ComponentOverflowException();
			this.logger.error(e.getMessage(), e);
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
			ComponentOverflowException e = new ComponentOverflowException();
			this.logger.error(e.getMessage(), e);
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

	public ColorVO getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(ColorVO backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public ImageData getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(ImageData backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public Float getFontSize() {
		return fontSize;
	}

	public void setFontSize(Float fontSize) {
		this.fontSize = fontSize;
	}

	public ColorVO getFontColor() {
		return fontColor;
	}

	public void setFontColor(ColorVO fontColor) {
		this.fontColor = fontColor;
	}
}
