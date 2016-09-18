package com.xhub.pdflego.bloc;
import java.io.File;
import java.net.MalformedURLException;

import com.xhub.pdflego.core.Component;
import org.apache.log4j.Logger;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

/**
 *  PLImageBlock is an Image {@link Component}, it is used to draw an image to a page
 * @author amine
 */
public class PLImageBlock extends Component{
	private ImageData image;
	private Logger logger = Logger.getLogger(PLImageBlock.class);
	
	public PLImageBlock(Component parent){
		this.parent = parent;
	}

	public ImageData getImage(){
		return image;
	}

	public void setImage(ImageData image){
		this.image = image;
	}

	public void setImage(String filePath) throws MalformedURLException{
		this.image = ImageDataFactory.create(filePath);
	}
}
