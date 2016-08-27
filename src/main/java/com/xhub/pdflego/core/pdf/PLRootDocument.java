package com.xhub.pdflego.core.pdf;
import java.awt.geom.Dimension2D;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.pdfclown.documents.Document;
import org.pdfclown.documents.Page;
import org.pdfclown.documents.PageFormat;
import org.pdfclown.documents.contents.fonts.Font;
import org.pdfclown.documents.contents.fonts.StandardType1Font;
import org.pdfclown.files.File;
import org.pdfclown.files.SerializationModeEnum;
import com.xhub.pdflego.core.Composite;

/**
 * PLRootDocument is the root {@link Composite},
 * it will render its sub Components and actually write it to a file
 * @author Amine Hakkou
 */
public abstract class PLRootDocument extends Composite{
	private Document document;
	private Page page;
	private Font font;
	private Logger logger = Logger.getLogger(PLRootDocument.class);

	public PLRootDocument() {
		this(PageFormat.getSize());
	}
	
	public PLRootDocument(Dimension2D size){
		document = new File().getDocument();
		page = new Page(document, size);
		document.getPages().add(page);
		font = new StandardType1Font(document, StandardType1Font.FamilyEnum.Times, false, false);
		x = 0;
		y = 0;
		this.logger.info("PLRootDocument constructed, font=" + font.getName() +
					", page.height=" + page.getSize().getHeight() +
					", page.width=" + page.getSize().getWidth());
	}
	
	public void save(String filePath){
		try {
			this.render();
			document.getFile().save(filePath, SerializationModeEnum.Standard);
			this.logger.info("Document saved to " + filePath);
		} catch (IOException e) {
			this.logger.error("Exception occured while saving to " + filePath, e);
		}
	}
	
	protected void render(){
		this.render(this.page);
		this.logger.info("Document rendered");
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	@Override
	public Integer getX(){
		return this.x;
	}
	
	@Override
	public Integer getY(){
		return this.getHeight() - this.y;
	}
	
	@Override
	public Integer getHeight(){
		Double pageHeight = page.getSize().getHeight();
		return this.height = Integer.valueOf(pageHeight.intValue());
	}
	
	@Override
	public Integer getWidth(){
		Double pageWidth = page.getSize().getWidth();
		return this.width = Integer.valueOf(pageWidth.intValue());
	}

	@Override
	public void setX(Integer x){}
	
	@Override
	public void setY(Integer y){}
	
	@Override
	public void setHeight(Integer height){}
	
	@Override
	public void setWidth(Integer width){}
}
