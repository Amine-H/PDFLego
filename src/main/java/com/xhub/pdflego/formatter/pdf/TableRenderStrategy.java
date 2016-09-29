package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.color.Color;
import com.xhub.pdflego.bloc.PLTableBlock;
import com.xhub.pdflego.core.vo.PLColor;
import org.apache.log4j.Logger;
import java.util.Arrays;

/**
 * Created by amine
 */
public class TableRenderStrategy implements ComponenRenderStrategy<PLTableBlock>{
    private Logger logger = Logger.getLogger(TableRenderStrategy.class);
    @Override
    public void render(Canvas componentCanvas, PLTableBlock component) {
        String[] headers = component.getHeader();
        String[][] data = component.getData();
        int width = component.getWidth();
        int height = component.getHeight();
        Color headerBgColor = PLColor.create(component.getHeaderBackgroundColor(), Color.class);
        Color cellBgColor = PLColor.create(component.getCellBackgroundColor(), Color.class);
        Color fontColor = PLColor.create(component.getFontColor(), Color.class);
        Float fontSize = component.getFontSize();
        Color[] zebraStripes = (component.getZebraSripes() == null)?null:Arrays.stream(component.getZebraSripes()).map(color -> PLColor.create(color, Color.class)).toArray(Color[]::new);

        if(headerBgColor == null) headerBgColor = cellBgColor;

        if(data != null && data.length > 0){
            int columnCount = data[0].length;
            Table table = new Table(columnCount);
            table.setWidth(width);
            table.setHeight(height);
            if(headers != null){
                for(String header:headers){
                    Cell cell = new Cell().add(header);
                    cell.setBorder(Border.NO_BORDER);
                    if(fontSize != null) cell.setFontSize(fontSize);
                    if(fontColor != null) cell.setFontColor(fontColor);
                    if(headerBgColor != null) cell.setBackgroundColor(headerBgColor);
                    table.addHeaderCell(cell);
                }
            }
            for(int i = 0;i < data.length;i++){
                for (int j = 0;j < data[i].length;j++){
                    Cell cell = new Cell().add(data[i][j]);
                    cell.setBorder(Border.NO_BORDER);
                    if(fontSize != null) cell.setFontSize(fontSize);
                    if(fontColor != null) cell.setFontColor(fontColor);
                    if(zebraStripes != null && zebraStripes.length == 2) cell.setBackgroundColor(zebraStripes[i % 2]);
                    else if(cellBgColor != null) cell.setBackgroundColor(cellBgColor);
                    table.addCell(cell);
                }
            }
            componentCanvas.add(table);
        }else{
            this.logger.warn("no data for " + component + " block was not rendered");
        }
    }
}
