package com.xhub.pdflego.core.pdf;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.pdfclown.documents.Page;
import org.pdfclown.documents.contents.composition.PrimitiveComposer;
import org.pdfclown.documents.contents.fonts.Font;

import com.xhub.pdflego.core.Component;

public class PLTextBlock extends Component{
	private String text;
	private Integer fontSize;
	private Float leading;
	private Font font;

	/**
	 * will draw text to a PDPageContentStream
	 * inspired from http://stackoverflow.com/questions/19635275/how-to-generate-multiple-lines-in-pdf-using-apache-pdfbox
	 * @param contentStream
	 * @throws IOException
	 */
	private void drawText(Page page){
		String text = new String(this.text);
		Integer blockWidth = this.getWidth();
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
	        int textWidth = (int) (fontSize * font.getWidth(myLine + word) / 1000);
	        if(textWidth > blockWidth) {
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
		Float x = Float.valueOf(this.getX());
		Float y = 0f;
		PrimitiveComposer composer = new PrimitiveComposer(page);
		composer.setFont(font, fontSize);
		for(String line: lines){
			composer.showText(line, new Point2D.Double(x, y));
			y += fontSize + 0.5f;
		}
		composer.flush();
	}

	@Override
	protected void beforeRender(Page page) {}

	@Override
	public void render(Page page) {
		beforeRender(page);
		drawText(page);
		afterRender(page);
	}

	@Override
	protected void afterRender(Page page) {}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
}
