package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.vo.ColorVO;

/**
 * Created by amine
 */
public class PLTableBlock extends Component{
    public PLTableBlock(Component parent){
        super(parent);
    }
    private String[] header;
    private String[][] data;
    private ColorVO headerBackgroundColor;
    private ColorVO cellBackgroundColor;
    private ColorVO[] zebraSripes;

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

    public ColorVO getHeaderBackgroundColor() {
        return headerBackgroundColor;
    }

    public void setHeaderBackgroundColor(ColorVO headerBackgroundColor) {
        this.headerBackgroundColor = headerBackgroundColor;
    }

    public ColorVO getCellBackgroundColor() {
        return cellBackgroundColor;
    }

    public void setCellBackgroundColor(ColorVO cellBackgroundColor) {
        this.cellBackgroundColor = cellBackgroundColor;
    }

    public ColorVO[] getZebraSripes() {
        return zebraSripes;
    }

    public void setZebraSripes(ColorVO[] zebraSripes) {
        if(zebraSripes != null && zebraSripes.length == 2) this.zebraSripes = zebraSripes;
    }
}
