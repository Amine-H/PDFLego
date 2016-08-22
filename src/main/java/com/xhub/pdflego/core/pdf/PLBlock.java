package com.xhub.pdflego.core.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;

public interface PLBlock {
	public PDDocument getDocument();
	public void setDocument(PDDocument document);
	public PDPage getPage();
	public void setPage(PDPage page);
	public PDFont getFont();
	public void setFont(PDFont font);
}
