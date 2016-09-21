package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import de.erichseifert.gral.data.DataSeries;

import java.awt.*;

public class PLXYChartBlock extends Component {
	private String title;
	private DataSeries[] data;
	private Color[] seriesColor;
	private Color backgroundColor = Color.WHITE;
	private Color titleColor = Color.BLACK;
	private boolean legendVisible = true;

	public PLXYChartBlock(Component parent){
		super(parent);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DataSeries[] getData() {
		return data;
	}

	public void setData(DataSeries... data) {
		this.data = data;
	}

	public Color[] getSeriesColor() {
		return seriesColor;
	}

	public void setSeriesColor(Color... seriesColor) {
		this.seriesColor = seriesColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(Color titleColor) {
		this.titleColor = titleColor;
	}

	public boolean isLegendVisible() {
		return legendVisible;
	}

	public void setLegendVisible(boolean legendVisible) {
		this.legendVisible = legendVisible;
	}
}
