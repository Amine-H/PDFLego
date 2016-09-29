package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.PLColor;
import de.erichseifert.gral.data.DataSeries;

public class PLXYChartBlock extends Component {
	private String title;
	private DataSeries[] data;
	private PLColor[] seriesColor;
	private PLColor plotBackgroundColor;
	private PLColor titleColor;
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

	public PLColor[] getSeriesColor() {
		return seriesColor;
	}

	public void setSeriesColor(PLColor... seriesColor) {
		this.seriesColor = seriesColor;
	}

	public PLColor getPlotBackgroundColor() {
		return plotBackgroundColor;
	}

	public void setPlotBackgroundColor(PLColor plotBackgroundColor) {
		this.plotBackgroundColor = plotBackgroundColor;
	}

	public PLColor getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(PLColor titleColor) {
		this.titleColor = titleColor;
	}

	public boolean isLegendVisible() {
		return legendVisible;
	}

	public void setLegendVisible(boolean legendVisible) {
		this.legendVisible = legendVisible;
	}
}
