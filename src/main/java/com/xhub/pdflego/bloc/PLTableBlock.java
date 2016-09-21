package com.xhub.pdflego.bloc;

import com.xhub.pdflego.core.Component;

/**
 * Created by amine
 */
public class PLTableBlock extends Component{
    public PLTableBlock(Component parent){
        super(parent);
    }
    private String[][] data;

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
