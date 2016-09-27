package com.xhub.pdflego.formatter.pdf;

import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.xhub.pdflego.bloc.PLTableBlock;
import org.apache.log4j.Logger;

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
        com.itextpdf.kernel.color.Color headerBgColor = component.getHeaderBackgroundColor();
        com.itextpdf.kernel.color.Color cellBgColor = component.getCellBackgroundColor();
        com.itextpdf.kernel.color.Color fontColor = component.getFontColor();
        com.itextpdf.kernel.color.Color[] zebraStripes = component.getZebraSripes();
        if(headerBgColor == null){
            headerBgColor = cellBgColor;
        }
        if(data != null && data.length > 0){
            int columnCount = data[0].length;
            Table table = new Table(columnCount);
            table.setWidth(width);
            table.setHeight(height);
            if(headers != null){
                for(String header:headers){
                    Cell cell = new Cell().add(header);
                    cell.setBorder(Border.NO_BORDER);
                    if(fontColor != null){
                        cell.setFontColor(fontColor);
                    }
                    if(headerBgColor != null){
                        cell.setBackgroundColor(headerBgColor);
                    }
                    table.addHeaderCell(cell);
                }
            }
            for(int i = 0;i < data.length;i++){
                for (int j = 0;j < data[i].length;j++){
                    Cell cell = new Cell().add(data[i][j]);
                    cell.setBorder(Border.NO_BORDER);
                    if(fontColor != null){
                        cell.setFontColor(fontColor);
                    }
                    if(zebraStripes != null && zebraStripes.length == 2){
                        cell.setBackgroundColor(zebraStripes[i % 2]);
                    }
                    else if(cellBgColor != null){
                        cell.setBackgroundColor(cellBgColor);
                    }
                    table.addCell(cell);
                }
            }
            componentCanvas.add(table);
        }else{
            this.logger.warn("no data for " + component + " block was not rendered");
        }
    }
}
