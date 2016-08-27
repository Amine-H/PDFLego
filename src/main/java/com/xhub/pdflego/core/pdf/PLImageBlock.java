package com.xhub.pdflego.core.pdf;
import java.awt.geom.Point2D;
import java.io.File;
import org.apache.log4j.Logger;
import org.pdfclown.documents.Page;
import org.pdfclown.documents.contents.composition.PrimitiveComposer;
import org.pdfclown.documents.contents.entities.Image;
import org.pdfclown.documents.contents.xObjects.XObject;

import com.xhub.pdflego.core.Component;

/**
 *  PLImageBlock is an Image {@link Component}, it is used to draw an image to a page
 * @author amine
 */
public class PLImageBlock extends Component{
	private Image image;
	private Logger logger = Logger.getLogger(PLImageBlock.class);
	
	public PLImageBlock(Component parent){
		this.parent = parent;
	}

	@Override
	protected void render(Page page) {
		if(this.image != null){
			if(this.image.getHeight() <= this.getHeight()
				|| this.image.getWidth() <= this.getWidth()){
				//getting coordinates
				Float x = Float.valueOf(this.getX());
				Float y = Float.valueOf(this.getY());
				//getting a composer for the page
				PrimitiveComposer composer = new PrimitiveComposer(page);
				//making an XObject out of the image
				XObject imageXObject =  this.getImage().toXObject(page.getDocument());
				//drawing the image
				composer.showXObject(imageXObject, new Point2D.Float(x, y));
				composer.flush();
				logger.info("image block " + this + " was rendered into the page " + page);
			}else{
				logger.warn("the content of the image block " + this + " is too big to render on the page " + page);
			}
		}else{
			logger.warn("image is not set, image block " + this + " was not renderded into page " + page);
		}
	}

	@Override
	protected void beforeRender(Page page) {}

	@Override
	protected void afterRender(Page page) {}

	public Image getImage(){
		return image;
	}

	public void setImage(Image image){
		this.image = image;
	}
	
	public void setImage(String filePath){
		this.image = Image.get(filePath);
	}
	
	public void setImage(File file){
		this.image = Image.get(file);
	}
}
