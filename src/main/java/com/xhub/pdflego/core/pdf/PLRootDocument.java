package com.xhub.pdflego.core.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.xhub.pdflego.core.Composite;

public abstract class PLRootDocument extends Composite implements PLBlock{
	private PDDocument document = new PDDocument();
	private PDPage page = new PDPage();
	private PDFont font = PDType1Font.TIMES_ROMAN;

	public PLRootDocument() {
		document.addPage(page);
	}

	@Override
	public PDDocument getDocument() {
		return document;
	}

	@Override
	public void setDocument(PDDocument document) {
		this.document = document;
	}

	@Override
	public PDPage getPage() {
		return page;
	}

	@Override
	public void setPage(PDPage page) {
		this.page = page;
	}

	@Override
	public PDFont getFont() {
		return font;
	}

	@Override
	public void setFont(PDFont font) {
		this.font = font;
	}
}
