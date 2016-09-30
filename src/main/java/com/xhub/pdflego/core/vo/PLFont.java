package com.xhub.pdflego.core.vo;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

/**
 * Created by amine
 */
public class PLFont{
    private PLFile file;
    private String encoding = "Identity-H";

    public PLFont(){

    }

    public PLFont(PLFile file){
        this.file = file;
    }

    public static <T> T create(PLFont vo, Class c){
        T result = null;
        try{
            if(c.equals(PdfFont.class)){
                result = (T) PdfFontFactory.createFont(vo.getFile().getData(), vo.getEncoding());
            }
        }catch(Exception e){}
        return result;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public PLFile getFile() {
        return file;
    }

    public void setFile(PLFile file) {
        this.file = file;
    }
}
