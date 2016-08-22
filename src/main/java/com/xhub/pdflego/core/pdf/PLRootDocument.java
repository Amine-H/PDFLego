package com.xhub.pdflego.core.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.xhub.pdflego.core.Composite;

public abstract class PLRootDocument extends Composite{
	private PDDocument document = new PDDocument();
	private PDPage page = new PDPage();
	private PDFont font = PDType1Font.TIMES_ROMAN;

	public PLRootDocument() {
		document.addPage(page);
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
}
