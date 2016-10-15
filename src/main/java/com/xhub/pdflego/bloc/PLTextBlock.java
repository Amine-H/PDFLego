package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;

/**
 * PLTextBlock is a text {@link Component}, it is used to draw text to a page
 * @author Amine Hakkou
 */
public class PLTextBlock extends Component{
	private String className = "PLTextBlock";
	private String text;
	private Float lineSpacing;

	public PLTextBlock(Component parent){
		super(parent);
		this.setFontSize(14f);
		this.lineSpacing =  0.5f;
	}

	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Float getLineSpacing() {
		return lineSpacing;
	}

	public void setLineSpacing(Float lineSpacing) {
		this.lineSpacing = lineSpacing;
	}
}
