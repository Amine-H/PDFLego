package com.xhub.pdflego.core.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by amine
 */
@XmlRootElement
public class ColorVO{
    private Integer r;
    private Integer g;
    private Integer b;

    public ColorVO(){}

    public ColorVO(Integer r, Integer g, Integer b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static <T> T create(ColorVO vo, Class c){
        T result = null;
        try{
            if(c.equals(java.awt.Color.class)){
                result = (T) new java.awt.Color(vo.getR(), vo.getG(), vo.getB());
            }else if(c.equals(com.itextpdf.kernel.color.Color.class)){
                result = (T) new com.itextpdf.kernel.color.DeviceRgb(vo.getR(), vo.getG(), vo.getB());
            }
        }catch (Exception e){}
        return result;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }
}
