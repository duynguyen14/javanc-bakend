package com.example.book.models;

//import org.springframework.data.relational.core.sql.In;

public class Catalog {
    private Integer CatalodId;
    private String CatalodName;

    public Catalog() {
    }

    public Catalog(Integer catalodId, String catalodName) {
        CatalodId = catalodId;
        CatalodName = catalodName;
    }

    public Integer getCatalodId() {
        return CatalodId;
    }

    public void setCatalodId(Integer catalodId) {
        CatalodId = catalodId;
    }

    public String getCatalodName() {
        return CatalodName;
    }

    public void setCatalodName(String catalodName) {
        CatalodName = catalodName;
    }
}
