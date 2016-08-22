package com.xhub.pdflego.core.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import com.xhub.pdflego.core.Component;

public class PLTextBlock extends Component implements PLBlock{
	private String text;
	private Integer fontSize;
	private Float leading;
	private PDDocument document;
	private PDPage page;
	private PDFont font;
	
	/**
	 * will draw text to a PDPageContentStream
	 * inspired from http://stackoverflow.com/questions/19635275/how-to-generate-multiple-lines-in-pdf-using-apache-pdfbox
	 * @param contentStream
	 * @throws IOException
	 */
	private void drawText(PDPageContentStream contentStream) throws IOException{
		String text = new String(this.text);
		Integer parentWidth = this.getParent().getWidth();
		List<String> lines = new ArrayList<String>();
		String[] words = text.split(" ");
	    String myLine = "";

	    // get all words from the text
	    // keep in mind that words are separated by spaces -> "Lorem ipsum!!!!:)" -> words are "Lorem" and "ipsum!!!!:)"
	    for(String word : words) {
	        if(!myLine.isEmpty()) {
	            myLine += " ";
	        }
	        // test the width of the current line + the current word
	        int size = (int) (fontSize * font.getStringWidth(myLine + word) / 1000);
	        if(size > parentWidth) {
	            // if the line would be too long with the current word, add the line without the current word
	            lines.add(myLine);
	            // and start a new line with the current word
	            myLine = word;
	        } else {
	            // if the current line + the current word would fit, add the current word to the line
	            myLine += word;
	        }
	    }
	    // add the rest to lines
	    lines.add(myLine);
		//draw the list of lines
		for(String line: lines){
			contentStream.showText(line);
			contentStream.newLine();
		}
	}

	@Override
	protected void beforeRender() {}

	@Override
	public void render() {
		beforeRender();
		try {
			//
			PDPageContentStream contentStream = new PDPageContentStream(this.document, this.page);
			contentStream.beginText();
			contentStream.setFont(this.font, this.fontSize);
			contentStream.setLeading(leading);
			//draw the text
			drawText(contentStream);
			contentStream.endText();
			contentStream.close();
		} catch (IOException e) {
			Logger.getLogger(PLTextBlock.class).error("exeption on render", e);
		}
		afterRender();
	}

	@Override
	protected void afterRender() {}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Integer getFontSize() {
		return fontSize;
	}

	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	public Float getLeading() {
		return leading;
	}

	public void setLeading(Float leading) {
		this.leading = leading;
	}
}
