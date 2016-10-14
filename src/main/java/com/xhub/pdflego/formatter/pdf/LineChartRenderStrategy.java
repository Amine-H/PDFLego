package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.xhub.pdflego.bloc.PLLineChartBlock;
import com.xhub.pdflego.bloc.data.lineplot.PLLinePlotData;
import com.xhub.pdflego.core.vo.PLColor;
import com.xhub.pdflego.formatter.PlotRenderHelper;
import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import org.apache.log4j.Logger;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by amine
 */
public class LineChartRenderStrategy extends PlotRenderHelper<PLLineChartBlock> implements ComponenRenderStrategy<PLLineChartBlock>{
    private Logger logger = Logger.getLogger(LineChartRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLLineChartBlock component) {
        PLLinePlotData data = component.getData();
        if(data != null){
            int linesCount = data.getY().size();
            Class[] classes = Arrays.stream((new Class[linesCount + 1])).map(v -> Double.class).toArray(Class[]::new);
            DataTable dataTable = new DataTable(classes);
            for(int i = 0;i < linesCount;i++){
                final int index = i;
                Double[] points = Stream.concat(Stream.of(data.getX().get(index)),
                        data.getY().stream().map(lineData -> lineData.getY().get(index))).toArray(Double[]::new);
                dataTable.add(points);
            }
            DataSeries[] dataSeries = IntStream.range(0, linesCount).mapToObj(index -> new DataSeries(data.getY().get(index).getName(), dataTable, 0, index + 1)).toArray(DataSeries[]::new);
            XYPlot plot = new XYPlot(dataSeries);
            super.preparePlot(plot, component);
            plot.getAxisRenderer(XYPlot.AXIS_X).setIntersection(-Double.MAX_VALUE);
            plot.getAxisRenderer(XYPlot.AXIS_Y).setIntersection(-Double.MAX_VALUE);

            Color[] seriesColor = (component.getData().getY() == null)?null:component.getData().getY().stream().map(point -> PLColor.create(point.getColor(), Color.class)).toArray(Color[]::new);
            if(seriesColor != null){
                for(int i = 0;i < seriesColor.length;i++){
                    try{
                        PointRenderer ptRenderer = new DefaultPointRenderer2D();
                        LineRenderer lineRenderer = new DefaultLineRenderer2D();
                        Color lineColor = seriesColor[i];
                        ptRenderer.setColor(lineColor);
                        lineRenderer.setColor(lineColor);
                        plot.setPointRenderers(dataSeries[i], ptRenderer);
                        plot.setLineRenderers(dataSeries[i], lineRenderer);
                    }catch(Exception e){
                        logger.error("Error caused when trying to set color for a series", e);
                    }
                }
            }
            super.drawPlot(plot, component, componentCanvas);
        }else{
            logger.warn("data not set, not rendering " + component);
        }
    }
}
