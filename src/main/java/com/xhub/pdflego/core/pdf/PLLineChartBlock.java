package com.xhub.pdflego.core.pdf;
import java.awt.geom.Point2D;

import org.apache.log4j.Logger;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYChart;
import org.pdfclown.bytes.Buffer;
import org.pdfclown.bytes.IInputStream;
import org.pdfclown.documents.Page;
import org.pdfclown.documents.contents.composition.PrimitiveComposer;
import org.pdfclown.documents.contents.entities.Image;
import org.pdfclown.documents.contents.xObjects.XObject;

import com.xhub.pdflego.core.Component;

public class PLLineChartBlock extends Component{
	private String chartName;
	private String seriesName;
	private String xLabel;
	private String yLabel;
	private double[] xData;
	private double[] yData;
	private Logger logger = Logger.getLogger(PLLineChartBlock.class);

	@Override
	protected void beforeRender(Page page) {}

	@Override
	protected void afterRender(Page page) {}

	@Override
	protected void render(Page page) {
		PrimitiveComposer composer = new PrimitiveComposer(page);
		XYChart chart = new XYChart(this.getWidth(), this.getHeight());
		chart.setTitle(this.chartName);
		chart.setXAxisTitle(this.xLabel);
		chart.setYAxisTitle(this.yLabel);
		chart.addSeries(this.seriesName, this.xData, this.yData);
		try {
			//get the data from the chart
			byte[] chartData = BitmapEncoder.getBitmapBytes(chart, BitmapFormat.JPG);
			//create a stream and write the data into the stream
			IInputStream stream = new Buffer(chartData);
			//get the image from the stream
			Image image = Image.get(stream);
			//make xobject out of the image
			XObject imageXObject =  image.toXObject(page.getDocument());
			//getting coordinates
			Float x = Float.valueOf(this.getX());
			Float y = Float.valueOf(this.getY());
			//draw the xobject into the pdf
			composer.showXObject(imageXObject, new Point2D.Float(x, y));
			composer.flush();
			logger.info("line chart block " + this + " was rendered into the page " + page);
			//close the stream
			stream.close();
		} catch (Exception e) {
			logger.error("could not draw chart " + this + " exception occured", e);
		}
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
