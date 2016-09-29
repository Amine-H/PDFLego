package com.xhub.pdflego.core.vo;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

/**
 * Created by amine
 */
public class PLFont{
    private PLFile file;

    public PLFont(){

    }

    public PLFont(PLFile file){
        this.file = file;
    }

    public static <T> T create(PLFont vo, Class c){
        T result = null;
        try{
            if(c.equals(PdfFont.class)){
                result = (T) PdfFontFactory.createFont(vo.getFile().getData(),"Identity-H");
            }
        }catch(Exception e){}
        return result;
    }

    public PLFile getFile() {
        return file;
    }

    public void setFile(PLFile file) {
        this.file = file;
    }
}
