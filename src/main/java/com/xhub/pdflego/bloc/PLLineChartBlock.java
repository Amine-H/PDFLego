package com.xhub.pdflego.bloc;

import com.xhub.pdflego.bloc.data.lineplot.PLLinePlotData;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.PLColor;
import de.erichseifert.gral.data.DataSeries;

public class PLLineChartBlock extends AbstractPlotBlock {
	private String className = "PLLineChartBlock";
	private PLLinePlotData data;

	public PLLineChartBlock(){
		this(null);
	}

	public PLLineChartBlock(Component parent){
		super(parent);
	}

	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	public PLLinePlotData getData() {
		return data;
	}

	public void setData(PLLinePlotData data) {
		this.data = data;
	}
}
