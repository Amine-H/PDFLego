package com.xhub.pdflego.core.vo;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.IOUtils.toByteArray;

/**
 * Created by amine
 */
public class PLFile {
    protected byte[] data;
    protected String path;
    private Logger logger = Logger.getLogger(PLFile.class);

    public PLFile(){}

    public PLFile(String path){
        this.setPath(path);
    }

    public PLFile(byte[] data){
        this.data = data;
    }

    public static PLFile createInstance(String path){
       return  new PLFile(path);
    }

    public static PLFile createInstance(byte[] data){
        return new PLFile(data);
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        try {
            //load data from remote resource
            URL url = new URL(path);
            InputStream stream = url.openStream();
            this.data = IOUtils.toByteArray(stream);
        }catch(MalformedURLException e){
            //load data from drive
            try{
                FileInputStream stream = new FileInputStream(path);
                this.data = IOUtils.toByteArray(stream);
            }catch(FileNotFoundException e1){
                logger.error("could not find file " + path, e);
            }catch(IOException e2){
                logger.error("could not read from file " + path, e);
            }
        }catch(IOException e){
            logger.error("could not read data from remote resource " + path, e);
        }
        this.path = path;
    }
}
