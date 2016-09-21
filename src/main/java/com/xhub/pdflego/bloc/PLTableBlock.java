package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import com.itextpdf.kernel.color.Color;

/**
 * Created by amine
 */
public class PLTableBlock extends Component{
    public PLTableBlock(Component parent){
        super(parent);
    }
    private String[] header;
    private String[][] data;
    private Color headerBackgroundColor;
    private Color cellBackgroundColor;

    public String[][] getData() {
        return data;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public Color getHeaderBackgroundColor() {
        return headerBackgroundColor;
    }

    public void setHeaderBackgroundColor(Color headerBackgroundColor) {
        this.headerBackgroundColor = headerBackgroundColor;
    }

    public Color getCellBackgroundColor() {
        return cellBackgroundColor;
    }

    public void setCellBackgroundColor(Color cellBackgroundColor) {
        this.cellBackgroundColor = cellBackgroundColor;
    }
}
