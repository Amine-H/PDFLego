package com.xhub.pdflego.bloc;
import com.itextpdf.kernel.font.PdfFont;
import com.xhub.pdflego.core.Component;

/**
 * PLTextBlock is a text {@link Component}, it is used to draw text to a page
 * @author Amine Hakkou
 */
public class PLTextBlock extends Component{
	private String text;
	private Integer fontSize = 14;
	private Float lineSpacing = 0.5f;
	private PdfFont font;

	public PLTextBlock(Component parent){
		super(parent);
	}

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

	public PdfFont getFont() {
		return font;
	}

	public void setFont(PdfFont font) {
		this.font = font;
	}

	public Float getLineSpacing() {
		return lineSpacing;
	}

	public void setLineSpacing(Float lineSpacing) {
		this.lineSpacing = lineSpacing;
	}
}
