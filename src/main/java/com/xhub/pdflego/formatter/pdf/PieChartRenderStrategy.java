package com.xhub.pdflego.formatter.pdf;

import java.awt.Color;
import java.util.Arrays;
import com.itextpdf.layout.Canvas;
import com.xhub.pdflego.bloc.PLPieChartBlock;
import com.xhub.pdflego.core.vo.PLColor;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.PiePlot.PieSliceRenderer;
import de.erichseifert.gral.plots.colors.IndexedColors;
import org.apache.log4j.Logger;

/**
 * Created by amine
 */
public class PieChartRenderStrategy extends PlotRenderHelper<PLPieChartBlock> implements ComponenRenderStrategy<PLPieChartBlock>{
    private Logger logger = Logger.getLogger(PieChartRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLPieChartBlock component) {
        DataTable dataTable = new DataTable(Integer.class);
        PiePlot plot = new PiePlot(dataTable);
        super.preparePlot(plot, component);
        Integer[] dataset = component.getData();
        Float gap = component.getGap();
        Float innerRadius = component.getInnerRadius();
        Float outerRadius = component.getOuterRadius();
        Color pieColor = PLColor.create(component.getPieColor(), Color.class);
        Color[] colors = (component.getColors() == null)?null:Arrays.stream(component.getColors()).map(color -> PLColor.create(color, Color.class)).toArray(Color[]::new);
        if(dataset != null){
            for(Integer row:dataset){
                dataTable.add(row);
            }
            PieSliceRenderer pointRenderer = (PieSliceRenderer) plot.getPointRenderer(dataTable);

            if(gap != null) pointRenderer.setGap(gap);
            if(innerRadius != null) pointRenderer.setInnerRadius(innerRadius);
            if(outerRadius != null) pointRenderer.setOuterRadius(outerRadius);
            if(pieColor != null && colors != null){
                IndexedColors indexedColors = new IndexedColors(pieColor, colors);
                pointRenderer.setColor(indexedColors);
            }
            super.drawPlot(plot, component, componentCanvas);
        }else{
            logger.warn("no data found for " + component + ", ignoring Block");
        }
    }
}
