package com.xhub.pdflego.core.vo;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

/**
 * Created by amine
 */
public class PLImage{
    private PLFile file;

    public PLImage(){

    }

    public PLImage(PLFile file){
        this.file = file;
    }

    public static <T> T create(PLImage vo, Class c){
        T result = null;
        try{
            if(c.equals(ImageData.class)){
                result = (T) ImageDataFactory.create(vo.getFile().getData());
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
