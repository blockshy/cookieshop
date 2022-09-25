package model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Type {
    private int id;
    private String name;
    private String encodeName;

    public String getEncodeName() {
        return this.encodeName;
    }

    public void setEncodeName(String encodeName) {
        this.encodeName = encodeName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;

        try {
            this.encodeName = URLEncoder.encode(name, "utf-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

    }

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }
}
