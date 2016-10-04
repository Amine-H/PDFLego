package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.PLColor;
import de.erichseifert.gral.data.DataSeries;

public class PLXYChartBlock extends AbstractPlotBlock {
	private DataSeries[] data;
	private PLColor[] seriesColor;

	public PLXYChartBlock(Component parent){
		super(parent);
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
}
