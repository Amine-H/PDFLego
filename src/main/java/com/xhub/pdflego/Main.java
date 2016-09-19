package com.xhub.pdflego;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.FontConstants;
import com.xhub.pdflego.bloc.PLTextBlock;
import com.xhub.pdflego.core.Component;
import com.xhub.pdflego.core.Composite;
import com.xhub.pdflego.formatter.PDFRenderer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by amine
 */
public class Main {
    public  static void main(String[] args){
        try {
            Composite rootComponent = new Composite(){
                @Override
                public void postAdd(Component component) {

                }

                @Override
                public void preRemove(Component component) {

                }
            };
            rootComponent.setHeight(800);
            rootComponent.setWidth(500);
            PLTextBlock textBlock = new PLTextBlock(rootComponent);
            textBlock.setText("Hello itext");
            textBlock.setWidth(400);
            textBlock.setHeight(400);
            textBlock.setFontSize(14);
            textBlock.setFont(PdfFontFactory.createFont(FontConstants.TIMES_ROMAN));
            rootComponent.add(textBlock);

            PDFRenderer renderer = new PDFRenderer(rootComponent);
            ByteArrayOutputStream stream = renderer.render();
            File file = new File("demo.pdf");
            FileOutputStream fStream = new FileOutputStream(file);
            fStream.write(stream.toByteArray());
            fStream.flush();
            fStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
