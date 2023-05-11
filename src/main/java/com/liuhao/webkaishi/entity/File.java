package com.liuhao.webkaishi.entity;

public class File {
    private String name;
    private String time;
    private String filename;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
