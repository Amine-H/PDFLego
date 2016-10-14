package com.xhub.pdflego.bloc;

import com.xhub.pdflego.bloc.data.lineplot.PLLinePlotData;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.PLColor;
import de.erichseifert.gral.data.DataSeries;

public class PLLineChartBlock extends AbstractPlotBlock {
	private PLLinePlotData data;
	private PLColor[] seriesColor;

	public PLLineChartBlock(Component parent){
		super(parent);
	}

	public PLLinePlotData getData() {
		return data;
	}

	public void setData(PLLinePlotData data) {
		this.data = data;
	}

	public PLColor[] getSeriesColor() {
		return seriesColor;
	}

	public void setSeriesColor(PLColor... seriesColor) {
		this.seriesColor = seriesColor;
	}
}
