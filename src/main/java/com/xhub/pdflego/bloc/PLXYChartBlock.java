package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.ColorVO;
import de.erichseifert.gral.data.DataSeries;

public class PLXYChartBlock extends Component {
	private String title;
	private DataSeries[] data;
	private ColorVO[] seriesColor;
	private ColorVO  plotBackgroundColor;
	private ColorVO titleColor;
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

	public ColorVO[] getSeriesColor() {
		return seriesColor;
	}

	public void setSeriesColor(ColorVO... seriesColor) {
		this.seriesColor = seriesColor;
	}

	public ColorVO getPlotBackgroundColor() {
		return plotBackgroundColor;
	}

	public void setPlotBackgroundColor(ColorVO plotBackgroundColor) {
		this.plotBackgroundColor = plotBackgroundColor;
	}

	public ColorVO getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(ColorVO titleColor) {
		this.titleColor = titleColor;
	}

	public boolean isLegendVisible() {
		return legendVisible;
	}

	public void setLegendVisible(boolean legendVisible) {
		this.legendVisible = legendVisible;
	}
}
