package com.xhub.pdflego.core.pdf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.xhub.pdflego.core.Composite;

public abstract class PLRootDocument extends Composite{
	private PDDocument document;
	private PDPage page;
	private PDFont font;

	public PLRootDocument() {
		this(PDRectangle.A4);
	}
	
	public PLRootDocument(PDRectangle format){
		document = new PDDocument();
		page = new PDPage(format);
		document.addPage(page);
		font = PDType1Font.TIMES_ROMAN;
	}
	
	public void render(){
		this.render(document, page);
	}

	public PDDocument getDocument() {
		return document;
	}

	public void setDocument(PDDocument document) {
		this.document = document;
	}

	public PDPage getPage() {
		return page;
	}

	public void setPage(PDPage page) {
		this.page = page;
	}

	public PDFont getFont() {
		return font;
	}

	public void setFont(PDFont font) {
		this.font = font;
	}
	
	@Override
	public Integer getHeight(){
		Float mediaBoxHeight = page.getMediaBox().getHeight();
		return this.height = Integer.valueOf(mediaBoxHeight.intValue());
	}
	
	@Override
	public Integer getWidth(){
		Float mediaBoxWidth = page.getMediaBox().getWidth();
		return this.width = Integer.valueOf(mediaBoxWidth.intValue());
	}
}
