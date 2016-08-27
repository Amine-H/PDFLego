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

/**
 * PLTextBlock is a text {@link Component}, it is used to draw text to a page
 * @author Amine Hakkou
 */
public class PLTextBlock extends Component{
	private String text;
	private Integer fontSize = 14;
	private Float lineSpacing = 0.5f;
	private Font font;
	private Logger logger = Logger.getLogger(PLTextBlock.class);

	/**
	 * draws text to a {@link Page}
	 * @param page (required) page where the text will be drawn on
	 * @throws IOException
	 */
	private void drawText(Page page){
		String text = new String(this.text);
		Integer blockWidth = this.getWidth();
		Integer blockHeight = this.getHeight();
		List<String> lines = new ArrayList<String>();
		String[] words = text.split(" ");
	    String myLine = "";

	    // get all words from the text
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
		Float y = Float.valueOf(this.getY());
		Float textHeight = 0f;
		PrimitiveComposer composer = new PrimitiveComposer(page);
		composer.setFont(this.font, this.fontSize);
		for(String line: lines){
			Float lineHeight = fontSize + this.lineSpacing;
			if(textHeight + lineHeight > this.getHeight()){
				this.logger.warn("text is bigger than the Component's Height, ignoring the rest of the text");
				break;
			}
			composer.showText(line, new Point2D.Double(x, y));
			textHeight += lineHeight;
			y += lineHeight;
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
		this.logger.info("Text rendered");
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

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Float getLineSpacing() {
		return lineSpacing;
	}

	public void setLineSpacing(Float lineSpacing) {
		this.lineSpacing = lineSpacing;
	}
}
