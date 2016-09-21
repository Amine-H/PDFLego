package com.xhub.pdflego.bloc;
import java.net.MalformedURLException;
import com.xhub.pdflego.core.Component;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

/**
 *  PLImageBlock is an Image {@link Component}, it is used to draw an image to a page
 * @author amine
 */
public class PLImageBlock extends Component{
	private ImageData image;
	
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

	public ImageData getImage(){
		return image;
	}

	public void setImage(byte[] data){
		this.image = ImageDataFactory.create(data);
	}

	public void setImage(ImageData image){
		this.image = image;
	}

	public void setImage(String filePath) throws MalformedURLException{
		this.image = ImageDataFactory.create(filePath);
	}
}
