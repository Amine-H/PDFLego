package com.xhub.pdflego.bloc;
import java.awt.geom.Point2D;

import com.xhub.pdflego.core.Component;
import org.apache.log4j.Logger;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYChart;

public class PLLineChartBlock extends Component {
	private String chartName;
	private String seriesName;
	private String xLabel;
	private String yLabel;
	private double[] xData;
	private double[] yData;
	private Logger logger = Logger.getLogger(PLLineChartBlock.class);

	public PLLineChartBlock(Component parent){
		this.parent = parent;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
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

	public double[] getxData() {
		return xData;
	}

	public void setxData(double[] xData) {
		this.xData = xData;
	}

	public double[] getyData() {
		return yData;
	}

	public void setyData(double[] yData) {
		this.yData = yData;
	}
}
