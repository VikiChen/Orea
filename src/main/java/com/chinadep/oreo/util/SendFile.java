package com.chinadep.oreo.util;


import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class SendFile implements Serializable {
    private static final long serialVersionUID = 1L;
    private String file_name;
    private int starPos;
    private byte[] bytes;
    private int endPos;
    private boolean isOver;
    private String path;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getStarPos() {
        return starPos;
    }

    public void setStarPos(int starPos) {
        this.starPos = starPos;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
