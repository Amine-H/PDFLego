package com.xhub.pdflego.bloc;


import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.PLImage;

/**
 *  PLImageBlock is an Image {@link Component}, it is used to draw an image to a page
 * @author amine
 */
public class PLImageBlock extends Component{
	private PLImage image;
	
	public PLImageBlock(Component parent){
		super(parent);
	}

	public static PLImageBlock create(Component component){
		PLImageBlock imageBlock = new PLImageBlock(component.getParent());
		imageBlock.setHeight(component.getHeight());
		imageBlock.setWidth(component.getWidth());
		imageBlock.setX(component.getX());
		imageBlock.setY(component.getY());
		return imageBlock;
	}

	public PLImage getImage() {
		return image;
	}

	public void setImage(PLImage image) {
		this.image = image;
	}
}
