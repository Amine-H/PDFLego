package com.xhub.pdflego.core.vo;

import org.apache.log4j.Logger;
/**
 * Created by amine
 */
public class PLColor {
    private Integer r;
    private Integer g;
    private Integer b;
    private Logger logger = Logger.getLogger(PLColor.class);

    public PLColor(){}

    public PLColor(Integer r, Integer g, Integer b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public PLColor(String hex){
        try{
            java.awt.Color color = java.awt.Color.decode(hex);
            this.r = color.getRed();
            this.g = color.getGreen();
            this.b = color.getBlue();
        }catch(NumberFormatException e){
            logger.error("couldn't load color data using hex value", e);
        }
    }

    public static <T> T create(PLColor vo, Class c){
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
