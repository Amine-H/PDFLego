package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.PLColor;

/**
 * Created by amine
 */
public class PLTableBlock extends Component{
    public PLTableBlock(Component parent){
        super(parent);
    }
    private String[] header;
    private String[][] data;
    private PLColor headerBackgroundColor;
    private PLColor cellBackgroundColor;
    private PLColor[] zebraSripes;

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

    public PLColor getHeaderBackgroundColor() {
        return headerBackgroundColor;
    }

    public void setHeaderBackgroundColor(PLColor headerBackgroundColor) {
        this.headerBackgroundColor = headerBackgroundColor;
    }

    public PLColor getCellBackgroundColor() {
        return cellBackgroundColor;
    }

    public void setCellBackgroundColor(PLColor cellBackgroundColor) {
        this.cellBackgroundColor = cellBackgroundColor;
    }

    public PLColor[] getZebraSripes() {
        return zebraSripes;
    }

    public void setZebraSripes(PLColor[] zebraSripes) {
        if(zebraSripes != null && zebraSripes.length == 2) this.zebraSripes = zebraSripes;
    }
}
