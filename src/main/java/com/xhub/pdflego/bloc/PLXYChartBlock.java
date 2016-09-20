package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import de.erichseifert.gral.data.DataTable;
import org.apache.log4j.Logger;

public class PLXYChartBlock extends Component {
	private String chartName;
	private String xLabel;
	private String yLabel;
	private DataTable[] data;
	private Logger logger = Logger.getLogger(PLXYChartBlock.class);

	public PLXYChartBlock(Component parent){
		super(parent);
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public String getxLabel() {
		return xLabel;
	}

	public void setxLabel(String xLabel) {
		this.xLabel = xLabel;
	}

	public String getyLabel() {
		return yLabel;
	}

	public void setyLabel(String yLabel) {
		this.yLabel = yLabel;
	}

	public DataTable[] getData() {
		return data;
	}

	public void setData(DataTable... data) {
		this.data = data;
	}
}
