package com.se.aarati.termproject.model;

import com.se.aarati.termproject.model.common.ConnectionHandler;
import com.se.aarati.termproject.utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by aarati on 20/5/19.
 */
public class Resource {
    private String name;
    private String type;
    private String info;
    public Resource() {
    }

    public Resource(String name, String type, String info) {
        this.name = name;
        this.type = type;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
