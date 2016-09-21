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
    private Color fontColor;
    private Color headerBackgroundColor;
    private Color cellBackgroundColor;
    private Color[] zebraSripes;

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
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

    public Color[] getZebraSripes() {
        return zebraSripes;
    }

    public void setZebraSripes(Color[] zebraSripes) {
        this.zebraSripes = zebraSripes;
    }
}
